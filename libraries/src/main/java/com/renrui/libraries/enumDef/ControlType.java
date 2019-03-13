package com.renrui.libraries.enumDef;

import android.support.annotation.IntDef;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 基础类型
 */
public class ControlType {

    /**
     * textView
     */
    public static final int TEXTVIEW = 0;

    /**
     * editText
     */
    public static final int EDITTEXT = 1;

    /**
     * button
     */
    public static final int BUTTON = 2;

    /**
     * 其他
     */
    public static final int OTHER = 99;

    @IntDef({TEXTVIEW, EDITTEXT, BUTTON, OTHER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Def {
    }
}