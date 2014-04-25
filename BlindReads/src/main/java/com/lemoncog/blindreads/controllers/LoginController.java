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

import org.apache.http.client.HttpResponseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import retrofit.RetrofitError;
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
    private IUserSupplier mUserSupplier;
    private OAuthConfig mOAuthConfig;
    private OAuthService mOAuthService;

    public LoginController(ILoginCallBack loginCallBack, OAuthService oAuthService, IUserSupplier userSupplier, OAuthConfig oAuthConfig) {
        mLoginCallback = loginCallBack;
        mUserSupplier = userSupplier;
        mOAuthConfig = oAuthConfig;
        mOAuthService = oAuthService;
    }

    public IUserSupplier getUserSupplier() {
        return mUserSupplier;
    }



    public void login()
    {
        IUser user = getUserSupplier().getUser();

        if(!user.isLoggedIn() && user.getToken() == null)
        {
            try
            {
                getUserSupplier().getUser().setToken(fetchRequestTokenAndSecret());
            }catch(HttpResponseException error)
            {
                //TODO - Handle fail message.
            }
        }

        if(user.getToken() != null)
        {
            IToken token = user.getToken();

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

    public IToken fetchRequestTokenAndSecret() throws HttpResponseException
    {
        Response response = null;
        try
        {
            response = mOAuthService.fetchRequestToken();
        }catch(RetrofitError error)
        {
            if(error.getResponse() != null)
            {
                throw new HttpResponseException(error.getResponse().getStatus(), "Too soon");
            }
        }

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
