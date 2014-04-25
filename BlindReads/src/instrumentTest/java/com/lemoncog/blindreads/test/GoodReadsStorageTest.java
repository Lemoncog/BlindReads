package com.lemoncog.blindreads.test;

import android.test.ApplicationTestCase;

import com.google.inject.util.Modules;
import com.lemoncog.blindreads.ApiModule;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.models.IUser;

import roboguice.RoboGuice;

import static org.mockito.Mockito.mock;

/**
 * Created by Gaming on 29/01/14.
 */
public class GoodReadsStorageTest extends ApplicationTestCase{

   // protected Application application = mock(Application.class, RETURNS_DEEP_STUBS);

    public GoodReadsStorageTest(Class applicationClass) {
        super(applicationClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        RoboGuice.setBaseApplicationInjector(getApplication(), RoboGuice.DEFAULT_STAGE, Modules.override(RoboGuice.newDefaultRoboModule(getApplication())).with(new ApiModule()));
    }

    public void testUserSupplierStoreUser(){
        IUserSupplier userSupplier = RoboGuice.getInjector(getApplication()).getInstance(IUserSupplier.class);

        //Create a mock user
        IUser user = mock(IUser.class);

        userSupplier.getUser();
    }
}
