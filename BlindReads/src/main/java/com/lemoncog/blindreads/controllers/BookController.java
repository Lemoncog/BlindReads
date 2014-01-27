package com.lemoncog.blindreads.controllers;

import android.util.Log;

import com.lemoncog.blindreads.ApiFactory;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.goodreads.GoodReadsService;
import com.lemoncog.blindreads.models.BookList;
import com.lemoncog.blindreads.models.IUser;

import retrofit.RestAdapter;
/**
 * Created by Gaming on 23/01/14.
 */
public class BookController
{
    private RestAdapter mRestAdapter;
    private IUserSupplier mUserSupplier;
    String apiKey = ApiFactory.provideAPIKey();

    public BookController(IUserSupplier userSupplier, RestAdapter restAdapter)
    {
        mRestAdapter = restAdapter;
    }

    public BookList getUserBookList(IUser user)
    {
        GoodReadsService service = mRestAdapter.create(GoodReadsService.class);

        BookList books = service.usersBookList(user.getUserID(), apiKey, "2", "currently-reading");

        Log.v("HAHAHA MADNESS", books.getReviews().get(0).getBook().getTitle());

        //Notify its complete or fail or whatever!
        return books;
    }
}
