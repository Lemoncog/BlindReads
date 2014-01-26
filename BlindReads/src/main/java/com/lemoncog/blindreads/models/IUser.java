package com.lemoncog.blindreads.models;

/**
 * Created by Gaming on 25/01/14.
 */
public interface IUser {
    public void setUserID(String userID);
    public String getUserID();
    public void setBookListing(BookList bookListing);

    boolean isLoggedIn();
}
