package com.lemoncog.blindreads.engine;

import com.lemoncog.blindreads.models.IUser;

import javax.inject.Inject;

/**
 * Created by Gaming on 26/01/14.
 */
public class MemoryUserSupplier implements IUserSupplier {
    @Inject
    private IUser mUser;

    @Inject
    public MemoryUserSupplier()
    {
    }

    @Override
    public IUser getUser() {
        return mUser;
    }
}
