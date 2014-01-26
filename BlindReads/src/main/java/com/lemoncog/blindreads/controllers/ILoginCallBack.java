package com.lemoncog.blindreads.controllers;

/**
 * Created by Gaming on 25/01/14.
 */
public interface ILoginCallBack {
    void userAuthorizeSuccess();

    void requestWebView(String url);
}
