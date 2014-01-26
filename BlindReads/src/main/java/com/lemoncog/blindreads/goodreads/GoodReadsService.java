package com.lemoncog.blindreads.goodreads;

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
    @GET("/review/list/{userID}.json")
    BookList BookList(@Path("userID") String userID, @Query("key") String apiKey, @Query("v") String version);
}
