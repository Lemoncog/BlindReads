package com.lemoncog.blindreads.oAuth;

/**
 * Created by Gaming on 26/01/14.
 */
public interface OAuthConfig {
    public String getAuthorizeURL(String token, String callback);
}
