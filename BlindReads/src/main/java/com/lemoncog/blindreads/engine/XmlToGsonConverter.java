package com.lemoncog.blindreads.engine;

import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * Created by Gaming on 23/01/14.
 */
public class XmlToGsonConverter implements Converter
{
    @Override
    public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
        return null;
    }

    @Override
    public TypedOutput toBody(Object o) {
        return null;
    }
}
