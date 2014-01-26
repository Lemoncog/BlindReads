package com.lemoncog.blindreads;

import android.app.Application;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by Gaming on 25/01/14.
 */
public class BlindReadsApp extends Application
{
    private Injector mInjector;

    @Override
    public void onCreate()
    {
        super.onCreate();

       mInjector = Guice.createInjector(new ApiModule());
    }

    public Injector getInjector()
    {
        return mInjector;
    }
}
