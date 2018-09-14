package com.renrui.libraries.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class UtilitySharedPreferences {

    public static final String string_app_uuid = "app_uuid";

    public static void writeString(String name, String value) {
        try {
            SharedPreferences sharedPreferences = LibrariesCons.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(name, value);
            editor.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String readString(String name) {

        String value;
        try {
            SharedPreferences sharedPreferences = LibrariesCons.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
            value = sharedPreferences.getString(name, "");
        } catch (Exception ex) {
            ex.printStackTrace();
            value = "";
        }

        return value;
    }

    public static void writeInt(String name, int value) {
        try {
            SharedPreferences sharedPreferences = LibrariesCons.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(name, value);
            editor.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int readInt(String name, int defaultValue) {

        int value;
        try {
            SharedPreferences sharedPreferences = LibrariesCons.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
            value = sharedPreferences.getInt(name, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            value = -1;
        }

        return value;
    }

    public static int readInt(String name) {
        return readInt(name, 0);
    }

    public static void writeBoolean(String name, boolean value) {
        try {
            SharedPreferences sharedPreferences = LibrariesCons.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(name, value);
            editor.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean readBoolean(String name, boolean defaultValue) {

        boolean value;
        try {
            SharedPreferences sharedPreferences = LibrariesCons.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
            value = sharedPreferences.getBoolean(name, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            value = false;
        }

        return value;
    }

    public static boolean readBoolean(String name) {
        return readBoolean(name, false);
    }

    public static Object getObj(String name) {
        Object obj = null;

        try {
            String strObj = UtilitySharedPreferences.readString(name);

            if (!TextUtils.isEmpty(strObj)) {
                obj = LibUtility.deSerializationToObject(strObj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            obj = null;
        }

        return obj;
    }

    public void setObj(String name, Object obj) {
        try {
            String strObj = LibUtility.serializeToString(obj);

            if (!TextUtils.isEmpty(strObj)) {
                UtilitySharedPreferences.writeString(name, strObj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
