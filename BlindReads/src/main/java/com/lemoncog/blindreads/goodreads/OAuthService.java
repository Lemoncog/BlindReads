package com.lemoncog.blindreads.goodreads;

import com.lemoncog.blindreads.models.BookList;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Gaming on 25/01/14.
 */
public interface OAuthService {
    @GET("/oauth/request_token")
    Response fetchRequestToken();
}
