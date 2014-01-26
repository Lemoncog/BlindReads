package com.lemoncog.blindreads;


import com.lemoncog.blindreads.oAuth.IToken;
import com.lemoncog.blindreads.oAuth.Token;

import org.simpleframework.xml.core.Persister;

import retrofit.RestAdapter;
import retrofit.Server;
import retrofit.client.Client;
import retrofit.converter.Converter;
import retrofit.converter.SimpleXMLConverter;
import se.akerfeldt.signpost.retrofit.RetrofitHttpOAuthConsumer;
import se.akerfeldt.signpost.retrofit.SigningOkClient;

/**
 * Created by Gaming on 25/01/14.
 */
public class ApiFactory {
    private static final String PRODUCTION_API_URL = "https://www.goodreads.com";
    private static String API_KEY = "uyFUveTbOQWKWgszgLL2xQ";
    private static String API_SECRET = "8wos2RNrsBj5ItGLQ5yHKA7NI7wMF5lhtmCJFI6ic";
    private static IToken API_TOKEN = new Token();

    public static IToken provideAPIToken()
    {
        return API_TOKEN;
    }

    public static String provideAPIKey(){
        return API_KEY;
    }

    public static String provideAPISecret() {
        return API_SECRET;
    }

    public static Server provideServer()
    {
        return new Server(PRODUCTION_API_URL);
    }

    public static Converter provideConverter()
    {
        return new SimpleXMLConverter(new Persister());
    }

    public static Client provideClient()
    {
        return new SigningOkClient(provideOAuthConsumer());
    }

    //Shitty Singleton(wut) I will regret.
    private static RetrofitHttpOAuthConsumer mOAuthConsumer;
    private static RetrofitHttpOAuthConsumer provideOAuthConsumer()
    {
        if(mOAuthConsumer == null)
        {
            mOAuthConsumer =  new RetrofitHttpOAuthConsumer(provideAPIKey(), provideAPISecret());
        }

        if(API_TOKEN.hasTokenAndSecret())
        {
            mOAuthConsumer.setTokenWithSecret(API_TOKEN.getToken(), API_TOKEN.getTokenSecret());
        }

        return mOAuthConsumer;
    }

    public static RestAdapter provideRestAdapter(Server server, Client client, Converter converter)
    {
        return new RestAdapter.Builder().setClient(client).setServer(server).setConverter(converter).build();
    }
}
