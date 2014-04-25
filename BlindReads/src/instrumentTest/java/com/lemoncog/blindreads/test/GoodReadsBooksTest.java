package com.lemoncog.blindreads.test;

import android.test.ActivityTestCase;

import com.lemoncog.blindreads.GoodReadsEngine;
import com.lemoncog.blindreads.controllers.BookController;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.goodreads.GoodReadsService;
import com.lemoncog.blindreads.models.BookList;
import com.lemoncog.blindreads.test.factory.MockFactory;

import junit.framework.Assert;

import static org.mockito.Mockito.mock;

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
        IUserSupplier userSupplier = mock(IUserSupplier.class);

        BookController bookController = new BookController(userSupplier, GoodReadsEngine.provideRestAdapter(GoodReadsEngine.provideServer(), GoodReadsEngine.provideClient(), GoodReadsEngine.provideConverter()));

        BookList bookList = bookController.getUserBookList(MockFactory.getMockUserWithID("15315090"));

        Assert.assertTrue(bookList.getReviews().size() > 0);
    }

    public void testStoreUsersBookList()
    {
        GoodReadsService service = GoodReadsEngine.provideRestAdapter(GoodReadsEngine.provideServer(), GoodReadsEngine.provideClient(), GoodReadsEngine.provideConverter()).create(GoodReadsService.class);

        BookList bookList = service.usersBookList("15315090", GoodReadsEngine.provideAPIKey(), "2");

        Assert.assertTrue(bookList.getReviews().size() > 0);
    }
}
