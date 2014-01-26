//package com.lemoncog.blindreads.engine.oauth;
//
//import org.scribe.builder.api.DefaultApi10a;
//import org.scribe.model.Token;
//
///**
// * Created by Gaming on 25/01/14.
// */
//public class GoodReadsApi extends DefaultApi10a {
//
//    private static String  AUTHORIZATION_URL = "http://www.goodreads.com/api/oauth/authorize?oauth_token=%s";
//
//    @Override
//    public String getRequestTokenEndpoint() {
//        return "http://www.goodreads.com/api/oauth/request_token";
//    }
//
//    @Override
//    public String getAccessTokenEndpoint() {
//        return "http://www.goodreads.com/api/oauth/";
//    }
//
//    @Override
//    public String getAuthorizationUrl(Token token) {
//        return String.format(AUTHORIZATION_URL, token.getToken());
//    }
//}
