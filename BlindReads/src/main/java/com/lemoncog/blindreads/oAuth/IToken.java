package com.lemoncog.blindreads.oAuth;

/**
 * Created by Gaming on 25/01/14.
 */
public interface IToken
{
    public String getToken();
    public String getTokenSecret();
    boolean hasTokenAndSecret();
}
