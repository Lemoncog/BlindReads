package com.lemoncog.blindreads;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.engine.MemoryUserSupplier;
import com.lemoncog.blindreads.goodreads.GoodReadsOAuthConfig;
import com.lemoncog.blindreads.models.IUser;
import com.lemoncog.blindreads.models.User;
import com.lemoncog.blindreads.oAuth.OAuthConfig;

import retrofit.RestAdapter;
import retrofit.Server;
import retrofit.client.Client;
import retrofit.converter.Converter;
import retrofit.converter.SimpleXMLConverter;
import se.akerfeldt.signpost.retrofit.SigningOkClient;

/**
 * Created by Gaming on 26/01/14.
 */
public class ApiModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(OAuthConfig.class).to(GoodReadsOAuthConfig.class).in(Scopes.SINGLETON);
        bind(IUserSupplier.class).to(MemoryUserSupplier.class).in(Scopes.SINGLETON);
        bind(IUser.class).to(User.class);
        bind(GoodReadsEngine.class).in(Scopes.SINGLETON);
    }
}
