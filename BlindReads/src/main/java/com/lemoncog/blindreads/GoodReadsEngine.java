package com.lemoncog.blindreads;


import com.google.inject.Singleton;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.engine.MemoryUserSupplier;
import com.lemoncog.blindreads.models.IUser;
import com.lemoncog.blindreads.oAuth.IToken;
import com.lemoncog.blindreads.oAuth.Token;

import org.simpleframework.xml.core.Persister;

import retrofit.Endpoint;
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
public class GoodReadsEngine {
    private static final String PRODUCTION_API_URL = "https://www.goodreads.com";
    private static String API_KEY = "uyFUveTbOQWKWgszgLL2xQ";
    private static String API_SECRET = "8wos2RNrsBj5ItGLQ5yHKA7NI7wMF5lhtmCJFI6ic";
    //private static IToken API_TOKEN = new Token();

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

    public static Endpoint provideEndpoint()
    {
        return new Endpoint() {
            @Override
            public String getUrl() {
                return PRODUCTION_API_URL;
            }

            @Override
            public String getName() {
                return "ProductonServer";
            }
        };
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
    public static RetrofitHttpOAuthConsumer provideOAuthConsumer()
    {
        if(mOAuthConsumer == null)
        {
            mOAuthConsumer =  new RetrofitHttpOAuthConsumer(provideAPIKey(), provideAPISecret());
        }

        return mOAuthConsumer;
    }

    public static RestAdapter provideRestAdapter(Endpoint endPoint, Client client, Converter converter)
    {
        return new RestAdapter.Builder().setClient(client).setEndpoint(endPoint).setConverter(converter).setLogLevel(RestAdapter.LogLevel.FULL).build();
    }
}
