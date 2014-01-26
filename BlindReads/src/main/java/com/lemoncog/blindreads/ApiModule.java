package com.lemoncog.blindreads;

import com.google.inject.AbstractModule;
import com.lemoncog.blindreads.engine.IUserSupplier;

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
    }
}
