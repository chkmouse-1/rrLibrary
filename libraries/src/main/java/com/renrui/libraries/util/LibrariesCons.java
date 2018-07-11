package com.renrui.libraries.util;

import android.content.Context;

public class LibrariesCons {

    /**
     * 登录加密用
     */
    public static String AboutLoginPublicKey = "";
    public static void setAboutLoginPublicKey(String value) {
        AboutLoginPublicKey = value;
    }

    /**
     * 网络请求超时重试次数
     */
    public static final int httpRetryCounts = 3;

    /**
     * 普通网络请求超时20秒
     */
    public static final int httpTimeout = 20 * 1000;

    /**
     * 上传图片网络请求超时50秒
     */
    public static final int uploadFilehttpTimeout = 50 * 1000;

    /**
     * 网络请求最大并发
     */
    public static final int httpMaxConnections = 20;

    private static Context mContext;

    public static void setContext(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}