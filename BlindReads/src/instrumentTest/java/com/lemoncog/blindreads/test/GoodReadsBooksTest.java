package com.lemoncog.blindreads.test;

import android.test.ActivityTestCase;

import com.lemoncog.blindreads.ApiFactory;
import com.lemoncog.blindreads.controllers.BookController;
import com.lemoncog.blindreads.models.BookList;
import com.lemoncog.blindreads.test.factory.MockFactory;

import junit.framework.Assert;

/**
 * Created by Gaming on 25/01/14.
 */
public class GoodReadsBooksTest extends ActivityTestCase
{
    public void testHelloWorld()
    {
        Assert.assertTrue("Hello World".equals("Hello World"));
    }

    public void testGetPublicUserBookList()
    {
        BookController bookController = new BookController(ApiFactory.provideRestAdapter(ApiFactory.provideServer(), ApiFactory.provideClient(), ApiFactory.provideConverter()));

        BookList bookList = bookController.getUserBookList(MockFactory.getMockUserWithID("15315090"));

        Assert.assertTrue(bookList.getReviews().size() > 0);
    }

}
