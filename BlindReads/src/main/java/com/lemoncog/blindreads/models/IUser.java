package com.lemoncog.blindreads.models;

import com.lemoncog.blindreads.oAuth.IToken;

/**
 * Created by Gaming on 25/01/14.
 */
public interface IUser {
    public void setUserID(String userID);
    public String getUserID();
    public void setBookListing(BookList bookListing);

    boolean isLoggedIn();
    IToken getToken();

    void setToken(IToken token);

    void setLoggedIn(boolean loggedIn);
}
