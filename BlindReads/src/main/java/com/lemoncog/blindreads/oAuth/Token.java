package com.lemoncog.blindreads.oAuth;

/**
 * Created by Gaming on 25/01/14.
 */
public class Token implements IToken {
    private static String KEY_OAUTH_TOKEN = "oauth_token=";
    private static String KEY_OAUTH_TOKEN_SECRET = "oauth_token_secret=";

    private String mToken;
    private String mTokenSecret;

    public Token(String tokenAndSecret)
    {
        this(extractToken(tokenAndSecret), extractTokenSecret(tokenAndSecret));
    }

    public Token(String token, String secret)
    {
        mToken = token;
        mTokenSecret = secret;
    }

    public Token()
    {
    }

    public static String extractToken(String tokenAndSecret)
    {
        String value = extractValueAfterKey(KEY_OAUTH_TOKEN, tokenAndSecret);

        int endIndex = value.indexOf("&");
        return  value.substring(0, endIndex);
    }

    public static String extractTokenSecret(String tokenAndSecret) {
        return extractValueAfterKey(KEY_OAUTH_TOKEN_SECRET, tokenAndSecret);
    }

    public static String extractValueAfterKey(String key, String string)
    {
        int keyIndex = string.indexOf(key);

        if(keyIndex > -1)
        {
            return string.substring(keyIndex+key.length());
        }

        return "";
    }

    @Override
    public String getToken() {
        return mToken;
    }

    @Override
    public String getTokenSecret() {
        return mTokenSecret;
    }

    @Override
    public boolean hasTokenAndSecret() {
        return getToken() != null && getTokenSecret() != null;
    }
}
