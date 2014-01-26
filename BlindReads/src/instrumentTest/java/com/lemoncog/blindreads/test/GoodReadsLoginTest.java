package com.lemoncog.blindreads.test;

import android.app.Application;
import android.content.Context;
import android.test.ActivityTestCase;

import com.google.inject.util.Modules;
import com.lemoncog.blindreads.ApiFactory;
import com.lemoncog.blindreads.ApiModule;
import com.lemoncog.blindreads.controllers.ILoginCallBack;
import com.lemoncog.blindreads.controllers.LoginController;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.goodreads.OAuthService;
import com.lemoncog.blindreads.oAuth.IToken;
import com.lemoncog.blindreads.oAuth.OAuthConfig;
import com.lemoncog.blindreads.oAuth.Token;
import com.lemoncog.blindreads.test.factory.MockFactory;

import junit.framework.Assert;

import org.mockito.internal.matchers.Any;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
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
        return ApiFactory.provideRestAdapter(ApiFactory.provideServer(), ApiFactory.provideClient(), ApiFactory.provideConverter()).create(OAuthService.class);
    }

    public void testOAuthRequestToken()
    {
        IUserSupplier userSupplier = mock(IUserSupplier.class);
        ILoginCallBack loginCallBack = mock(ILoginCallBack.class);
        OAuthConfig oAuthConfig = mock(OAuthConfig.class);

        LoginController loginController = new LoginController(loginCallBack, createOAuthService(), userSupplier, oAuthConfig);

        IToken token = loginController.fetchRequestTokenAndSecret();

        assertFalse(token == null);
        assertTrue(token.getToken().length() > 0);
        assertTrue(token.getTokenSecret().length() > 0);
    }

    public void testOAuthAuthorizeURL()
    {
        ILoginCallBack loginCallBack = mock(ILoginCallBack.class);
        IUserSupplier userSupplier = mock(IUserSupplier.class);
        OAuthConfig oAuthConfig = mock(OAuthConfig.class);
        when(oAuthConfig.getAuthorizeURL(anyString(), anyString())).thenReturn("www.authorizeuser.com");

        LoginController loginController = new LoginController(loginCallBack, createOAuthService(), userSupplier, oAuthConfig);

        loginController.authorizeUser();

        verify(loginCallBack).requestWebView("www.authorizeuser.com");
    }
}
