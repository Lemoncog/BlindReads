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
import com.lemoncog.blindreads.oAuth.IToken;
import com.lemoncog.blindreads.oAuth.Token;
import com.lemoncog.blindreads.test.factory.MockFactory;

import junit.framework.Assert;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gaming on 25/01/14.
 */
public class GoodReadsLoginTest extends ActivityTestCase {

    protected Application application = mock(Application.class, RETURNS_DEEP_STUBS);
    protected Context context = mock(RoboActivity.class, RETURNS_DEEP_STUBS);

    @Override
    protected void setUp() throws Exception {
        // Override the default RoboGuice module
        RoboGuice.setBaseApplicationInjector(application, RoboGuice.DEFAULT_STAGE, Modules.override(RoboGuice.newDefaultRoboModule(application)).with(new ApiModule()));
    }

    @Override
    protected void tearDown() throws Exception {
        RoboGuice.util.reset();;
    }

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

    public void testOAuthRequestToken()
    {
        IUserSupplier userSupplier = mock(IUserSupplier.class);
        ILoginCallBack loginCallBack = mock(ILoginCallBack.class);

        LoginController loginController = new LoginController(loginCallBack, ApiFactory.provideRestAdapter(ApiFactory.provideServer(), ApiFactory.provideClient(), ApiFactory.provideConverter()), userSupplier);

        IToken token = loginController.fetchRequestTokenAndSecret();

        assertFalse(token == null);
        assertTrue(token.getToken().length() > 0);
        assertTrue(token.getTokenSecret().length() > 0);
    }
}
