package com.lemoncog.blindreads.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Gaming on 23/01/14.
 */
@Root(strict = false)
public class Review {

    @Element(name="book")
    private Book mBook;

    public Book getBook() {
        return mBook;
    }
}
