package com.renrui.libraries.enumDef;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 网络请求类型：Get, Post
 */
public class HttpRequestType {

    /**
     * Get
     */
    public static final int Get = 0;

    /**
     * Post
     */
    public static final int Post = 1;

    @IntDef({Get, Post})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Def {
    }
}