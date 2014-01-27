package com.lemoncog.blindreads.goodreads;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


import com.lemoncog.blindreads.models.BookList;
import com.lemoncog.blindreads.models.User;
import com.lemoncog.blindreads.models.Book;

import java.util.List;

/**
 * Created by Gaming on 23/01/14.
 */
public interface GoodReadsService {
    @GET("/review/list/{userID}.xml")
    BookList usersBookList(@Path("userID") String userID, @Query("key") String apiKey, @Query("v") String version, @Query("shelf") String shelf);

    @GET("/review/list/{userID}.xml")
    BookList usersBookList(@Path("userID") String userID, @Query("key") String apiKey, @Query("v") String version);

    @GET("/api/auth_user")
    Response authUser();

    @GET("/friend/requests.xml")
    Response getFriendRequests();
}
