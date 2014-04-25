package com.lemoncog.blindreads.test;

import android.test.ActivityTestCase;

import com.lemoncog.blindreads.GoodReadsEngine;
import com.lemoncog.blindreads.controllers.ILoginCallBack;
import com.lemoncog.blindreads.controllers.LoginController;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.goodreads.OAuthService;
import com.lemoncog.blindreads.models.IUser;
import com.lemoncog.blindreads.oAuth.IToken;
import com.lemoncog.blindreads.oAuth.OAuthConfig;
import com.lemoncog.blindreads.oAuth.Token;

import junit.framework.Assert;

import org.apache.http.client.HttpResponseException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gaming on 25/01/14.
 */
public class GoodReadsLoginTest extends ActivityTestCase {
    public void testTokenParsesToken()
    {
        Token token = new Token("oauth_token=ncMexBE28oQvldwv1fjELg&oauth_token_secret=q2HZyMhbhwNc1oJ6bg7cwdqn0xdVcXKiObO4Gnq974");

        Assert.assertTrue(token.getToken().equals("ncMexBE28oQvldwv1fjELg"));
    }

    public void testTokenParsesTokenSecret()
    {
        Token token = new Token("oauth_token=ncMexBE28oQvldwv1fjELg&oauth_token_secret=q2HZyMhbhwNc1oJ6bg7cwdqn0xdVcXKiObO4Gnq974");

        Assert.assertTrue(token.getTokenSecret().equals("q2HZyMhbhwNc1oJ6bg7cwdqn0xdVcXKiObO4Gnq974"));
    }

    public OAuthService createOAuthService()
    {
        return GoodReadsEngine.provideRestAdapter(GoodReadsEngine.provideServer(), GoodReadsEngine.provideClient(), GoodReadsEngine.provideConverter()).create(OAuthService.class);
    }

    public void testOAuthRequestToken()
    {
        IUserSupplier userSupplier = mock(IUserSupplier.class);
        ILoginCallBack loginCallBack = mock(ILoginCallBack.class);
        OAuthConfig oAuthConfig = mock(OAuthConfig.class);

        LoginController loginController = new LoginController(loginCallBack, createOAuthService(), userSupplier, oAuthConfig);

        IToken token = null;
        try
        {
            token = loginController.fetchRequestTokenAndSecret();
        }catch(HttpResponseException error)
        {
        }

        assertFalse(token == null);
        assertTrue(token.getToken().length() > 0);
        assertTrue(token.getTokenSecret().length() > 0);
    }

    public void testOAuthAuthorizeURL()
    {
        ILoginCallBack loginCallBack = mock(ILoginCallBack.class);

        IToken token = mock(IToken.class);
        when(token.getToken()).thenReturn("tokenYo");

        IUser user = mock(IUser.class);
        when(user.getToken()).thenReturn(token);

        IUserSupplier userSupplier = mock(IUserSupplier.class);
        when(userSupplier.getUser()).thenReturn(user);

        OAuthConfig oAuthConfig = mock(OAuthConfig.class);
        when(oAuthConfig.getAuthorizeURL(anyString(), anyString())).thenReturn("www.authorizeuser.com");

        LoginController loginController = new LoginController(loginCallBack, createOAuthService(), userSupplier, oAuthConfig);

        loginController.authorizeUser();

        verify(loginCallBack).requestWebView("www.authorizeuser.com");
    }

    public void testLogoutUserController()
    {
        IUser user = mock(IUser.class);

        ILoginCallBack loginCallBack = mock(ILoginCallBack.class);
        IUserSupplier userSupplier = mock(IUserSupplier.class);
        when(userSupplier.getUser()).thenReturn(user);

        OAuthConfig oAuthConfig = mock(OAuthConfig.class);
        LoginController loginController = new LoginController(loginCallBack, createOAuthService(), userSupplier, oAuthConfig);

        loginController.logOut();

        verify(user).setLoggedIn(false);
        verify(user).setToken(null);
    }
}
