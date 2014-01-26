package com.lemoncog.blindreads.goodreads;

import com.lemoncog.blindreads.oAuth.OAuthConfig;

/**
 * Created by Gaming on 26/01/14.
 */
public class GoodReadsOAuthConfig implements OAuthConfig {

    @Override
    public String getAuthorizeURL(String token, String callback)
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append("http://www.goodreads.com/oauth/authorize?oauth_token=").append(token);

        if(callback.length() > 0)
        {
            buffer.append("oauth_callback=").append(callback);
        }

        return buffer.toString();
    }
}
