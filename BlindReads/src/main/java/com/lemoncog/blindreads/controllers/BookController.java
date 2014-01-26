package com.lemoncog.blindreads.controllers;

import android.util.Log;

import com.lemoncog.blindreads.ApiFactory;
import com.lemoncog.blindreads.goodreads.GoodReadsService;
import com.lemoncog.blindreads.models.BookList;
import com.lemoncog.blindreads.models.IUser;
import com.lemoncog.blindreads.models.User;

import retrofit.RestAdapter;
/**
 * Created by Gaming on 23/01/14.
 */
public class BookController
{
    private RestAdapter mRestAdapter;
    String apiKey = ApiFactory.provideAPIKey();

    public BookController(RestAdapter restAdapter)
    {
        mRestAdapter = restAdapter;
    }

    public BookList getUserBookList(IUser user)
    {
        GoodReadsService service = mRestAdapter.create(GoodReadsService.class);

        BookList books = service.BookList(user.getUserID(), apiKey, "2");

        Log.v("HAHAHA MADNESS", books.getReviews().get(0).getBook().getTitle());

        //Notify its complete or fail or whatever!
        return books;
    }
}
