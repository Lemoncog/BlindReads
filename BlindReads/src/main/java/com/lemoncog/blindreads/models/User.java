package com.lemoncog.blindreads.models;

import com.lemoncog.blindreads.oAuth.IToken;

import java.util.List;

/**
 * Created by Gaming on 23/01/14.
 */
public class User implements IUser
{
    private String mUserID;
    private BookList mBookList;
    private IToken mToken;
    private boolean mLoggedIn = false;

    @Override
    public void setUserID(String userID) {
        mUserID = userID;
    }

    @Override
    public String getUserID() {
        return mUserID;
    }

    @Override
    public void setBookListing(BookList bookListing) {
        mBookList = bookListing;
    }

    @Override
    public boolean isLoggedIn() {
        return mLoggedIn;
    }

    @Override
    public IToken getToken() {
        return mToken;
    }

    @Override
    public void setToken(IToken token) {
        mToken = token;
    }

    @Override
    public void setLoggedIn(boolean loggedIn) {
        mLoggedIn = loggedIn;
    }
}
