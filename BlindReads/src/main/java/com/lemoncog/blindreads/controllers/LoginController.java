package com.lemoncog.blindreads.controllers;
import android.util.Log;

import com.lemoncog.blindreads.ApiFactory;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.goodreads.OAuthService;
import com.lemoncog.blindreads.models.IUser;
import com.lemoncog.blindreads.oAuth.IToken;
import com.lemoncog.blindreads.oAuth.OAuthConfig;
import com.lemoncog.blindreads.oAuth.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

import retrofit.RestAdapter;
import retrofit.client.Response;

/**
 * Created by Gaming on 23/01/14.
 */
public class LoginController
{
    private ILoginCallBack mLoginCallback;
    //private RestAdapter mRestAdapter;
    private OAuthService mOAuthService;
    private IUserSupplier mUserSupplier;
    private OAuthConfig mOAuthConfig;

    @Inject
    public LoginController(ILoginCallBack loginCallBack, OAuthService oAuthService, IUserSupplier userSupplier, OAuthConfig oAuthConfig) {
        mLoginCallback = loginCallBack;
        mOAuthService = oAuthService;
        mUserSupplier = userSupplier;
        mOAuthConfig = oAuthConfig;
    }

    public IUserSupplier getUserSupplier() {
        return mUserSupplier;
    }

    public void login()
    {
        IUser user = getUserSupplier().getUser();

        if(!user.isLoggedIn())
        {
            IToken token = fetchRequestTokenAndSecret();
            user.setToken(token);

            //Update our dodgy static class :S
            ApiFactory.provideOAuthConsumer().setTokenWithSecret(token.getToken(), token.getTokenSecret());

            authorizeUser();
        }
    }

    public void authorizeUser() {
        promptUserToAuthorize(mOAuthConfig.getAuthorizeURL(getUserSupplier().getUser().getToken().getToken(), ""));
    }

    private void promptUserToAuthorize(String authorizeURL) {
        mLoginCallback.requestWebView(authorizeURL);
    }

    public IToken fetchRequestTokenAndSecret()
    {
        Response response = mOAuthService.fetchRequestToken();

        IToken tokenAndSecret = null;
        try {

            BufferedReader r = new BufferedReader(new InputStreamReader(response.getBody().in()));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }

            tokenAndSecret = new Token(total.toString());
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return tokenAndSecret;
    }


    public void userAcceptedAuthorization(String url)
    {
        //Update again with final token
        IToken oldToken = getUserSupplier().getUser().getToken();

        IToken token = new Token(Token.extractToken(url),oldToken.getTokenSecret());

        getUserSupplier().getUser().setToken(token);
        getUserSupplier().getUser().setLoggedIn(true);

        ApiFactory.provideOAuthConsumer().setTokenWithSecret(token.getToken(), token.getTokenSecret());

        //TEST!
        Response response = mOAuthService.getFriendRequests();

        try {

            BufferedReader r = new BufferedReader(new InputStreamReader(response.getBody().in()));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }

            Log.v("LoginController", total.toString());

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void userDeniedAuthorization() {

    }
}
