package com.lemoncog.blindreads.controllers;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.goodreads.OAuthService;
import com.lemoncog.blindreads.models.IUser;
import com.lemoncog.blindreads.oAuth.IToken;
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
    private RestAdapter mRestAdapter;
    private IUserSupplier mUserSupplier;

    @Inject
    public LoginController(ILoginCallBack loginCallBack, RestAdapter restAdapter, IUserSupplier userSupplier) {
        mLoginCallback = loginCallBack;
        mRestAdapter = restAdapter;
        mUserSupplier = userSupplier;
    }

    public IUserSupplier getUserSupplier() {
        return mUserSupplier;
    }

    public void login(IUser user)
    {
    }

    public void authorizeUser(IUser user)
    {
        
    }

    public IToken fetchRequestTokenAndSecret()
    {
        OAuthService service = mRestAdapter.create(OAuthService.class);

        Response response = service.fetchRequestToken();

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
}
