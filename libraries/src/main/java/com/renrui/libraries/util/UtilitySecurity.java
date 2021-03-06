package com.renrui.libraries.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.renrui.libraries.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 安全方法
 */
public class UtilitySecurity {

    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str.trim());
    }

    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str.toString().trim());
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty() || map.size() <= 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }

    public static boolean isEmpty(TextView tv) {
        return isEmpty(getText(tv));
    }

    public static boolean isEmpty(EditText et) {
        return isEmpty(getText(et));
    }

    public static boolean equals(String a, String b) {
        return TextUtils.equals(a, b);
    }

    public static boolean contains(String sourc, String tag) {
        if (isEmpty(sourc) || isEmpty(tag) || sourc.length() < tag.length()) return false;
        return sourc.contains(tag);
    }

    public static boolean contains(List<Integer> list, int tag) {
        if (isEmpty(list)) return false;
        try {
            for (Integer value : list) {
                if (value == tag) return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean contains(List<String> list, String tag) {
        if (isEmpty(list) || isEmpty(tag)) return false;
        try {
            for (String str : list) {
                if (equals(str, tag)) {
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean contains(Set<String> set, String tag) {
        if (isEmpty(set) || isEmpty(tag)) return false;
        try {
            for (String obg : set) {
                if (equals(obg, tag)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean contains(HashMap<String, ?> maps, String tag) {
        if (isEmpty(maps) || isEmpty(tag)) return false;
        Set<String> keys = null;
        try {
            keys = maps.keySet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contains(keys, tag);
    }


    public static <T> boolean contains(Collection<T> collection, T obj) {
        if (collection == null || collection.size() == 0) {
            return false;
        }
        try {
            return collection.contains(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 数组合并
     * 需要使用对象类型
     *
     * @param first
     * @param rest
     * @param <T>
     * @return
     */
    public static <T> T[] concatAll(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    // 判断String是否以字符串开头
    public static boolean startWith(String source, String tag) {
        boolean isStar = false;
        if (isEmpty(source) || isEmpty(tag) || getLength(source) < getLength(tag)) return isStar;
        isStar = source.startsWith(tag);
        return isStar;
    }

    // 判断String是否以字符串结尾
    public static boolean endWith(String source, String tag) {
        boolean isEnd = false;
        if (isEmpty(source) || isEmpty(tag) || getLength(source) < getLength(tag)) return isEnd;
        isEnd = source.endsWith(tag);
        return isEnd;
    }

    // 获取集合的长度
    public static int size(Collection<?> data) {
        if (data == null || data.isEmpty()) return 0;
        try {
            return data.size();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // 获取hashMap的长度
    public static int size(HashMap<?, ?> map) {
        if (isEmpty(map)) return 0;
        try {
            return map.size();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void remove(List<Integer> list, int tag) {
        remove(list, tag, false);
    }

    /**
     * 如果isObject为true 则删除tag表示需要删除的对象，否则表示需要删除的下标
     *
     * @param list
     * @param tag
     * @param isObject
     * @return
     */
    public static void remove(List<Integer> list, int tag, boolean isObject) {
        if (isEmpty(list)) return;
        try {
            if (isObject) {
                list.remove((Integer) tag);
            } else {
                list.remove(tag);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null && b == null)
            return true;

        if (a == null && b != null)
            return false;

        if (a != null && b == null)
            return false;

        try {
            return TextUtils.equals(a.toLowerCase(), b.toLowerCase());
        } catch (Exception ex) {
            return false;
        }
    }

    public static void putExtras(Bundle bundle, String keyName, Object keyValue) {
        if (bundle == null || TextUtils.isEmpty(keyName) || keyValue == null) {
            return;
        }
        try {
            if (keyValue instanceof String) {
                String value = keyValue.toString();
                if (!TextUtils.isEmpty(value) && !TextUtils.isEmpty(value.trim())) {
                    bundle.putString(keyName, value);
                }
            }
            // int
            else if (keyValue instanceof Integer) {
                bundle.putInt(keyName, ((Integer) keyValue).intValue());
            }
            // float
            else if (keyValue instanceof Float) {
                bundle.putFloat(keyName, Float.parseFloat(keyValue.toString()));
            }
            // double
            else if (keyValue instanceof Double) {
                bundle.putDouble(keyName, Double.parseDouble(keyValue.toString()));
            }
            // serializable
            else if (keyValue instanceof Serializable) {
                bundle.putSerializable(keyName, (Serializable) keyValue);
            }
            // arraryList<Integer>
            else if (UtilityClassInfo.isArraryInteger(keyValue)) {
                bundle.putIntegerArrayList(keyName, (ArrayList<Integer>) keyValue);
            }
            // arraryList<string>
            else if (UtilityClassInfo.isArraryString(keyValue)) {
                bundle.putStringArrayList(keyName, (ArrayList<String>) keyValue);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void putExtras(Intent intent, String keyName, Object keyValue) {
        if (intent == null || TextUtils.isEmpty(keyName) || keyValue == null)
            return;

        try {
            // string
            if (keyValue instanceof String) {
                String value = keyValue.toString();
                if (!TextUtils.isEmpty(value) && !TextUtils.isEmpty(value.trim())) {
                    intent.putExtra(keyName, keyValue.toString());
                }
            }
            // int
            else if (keyValue instanceof Integer) {
                intent.putExtra(keyName, ((Integer) keyValue).intValue());
            }
            // float
            else if (keyValue instanceof Float) {
                intent.putExtra(keyName, Float.parseFloat(keyValue.toString()));
            }
            // double
            else if (keyValue instanceof Double) {
                intent.putExtra(keyName, Double.parseDouble(keyValue.toString()));
            }
            // serializable
            else if (keyValue instanceof Serializable) {
                intent.putExtra(keyName, (Serializable) keyValue);
            }
            // arraryList<Integer>
            else if (UtilityClassInfo.isArraryInteger(keyValue)) {
                intent.putIntegerArrayListExtra(keyName, (ArrayList<Integer>) keyValue);
            }
            // arraryList<string>
            else if (UtilityClassInfo.isArraryString(keyValue)) {
                intent.putStringArrayListExtra(keyName, (ArrayList<String>) keyValue);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getExtrasString(Bundle bd, String keyName, String defaultValue) {
        String value = "";

        if (TextUtils.isEmpty(defaultValue)) {
            defaultValue = "";
        }

        if (bd == null || TextUtils.isEmpty(keyName)) {
            return defaultValue;
        }

        try {
            value = bd.getString(keyName, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value.trim();
    }

    public static String getExtrasString(Intent intent, String keyName, String defaultValue) {
        String value = "";

        if (TextUtils.isEmpty(defaultValue)) {
            defaultValue = "";
        }

        if (intent == null || intent.getExtras() == null || TextUtils.isEmpty(keyName)) {
            return defaultValue;
        }

        try {
            value = getExtrasString(intent.getExtras(), keyName, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value.trim();
    }

    /**
     * @return 默认返回空字符串
     */
    public static String getExtrasString(Intent intent, String keyName) {
        return getExtrasString(intent, keyName, "");
    }

    /**
     * @return 默认返回0f
     */
    public static double getExtrasDouble(Intent intent, String keyName) {
        return getExtrasDouble(intent, keyName, 0d);
    }

    public static double getExtrasDouble(Intent intent, String keyName, double defaultValue) {
        double value = 0f;

        if (intent == null || intent.getExtras() == null || TextUtils.isEmpty(keyName)) {
            return defaultValue;
        }

        try {
            value = intent.getDoubleExtra(keyName, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    /**
     * @return 默认返回0f
     */
    public static float getExtrasFloat(Intent intent, String keyName) {
        return getExtrasFloat(intent, keyName, 0f);
    }

    public static float getExtrasFloat(Intent intent, String keyName, float defaultValue) {
        float value = 0f;

        if (intent == null || intent.getExtras() == null || TextUtils.isEmpty(keyName)) {
            return defaultValue;
        }

        try {
            value = intent.getFloatExtra(keyName, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    public static long getExtrasLong(Bundle bd, String keyName) {
        return getExtrasLong(bd, keyName, 0);
    }

    public static long getExtrasLong(Intent intent, String keyName) {
        return getExtrasLong(intent, keyName, 0);
    }

    public static long getExtrasLong(Bundle bd, String keyName, long defaultValue) {
        long value = 0l;

        if (bd == null || bd.size() == 0 || TextUtils.isEmpty(keyName)) {
            return defaultValue;
        }

        try {
            value = bd.getLong(keyName, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    public static long getExtrasLong(Intent intent, String keyName, long defaultValue) {
        long value = 0l;

        if (intent == null || intent.getExtras() == null || TextUtils.isEmpty(keyName)) {
            return defaultValue;
        }

        try {
            value = intent.getLongExtra(keyName, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    public static int getExtrasInt(Intent intent, String keyName, int defaultValue) {
        int value = 0;

        if (intent == null || intent.getExtras() == null || TextUtils.isEmpty(keyName)) {
            return defaultValue;
        }

        try {
            value = intent.getExtras().getInt(keyName, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    /**
     * @return 默认返回0
     */
    public static int getExtrasInt(Intent intent, String keyName) {
        return getExtrasInt(intent, keyName, 0);
    }

    /**
     * @return 默认返回0
     */
    public static int getExtrasInt(Bundle db, String keyName) {
        if (db == null) {
            return 0;
        } else {
            return db.getInt(keyName, 0);
        }
    }

    public static boolean getExtrasBoolean(Bundle db, String keyName) {
        return getExtrasBoolean(db, keyName, false);
    }

    public static boolean getExtrasBoolean(Bundle db, String keyName, boolean defaultValue) {
        boolean value = false;

        if (db == null || TextUtils.isEmpty(keyName)) {
            return defaultValue;
        }

        try {
            value = db.getBoolean(keyName, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    public static boolean getExtrasBoolean(Intent intent, String keyName, boolean defaultValue) {
        boolean value = false;

        if (intent == null || intent.getExtras() == null || TextUtils.isEmpty(keyName)) {
            return defaultValue;
        }

        try {
            value = intent.getExtras().getBoolean(keyName, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return value;
    }

    /**
     * 可能返回null
     */
    public static boolean getExtrasBoolean(Intent intent, String keyName) {
        return getExtrasBoolean(intent, keyName, false);
    }

    public static ArrayList<String> getStringArrayList(Bundle bd, String keyName) {
        ArrayList<String> arrayList = null;

        if (bd == null || TextUtils.isEmpty(keyName)) {
            return arrayList;
        }

        try {
            arrayList = bd.getStringArrayList(keyName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return arrayList;
    }

    /**
     * 可能返回null
     */
    public static ArrayList<?> getStringArrayList(Intent intent, String keyName) {
        if (intent == null || intent.getExtras() == null || intent.getExtras().size() == 0)
            return null;

        return getStringArrayList(intent.getExtras(), keyName);
    }

    /**
     * 可能返回null
     */
    public static ArrayList<Integer> getIntegerArrayList(Bundle bd, String keyName) {
        ArrayList<Integer> arrayList = null;

        if (bd == null || TextUtils.isEmpty(keyName)) {
            return arrayList;
        }

        try {
            arrayList = bd.getIntegerArrayList(keyName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return arrayList;
    }

    /**
     * 可能返回null
     */
    public static ArrayList<Integer> getIntegerArrayList(Intent intent, String keyName) {
        if (intent == null || intent.getExtras() == null || intent.getExtras().size() == 0)
            return null;

        return getIntegerArrayList(intent.getExtras(), keyName);
    }

    public static <T> T getExtrasSerializable(Bundle db, String keyName) {
        if (db == null || db.size() == 0 || TextUtils.isEmpty(keyName)) {
            return null;
        }

        T tModel = null;

        try {
            tModel = (T) db.getSerializable(keyName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tModel;
    }

    public static <T> T getExtrasSerializable(Intent intent, String keyName) {
        if (intent == null || intent.getExtras() == null || TextUtils.isEmpty(keyName)) {
            return null;
        }

        T tModel = null;

        try {
            tModel = (T) intent.getExtras().getSerializable(keyName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tModel;
    }

    public static <T> T getExtrasParcelable(Intent intent, String keyName) {
        if (intent == null || intent.getExtras() == null || TextUtils.isEmpty(keyName)) {
            return null;
        }

        T tModel = null;

        try {
            tModel = intent.getExtras().getParcelable(keyName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tModel;
    }

    public static void resetVisibility(View view, int visibility) {
        if (view == null)
            return;

        try {
            if (view.getVisibility() != visibility)
                view.setVisibility(visibility);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 空字符串隐藏
     */
    public static void resetVisibility(View view, String text) {
        if (view == null)
            return;

        resetVisibility(view, !TextUtils.isEmpty(text));
    }

    public static void resetVisibility(View view, boolean isShow) {
        if (view == null)
            return;

        try {
            resetVisibility(view, isShow ? View.VISIBLE : View.GONE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void resetVisibility(boolean isShow, View... views) {
        if (views == null) return;
        try {
            for (View view : views) {
                if (view == null) {
                    continue;
                }
                resetVisibility(view, isShow);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void resetVisibility(int visibility, View... views) {
        if (visibility < 0 || views == null) {
            return;
        }
        try {
            for (View view : views) {
                if (view == null) continue;
                resetVisibility(view, visibility);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isVisiable(View view) {
        if (view == null)
            return false;
        try {
            return view.getVisibility() == View.VISIBLE;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static int getFirstVisiblePosition(AbsListView absListView) {
        if (null == absListView) return -1;
        try {
            return absListView.getFirstVisiblePosition();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static void setTranslationX(View view, float translationX) {
        if (null == view) return;
        try {
            view.setTranslationX(translationX);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTranslationY(View view, float translationY) {
        if (null == view) return;
        try {
            view.setTranslationY(translationY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setHint(TextView tv, CharSequence text) {
        if (tv == null) {
            return;
        }

        try {
            tv.setHint(TextUtils.isEmpty(text) ? "" : text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 设置文本框中字符串转换成密文的替代字符，比如将密码用小圆点替换
     *
     * @param tv
     * @param method
     */
    public static void setTransformationMethod(TextView tv, TransformationMethod method) {
        if (null == tv || null == method) return;
        try {
            tv.setTransformationMethod(method);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setChecked(CheckBox chk, boolean value) {
        if (chk == null)
            return;

        try {
            chk.setChecked(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setChecked(CompoundButton compoundButton, boolean value) {
        if (compoundButton == null) return;
        try {
            compoundButton.setChecked(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isChecked(CompoundButton compoundButton) {
        if (null == compoundButton) return false;
        try {
            return compoundButton.isChecked();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void setSelected(TextView tv, boolean checked) {
        if (null == tv) return;
        try {
            tv.setSelected(checked);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setSelected(boolean checked, TextView... textViews) {
        if (null == textViews) return;
        try {
            for (TextView tv : textViews) {
                if (null == tv) continue;
                setSelected(tv, checked);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setGravity(TextView tv, int gravity) {
        if (null == tv || gravity <= 0) return;
        try {
            tv.setGravity(gravity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setText(TextView tv, int text) {
        if (tv == null)
            return;

        setText(tv, text + "");
    }

    public static void setTextByResource(TextView tv, int resourceID) {
        if (tv == null)
            return;

        try {
            setText(tv, LibrariesCons.getContext().getString(resourceID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setText(TextView tv, CharSequence text) {
        if (tv == null)
            return;

        try {
//            if (!TextUtils.equals(tv.getText(), text))
            tv.setText(TextUtils.isEmpty(text) ? "" : text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setFocusable(TextView et, boolean isFocusable) {
        if (et == null) return;
        try {
            et.setFocusable(isFocusable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setFocusable(boolean isFocusable, TextView... ets) {
        if (ets == null) return;
        for (TextView et : ets) {
            if (et == null) continue;
            setFocusable(et, isFocusable);
        }
    }

    public static boolean isFocusable(TextView textView) {
        if (null == textView) return false;
        try {
            return textView.isFocusable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void setSelectAllOnFocus(boolean isSelectAll, TextView... textViews) {
        if (null == textViews) return;
        try {
            for (TextView tv : textViews) {
                if (null == tv) continue;
                tv.setSelectAllOnFocus(isSelectAll);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getText(TextView tv) {
        if (tv == null)
            return "";

        try {
            return tv.getText().toString().trim();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static String getText(TabLayout.Tab tab) {
        if (null == tab) return null;
        try {
            return tab.getText().toString().trim();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getHint(TextView tv) {
        if (null == tv) return null;
        try {
            return tv.getHint().toString().trim();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static int getLength(String value) {
        if (isEmpty(value)) return 0;
        try {
            return value.length();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int getLength(TextView tv) {
        if (tv == null) return 0;
        try {
            return getText(tv).length();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void clearText(EditText... ets) {
        if (null == ets) return;
        try {
            for (EditText et : ets) {
                if (null == et) continue;
                et.getText().clear();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void clearText(TextView... tvs) {
        if (tvs == null) return;
        try {
            for (TextView tv : tvs) {
                setText(tv, "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param tv
     * @param resourceID
     */
    public static void setDrawableLeft(TextView tv, int resourceID) {
        if (tv == null)
            return;

        try {
            Drawable rightDrawable = LibrariesCons.getContext().getResources().getDrawable(resourceID);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
            tv.setCompoundDrawables(rightDrawable, null, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param tv
     * @param resourceID
     */
    public static void setDrawableRight(TextView tv, int resourceID) {
        if (tv == null)
            return;

        try {
            Drawable rightDrawable = LibrariesCons.getContext().getResources().getDrawable(resourceID);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
            tv.setCompoundDrawables(null, null, rightDrawable, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param tv
     * @param resourceID
     */
    public static void setDrawableTop(TextView tv, int resourceID) {
        if (tv == null)
            return;

        try {
            Drawable topDrawable = LibrariesCons.getContext().getResources().getDrawable(resourceID);
            topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
            tv.setCompoundDrawables(null, topDrawable, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void requestFocus(EditText et) {
        if (et == null)
            return;

        try {
            et.requestFocus();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setFocusableInTouchMode(EditText et, boolean isMode) {
        if (null == et) return;
        try {
            et.setFocusableInTouchMode(isMode);
        } catch (Exception ex) {
            et.cancelLongPress();
        }
    }

    public static void clearFocus(EditText et) {
        if (et == null)
            return;

        try {
            et.clearFocus();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 将焦点放在最后
     */
    public static void setLastSelection(EditText tv) {
        if (tv == null || tv.getText().length() == 0)
            return;

        try {
            tv.setSelection(tv.length());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setEnabled(View tv, boolean enable) {
        if (tv == null)
            return;

        try {
            tv.setEnabled(enable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setMaxLines(TextView tv, int maxLine) {
        if (null == tv || maxLine <= 0) {
            return;
        }
        try {
            tv.setMaxLines(maxLine);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setClickable(View tv, boolean enable) {
        if (tv == null)
            return;

        try {
            tv.setClickable(enable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTextColor(TextView tv, int resourcesID) {
        if (tv == null || resourcesID == 0)
            return;

        try {
            tv.setTextColor(LibrariesCons.getContext().getResources().getColor(resourcesID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTextSize(TextView tv, int size) {
        if (tv == null || size <= 0) {
            return;
        }
        try {
            tv.setTextSize(size);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTextIsSelectable(TextView tv, boolean isSelectable) {
        if (null == tv) return;
        try {
            tv.setTextIsSelectable(isSelectable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTextIsSelectable(boolean isSelectable, TextView... textViews) {
        if (null == textViews) return;
        try {
            for (TextView tv : textViews) {
                if (null == tv) continue;
                setTextIsSelectable(tv, isSelectable);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 添加过滤
     *
     * @param tv
     * @param inputFilters
     */
    public static void setFilters(TextView tv, InputFilter[] inputFilters) {
        if (null == tv || isEmpty(inputFilters)) {
            return;
        }
        try {
            tv.setFilters(inputFilters);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 判断是否获取到焦点
     *
     * @param et
     * @return
     */
    public static boolean hasFocus(EditText et) {
        if (null == et) {
            return false;
        }
        try {
            return et.hasFocus();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void setBackground(View view, Drawable drawable) {
        if (null == view || null == drawable) return;
        try {
            view.setBackground(drawable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setBackgroundResource(View tv, int resoueceID) {
        if (tv == null)
            return;

        try {
            tv.setBackgroundResource(resoueceID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if (null == view || null == drawable) return;
        try {
            view.setBackgroundDrawable(drawable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setBackgroundColor(View view, int color) {
        if (view == null || color <= 0) return;
        try {
            view.setBackgroundColor(color);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setImageResource(ImageView iv, int resoueceID) {
        if (iv == null)
            return;

        try {
            iv.setImageResource(resoueceID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTextByStringSource(TextView tv, int resourceID) {
        if (tv == null)
            return;

        try {
            setText(tv, LibrariesCons.getContext().getString(resourceID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setHtmlText(TextView tv, CharSequence text) {
        if (tv == null)
            return;

        try {
            tv.setText(TextUtils.isEmpty(text) ? "" : Html.fromHtml(text.toString()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void selectAll(EditText et) {
        if (null == et) return;
        try {
            et.selectAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 内容为空时，隐藏TextView
     *
     * @param tv 内容
     */
    public static void setTextEmptyIsInvisible(TextView tv, CharSequence text) {
        if (tv == null)
            return;

        try {
            tv.setText(TextUtils.isEmpty(text) ? "" : text);

            if (TextUtils.isEmpty(text)) {
                resetVisibility(tv, View.INVISIBLE);
            } else {
                resetVisibility(tv, View.VISIBLE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 内容为空时，隐藏TextView
     *
     * @param tv 内容
     */
    public static void setTextEmptyIsGone(TextView tv, CharSequence text) {
        setText(tv, text, true);
    }

    /**
     * @param tv          TextView控件
     * @param text        内容
     * @param emptyIsGone 为空时，是否隐藏
     */
    public static void setText(TextView tv, CharSequence text, boolean emptyIsGone) {
        if (tv == null)
            return;

        try {
            tv.setText(TextUtils.isEmpty(text) ? "" : text);

            if (TextUtils.isEmpty(text) && emptyIsGone) {
                resetVisibility(tv, View.GONE);
            } else {
                resetVisibility(tv, View.VISIBLE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setText(TextView tv, String text, String defaultText) {
        if (tv == null)
            return;

        if (defaultText == null)
            defaultText = "";

        try {
            tv.setText(TextUtils.isEmpty(text) ? defaultText : text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTextAppearance(TextView tv, int styleResourceID) {
        if (tv == null)
            return;

        try {
            tv.setTextAppearance(LibrariesCons.getContext(), styleResourceID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTextSourceColor(TextView tv, int colorSourceId) {
        if (tv == null)
            return;

        try {
            tv.setTextColor(LibrariesCons.getContext().getResources().getColor(colorSourceId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setAlpha(View view, float alpha) {
        if (view == null)
            return;

        try {
            view.setAlpha(alpha);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 设置edittext 密码格式
     */
    public static void setEditTextTypePassword(EditText et) {

        if (et == null)
            return;

        try {
            et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 设置edittext 普通格式
     */
    public static void setEditTextTypeNormal(EditText et) {
        if (et == null)
            return;

        try {
            et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setSelection(EditText et, int index) {
        if (et == null || TextUtils.isEmpty(et.getText().toString()) || et.getText().toString().length() < index - 1) {
            return;
        }

        try {
            et.setSelection(index);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setSelection(ListView lv, int position) {
        if (lv == null || position < 0) {
            return;
        }

        try {
            lv.setSelection(position);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setRefreshing(SwipeRefreshLayout srl, boolean refreshing) {
        if (srl == null)
            return;

        try {
            srl.setRefreshing(refreshing);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setLayoutParams(View view, ViewGroup.LayoutParams params) {
        if (view == null || params == null)
            return;

        try {
            view.setLayoutParams(params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setProgress(ProgressBar progressBar, int value) {
        if (progressBar == null)
            return;

        try {
            progressBar.setProgress(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void dialogDismiss(Dialog dialog) {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }

        try {
            dialog.dismiss();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void dialogShow(Dialog dialog) {
        if (dialog == null || dialog.isShowing()) {
            return;
        }

        try {
            dialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void startActivity(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }

        try {
            context.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void startActivity(Activity activity, Intent intent) {
        if (activity == null || intent == null) {
            return;
        }

        try {
            activity.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void removeCallbacksAndMessages(Handler mHandler) {
        try {
            if (mHandler != null) {
                mHandler.removeCallbacksAndMessages(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static View getView(View convertView, Class cls) {
        if (convertView == null) {
            return View.inflate(LibrariesCons.getContext(), R.layout.view_empty, null);
        } else {
            return convertView;
        }
    }

    /**
     * 安全销毁webview
     * （setBuiltInZoomControls(true) 后，用户一旦触摸屏幕，就会出现缩放控制图标。如果图标自动消失前退出当前Activity的话 就可能崩溃）
     */
    public void x5WebViewDestroy(final WebView webView) {
        try {
            if (webView != null) {

                UtilitySecurity.resetVisibility(webView, View.GONE);
                webView.stopLoading();
                webView.removeAllViews();

                // 延时销毁
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            webView.destroy();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }, 100);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 是否最底部
     */
    public static boolean isBottom(BaseAdapter adapter, ListView listView) {

        boolean value;

        try {
            final int itemCount = adapter.getCount();
            final int lastVisibleItemPosition = listView.getLastVisiblePosition();
            value = itemCount < (lastVisibleItemPosition + 3);
        } catch (Exception ex) {
            ex.printStackTrace();
            value = false;
        }

        return value;
    }

    /**
     * 添加下划线
     */
    public void addUnderline(TextView textView) {
        if (textView == null) {
            return;
        }
        try {
            textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加设置日历时间
     */
    public static void setTime(Calendar calendar, Date date) {
        if (null == calendar || date == null) return;
        try {
            calendar.setTime(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setItemChecked(AbsListView absListView, int position, boolean isChecked) {
        if (null == absListView || position < 0) return;
        try {
            absListView.setItemChecked(position, isChecked);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void smoothScrollToPositionFromTop(AbsListView absListView, int position, int offset) {
        if (absListView == null || position < 0 && offset < 0) {
            return;
        }
        try {
            absListView.smoothScrollToPositionFromTop(position, offset);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}