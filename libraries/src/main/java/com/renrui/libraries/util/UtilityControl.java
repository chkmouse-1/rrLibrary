package com.renrui.libraries.util;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.renrui.libraries.enumDef.ClassType;
import com.renrui.libraries.enumDef.ControlType;
import com.renrui.libraries.interfaces.ITextviewClickable;
import com.renrui.libraries.model.SpanModel;

import java.util.List;

public class UtilityControl {

    /**
     * 可同时设置字体颜色、是否下划线和点击事件
     *
     * @param tv           TextView
     * @param text         要显示的全部文本
     * @param hotWordModel @see SpanModel
     * @param listener     点击监听
     */
    public static void setSpanText(TextView tv, CharSequence text, List<SpanModel> hotWordModel, ITextviewClickable listener) {
        if (tv == null || UtilitySecurity.isEmpty(text) || UtilitySecurity.isEmpty(hotWordModel)) {
            return;
        }
        try {
            SpannableStringBuilder textBuilder = new SpannableStringBuilder(text);
            ForegroundColorSpan hotWordsSpanColor;

            List<Integer> lis;
            for (int i = 0; i < hotWordModel.size(); i++) {
                lis = LibUtility.getStrIndex(text + "", hotWordModel.get(i).text);
                for (int j = 0; j < lis.size(); j++) {
                    // 点击事件
                    textBuilder.setSpan(new SpanClickable(listener, i, hotWordModel.get(i).isUnderline), lis.get(j), lis.get(j) + hotWordModel.get(i).text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    // 关键字颜色
                    hotWordsSpanColor = new ForegroundColorSpan(hotWordModel.get(i).color);
                    textBuilder.setSpan(hotWordsSpanColor, lis.get(j), lis.get(j) + hotWordModel.get(i).text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }

            tv.setMovementMethod(LinkMovementMethod.getInstance());
            tv.setHighlightColor(Color.TRANSPARENT);
            tv.setText(textBuilder);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param tv
     * @param text            Text
     * @param arrHotWords     关键词
     * @param colorResourceID 关键词颜色
     * @param listener        关键字点击回调
     */
    public static void setHotWordsText(TextView tv, CharSequence text, String[] arrHotWords, int colorResourceID, ITextviewClickable listener) {
        if (tv == null || UtilitySecurity.isEmpty(text) || UtilitySecurity.isEmpty(arrHotWords))
            return;

        try {
            SpannableStringBuilder textBuilder = new SpannableStringBuilder(text);
            ForegroundColorSpan hotWordsSpanColor;

            List<Integer> lis;
            for (int i = 0; i < arrHotWords.length; i++) {
                lis = LibUtility.getStrIndex(text + "", arrHotWords[i]);
                for (int j = 0; j < lis.size(); j++) {
                    // 点击事件
                    if (listener != null)
                        textBuilder.setSpan(new SpanClickable(listener, i), lis.get(j), lis.get(j) + arrHotWords[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    // 关键字颜色
                    hotWordsSpanColor = new ForegroundColorSpan(ContextCompat.getColor(LibrariesCons.getContext(), colorResourceID));
                    textBuilder.setSpan(hotWordsSpanColor, lis.get(j), lis.get(j) + arrHotWords[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }

            tv.setMovementMethod(LinkMovementMethod.getInstance());
            tv.setHighlightColor(Color.TRANSPARENT);
            tv.setText(textBuilder);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param tv
     * @param text            Text
     * @param hotWords        关键词
     * @param colorResourceID 关键词颜色
     * @param listener        关键字点击回调
     */
    public static void setHotWordsText(TextView tv, CharSequence text, String hotWords, int colorResourceID, ITextviewClickable listener) {
        setHotWordsText(tv, text, new String[]{hotWords}, colorResourceID, listener);
    }

    /**
     * @param tv
     * @param text            Text
     * @param hotWords        关键词
     * @param colorResourceID 关键词颜色
     */
    public static void setHotWordsText(TextView tv, CharSequence text, String hotWords, int colorResourceID) {
        setHotWordsText(tv, text, hotWords, colorResourceID, null);
    }

    /**
     * @param tv
     * @param text            Text
     * @param arrHotWords     关键词
     * @param colorResourceID 关键词颜色
     */
    public static void setHotWordsText(TextView tv, CharSequence text, String[] arrHotWords, int colorResourceID) {
        setHotWordsText(tv, text, arrHotWords, colorResourceID, null);
    }

    public static int getControlType(Object obj) {
        int classType;

        try {
            // TextView
            if (obj instanceof TextView) {
                classType = ControlType.TEXTVIEW;
            }
            // EditText
            else if (obj instanceof EditText) {
                classType = ControlType.EDITTEXT;
            }
            // Button
            else if (obj instanceof Button) {
                classType = ControlType.BUTTON;
            } else {
                classType = ClassType.Other;
            }
        } catch (Exception ex) {
            classType = ClassType.Other;
        }

        return classType;
    }
}
