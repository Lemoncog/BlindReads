package com.lemoncog.blindreads.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Gaming on 23/01/14.
 */
@Root(strict = false)
public class Book
{
    @Element(name="title")
    private String mTitle;

    @Element(name="image_url")
    private String mImageURL;

    @Element(name="link")
    private String mLink;

    @Element(name="id", type=Integer.class)
    private int mBookID;

    @Element(name="isbn", required = false)
    private String mISBN;

    @Element(name="isbn13", required = false)
    private String mISBN13;

    @Element(name="average_rating")
    private float mAverageRating;

    @Element(name="ratings_count")
    private int mRatingsCount;

    public String getTitle() {
        return mTitle;
    }
}
