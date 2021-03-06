package com.renrui.libraries.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * 输出日志 （只有调试模式下才会输出）
 */
public class Logger {

    public static boolean isDebug = false;

    public static void e(int value) {
        if (isDebug) {
            Log.e("xuelei", value + "");
        }
    }

    public static void e(String value) {
        if (isDebug && !TextUtils.isEmpty(value)) {
            Log.e("xuelei", value);
        }
    }

    public static void e(String tag, String value) {
        if (isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(value)) {
            Log.e(tag, value);
        }
    }

    public static void e(String tag, String value1, String value2) {
        if (isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(value1) && !TextUtils.isEmpty(value2)) {
            Log.e(tag, value1 + value2);
        }
    }

    public static void e(String tag, int value) {
        Log.e(tag, value + "");
    }

    public static void d(String tag, String value) {
        if (isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(value)) {
            Log.d(tag, value);
        }
    }

    public static void i(String tag, String value) {
        if (isDebug && TextUtils.isEmpty(tag) && !TextUtils.isEmpty(value)) {
            Log.e(tag, value);
        }
    }

    public static void SysOutPrint(String value) {
        if (isDebug && !TextUtils.isEmpty(value)) {
            System.out.print(value);
        }
    }

    public static void firstE(String value) {
        if (isDebug && !TextUtils.isEmpty(value)) {
            Log.e("first", value);
        }
    }

    public static void im(String tag, String value) {
        if (isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(value)) {
            Log.e(tag, "IM流程测试中:" + value);
        }
    }

    public static void im(String value) {
        if (isDebug && !TextUtils.isEmpty(value)) {
            Log.e("IM流程测试中:", value);
        }
    }
}