package com.renrui.libraries.enumDef;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 基础类型
 */
public class ClassType {

    /**
     * serializable
     */
    public static final int SERIALIZABLE = 0;

    /**
     * file
     */
    public static final int FILE =1;

    /**
     * string
     */
    public static final int STRING = 2;

    /**
     * int
     */
    public static final int INT = 3;

    /**
     * boolean
     */
    public static final int BOOLEAN = 4;

    /**
     * long
     */
    public static final int LONG = 5;
    /**
     * float
     */
    public static final int FLOAT = 6;
    /**
     * double
     */
    public static final int DOUBLE = 7;

    /**
     * arraryList
     */
    public static final int ARRARYLIST = 8;
    /**
     * arraryList<String>
     */
    public static final int ARRARYLIST_STRING = 9;
    /**
     * arraryList<integer>
     */
    public static final int ARRARYLIST_INTEGER = 10;
    /**
     * 其他
     */
    public static final int Other = 99;

    @IntDef({SERIALIZABLE,FILE,STRING,INT,BOOLEAN,LONG,FLOAT,DOUBLE,ARRARYLIST,ARRARYLIST_STRING,ARRARYLIST_INTEGER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Def {
    }
}