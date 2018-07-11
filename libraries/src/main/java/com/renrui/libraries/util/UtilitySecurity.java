package com.renrui.libraries.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.renrui.libraries.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 安全方法
 */
public class UtilitySecurity {

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
     * @return 默认返回false
     */
    public static boolean getExtrasBoolean(Intent intent, String keyName) {
        return getExtrasBoolean(intent, keyName, false);
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
        if (view == null) {
            return;
        }

        try {
            if (view.getVisibility() != visibility) {
                view.setVisibility(visibility);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 空字符串隐藏
     */
    public static void resetVisibility(View view, String text) {
        if (view == null) {
            return;
        }

        resetVisibility(view, TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
    }

    public static void resetVisibility(View view, boolean isShow) {
        if (view == null) {
            return;
        }

        try {
            view.setVisibility(isShow ? View.VISIBLE : View.GONE);
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

    public static void setChecked(CheckBox chk, boolean value) {
        if (chk == null) {
            return;
        }

        try {
            chk.setChecked(value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setText(TextView tv, int text) {
        if (tv == null) {
            return;
        }

        setText(tv, text + "");
    }

    public static void setText(TextView tv, CharSequence text) {
        if (tv == null) {
            return;
        }

        try {
            if (!tv.getText().toString().equalsIgnoreCase(text.toString()))
                tv.setText(TextUtils.isEmpty(text) ? "" : text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param tv
     * @param resourceID
     */
    public static void setDrawableLeft(TextView tv, int resourceID) {
        if (tv == null) {
            return;
        }

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
        if (tv == null) {
            return;
        }

        try {
            Drawable rightDrawable = LibrariesCons.getContext().getResources().getDrawable(resourceID);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
            tv.setCompoundDrawables(null, null, rightDrawable, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 将焦点放在最后
     */
    public static void setLastSelection(EditText tv) {
        if (tv == null || tv.getText().length() == 0) {
            return;
        }

        try {
            tv.setSelection(tv.length());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void setEnabled(View tv, boolean enable) {
        if (tv == null) {
            return;
        }

        try {
            tv.setEnabled(enable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setClickable(View tv, boolean enable) {
        if (tv == null) {
            return;
        }

        try {
            tv.setClickable(enable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTextColor(TextView tv, int resourcesID) {
        if (tv == null || resourcesID == 0) {
            return;
        }

        try {
            tv.setTextColor(LibrariesCons.getContext().getResources().getColor(resourcesID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setBackgroundResource(View tv, int resoueceID) {
        if (tv == null) {
            return;
        }

        try {
            tv.setBackgroundResource(resoueceID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTextByStringSource(TextView tv, int resourceID) {
        if (tv == null) {
            return;
        }

        try {
            setText(tv, LibrariesCons.getContext().getString(resourceID));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setHtmlText(TextView tv, CharSequence text) {
        if (tv == null) {
            return;
        }

        try {
            tv.setText(TextUtils.isEmpty(text) ? "" : Html.fromHtml(text.toString()));
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
        if (tv == null) {
            return;
        }

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
        if (tv == null) {
            return;
        }

        if (defaultText == null) {
            defaultText = "";
        }

        try {
            tv.setText(TextUtils.isEmpty(text) ? defaultText : text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTextAppearance(TextView tv, int styleResourceID) {
        if (tv == null) {
            return;
        }

        try {
            tv.setTextAppearance(LibrariesCons.getContext(), styleResourceID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setTextSourceColor(TextView tv, int colorSourceId) {
        if (tv == null) {
            return;
        }

        try {
            tv.setTextColor(LibrariesCons.getContext().getResources().getColor(colorSourceId));
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

//            try {
//                StatisticsLogException exceptionModel = new StatisticsLogException();
//                if (cls != null) {
//                    exceptionModel.className = cls.getName();
//                }
//                exceptionModel.content = "getView null";
//                exceptionModel.lisActivity = ApplicationUtils.getActivitys(UtilityJobSecurity.context);
//                UtilityHttpLog.sendGetviewException(exceptionModel);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }

            return View.inflate(LibrariesCons.getContext(), R.layout.view_empty, null);
        } else {
            return convertView;
        }
    }

//    /**
//     * @param tv
//     * @param text            Text
//     * @param hotWords        关键词
//     * @param colorResourceID 关键词颜色
//     */
//    public void setHotWordsText(TextView tv, CharSequence text, String hotWords, int colorResourceID) {
//        if (tv == null) {
//            return;
//        }
//
//        try {
//            SpannableStringBuilder textBuilder = new SpannableStringBuilder(text);
//            ForegroundColorSpan hotWordsSpanColor;
//
//            List<Integer> lis = UtilityData.getStrIndex(text + "", hotWords);
//            for (int i = 0; i < lis.size(); i++) {
//                hotWordsSpanColor = new ForegroundColorSpan(ContextCompat.getColor(LibrariesCons.getContext(), colorResourceID));
//                textBuilder.setSpan(hotWordsSpanColor, lis.get(i), lis.get(i) + hotWords.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//
//            setText(tv, textBuilder);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

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
//
//    /**
//     * 50毫秒以后结束下拉状态
//     */
//    public void setListViewOnRefreshComplete(final PullToRefreshListView lvList) {
//        try {
//            new Handler().postDelayed(new Runnable() {
//                public void run() {
//                    try {
//                        lvList.onRefreshComplete();
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }, 50);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     * 延时100毫秒置顶
//     */
//    public void setListViewScrollTop(final PullToRefreshListView lvList) {
//        try {
//            new Handler().postDelayed(new Runnable() {
//                public void run() {
//                    if (lvList.getRefreshableView().getChildCount() > 0)
//                        lvList.getRefreshableView().setSelection(0);
//                }
//            }, 100);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}