package com.renrui.libraries.enumDef;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 网络环境： 线上，预发布，测试
 */
public class NetWorkType {

    /**
     * 测试环境
     */
    public static final int Test = 0;

    /**
     * 预发布
     */
    public static final int Priview = 1;

    /**
     * 线上
     */
    public static final int Release = 2;

    @IntDef({Test, Priview, Release})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Def {
    }
}