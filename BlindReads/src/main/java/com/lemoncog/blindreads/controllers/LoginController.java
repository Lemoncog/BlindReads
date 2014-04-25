package com.lemoncog.blindreads.controllers;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.lemoncog.blindreads.GoodReadsEngine;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.goodreads.OAuthService;
import com.lemoncog.blindreads.models.IUser;
import com.lemoncog.blindreads.oAuth.IToken;
import com.lemoncog.blindreads.oAuth.OAuthConfig;
import com.lemoncog.blindreads.oAuth.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.util.functions.Action1;

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
        IToken token = null;
        if(!user.isLoggedIn())
        {
            getUserSupplier().getUser().setToken(token);

            //Update our dodgy static class :S
            GoodReadsEngine.provideOAuthConsumer().setTokenWithSecret(token.getToken(), token.getTokenSecret());

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

        GoodReadsEngine.provideOAuthConsumer().setTokenWithSecret(token.getToken(), token.getTokenSecret());

        //Groovy, we are now authenticated!
        mLoginCallback.userLoggedIn();
    }

    public void userDeniedAuthorization() {
        //Well..
    }

    public void logOut()
    {
        IUser user = getUserSupplier().getUser();

        user.setToken(null);
        user.setLoggedIn(false);
    }
}
