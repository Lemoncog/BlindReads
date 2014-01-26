package com.lemoncog.blindreads.models;

import java.util.List;

/**
 * Created by Gaming on 23/01/14.
 */
public class User implements IUser
{
    private String mUserID;
    private BookList mBookList;

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
        return false;
    }
}
