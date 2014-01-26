package com.lemoncog.blindreads.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Gaming on 23/01/14.
 */

@Root(name="GoodreadsResponse", strict = false)
public class BookList
{
    @ElementList(name="reviews")
    private List<Review> mReviews;

    public List<Review> getReviews() {
        return mReviews;
    }
}
