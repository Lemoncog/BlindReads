//package com.lemoncog.blindreads.engine.oauth;
//
//
//import com.lemoncog.blindreads.GoodReadsEngine;
//
//import org.scribe.builder.ServiceBuilder;
//import org.scribe.builder.api.DefaultApi10a;
//import org.scribe.model.OAuthConfig;
//import org.scribe.model.OAuthConstants;
//import org.scribe.model.OAuthRequest;
//import org.scribe.model.Token;
//import org.scribe.model.Verb;
//import org.scribe.oauth.OAuthService;
//import org.scribe.services.Base64Encoder;
//
//import java.io.IOException;
//
//import retrofit.client.Client;
//import retrofit.client.Header;
//import retrofit.client.Request;
//import retrofit.client.Response;
//
///**
// * Created by Gaming on 25/01/14.
// */
//public class OAuthClient implements Client
//{
//    private static final String VERSION = "1.0";
//    final Client wrapped;
//
////    private OAuthService service = new ServiceBuilder().provider(GoodReadsApi.class).apiKey(GoodReadsEngine.provideAPIKey()).apiSecret(GoodReadsEngine.provideAPISecret()).build();
//    private DefaultApi10a api;
//    private OAuthConfig config;
//
//    public OAuthClient(Client client, DefaultApi10a api10a) {
//        wrapped = client;
//    }
//
//    @Override
//    public Response execute(Request request) throws IOException {
//        Request newRequest = sign(request);
//
//        return wrapped.execute(request);
//    }
//
//    private Request sign(Request request) {
//
//        signRequest(token, request);
//
//        return request;
//    }
//
//    public String getVersion()
//    {
//        return VERSION;
//    }
//
//    public void addOAuthParams(Request request, Token token)
//    {
//        addHeader(request, OAuthConstants.TIMESTAMP, api.getTimestampService().getTimestampInSeconds());
//        addHeader(request, OAuthConstants.NONCE, api.getTimestampService().getNonce());
//        addHeader(request, OAuthConstants.CONSUMER_KEY, config.getApiKey());
//        addHeader(request, OAuthConstants.SIGN_METHOD, api.getSignatureService().getSignatureMethod());
//        addHeader(request, OAuthConstants.VERSION, getVersion());
//
//        if(config.hasScope())
//        {
//            addHeader(request,OAuthConstants.SCOPE, config.getScope());
//        }
//
//        addHeader(request, OAuthConstants.SIGNATURE, getSignature(request, token));
//    }
//
//    private void addHeader(Request request, String key, String value)
//    {
//        request.getHeaders().add(new Header(key, value));
//    }
//
//    private String getSignature(Request request, Token token)
//    {
//        config.log("generating signature...");
//        config.log("using base64 encoder: " + Base64Encoder.type());
//        String baseString = api.getBaseStringExtractor().extract(request);
//        String signature = api.getSignatureService().getSignature(baseString, config.getApiSecret(), token.getSecret());
//
//        config.log("base string is: " + baseString);
//        config.log("signature is: " + signature);
//        return signature;
//    }
//
//    public void signRequest(Token token, Request request)
//    {
//        addOAuthParams(request, token);
//        //appendSignature(request);
//    }
//}
