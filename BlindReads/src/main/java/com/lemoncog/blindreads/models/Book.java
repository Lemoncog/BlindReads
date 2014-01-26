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

    public String getTitle() {
        return mTitle;
    }
}
