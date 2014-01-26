//package com.lemoncog.blindreads.engine.oauth;
//
//import org.scribe.exceptions.*;
//import org.scribe.model.*;
//import org.scribe.utils.*;
//
//import retrofit.client.*;
//import retrofit.client.Request;
//
//
//public class BaseStringExtractorImpl
//{
//
//    private static final String AMPERSAND_SEPARATED_STRING = "%s&%s&%s";
//
//    /**
//     * {@inheritDoc}
//     */
//    public String extract(Request request)
//    {
//        checkPreconditions(request);
//
//        String verb = OAuthEncoder.encode(request.getVerb().name());
//        String url = OAuthEncoder.encode(request.getUrl());
//        String params = getSortedAndEncodedParams(request);
//        return String.format(AMPERSAND_SEPARATED_STRING, verb, url, params);
//    }
//
//    private String getSortedAndEncodedParams(Request request)
//    {
//        ParameterList params = new ParameterList();
//
//        params.addQuerystring(request.getMethod());
//
//       // params.addAll(request.getBodyParams());
//        new ParameterList()
//        params.addAll(new ParameterList(request.getOauthParameters()));
//        return params.sort().asOauthBaseString();
//    }
//
//    private void checkPreconditions(OAuthRequest request)
//    {
//        Preconditions.checkNotNull(request, "Cannot extract base string from null object");
//
//        if (request.getOauthParameters() == null || request.getOauthParameters().size() <= 0)
//        {
//            throw new OAuthParametersMissingException(request);
//        }
//    }
//}