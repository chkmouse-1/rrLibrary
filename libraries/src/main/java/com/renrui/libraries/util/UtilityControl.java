package com.renrui.libraries.util;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.renrui.libraries.enumDef.ClassType;
import com.renrui.libraries.enumDef.ControlType;
import com.renrui.libraries.interfaces.ITextviewClickable;
import com.renrui.libraries.model.SpanModel;

import java.util.ArrayList;
import java.util.List;

public class UtilityControl {

    /**
     * 可同时设置字体颜色、是否下划线、是否加粗、文字大小和点击事件
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

            List<Integer> lis;
            for (int i = 0; i < hotWordModel.size(); i++) {
                lis = LibUtility.getStrIndex(text + "", hotWordModel.get(i).text);
                for (int j = 0; j < lis.size(); j++) {
                    textBuilder.setSpan(new SpanClickable(listener, i, hotWordModel.get(i).isUnderline, hotWordModel.get(i).color, hotWordModel.get(i).isBold, hotWordModel.get(i).textSize),
                            lis.get(j),
                            lis.get(j) + hotWordModel.get(i).text.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
     * @param isBold          关键词是否加粗
     * @param textSize        关键字文字大小
     * @param listener        关键字点击回调
     */
    public static void setHotWordsText(TextView tv, CharSequence text, String[] arrHotWords, int colorResourceID, boolean isBold, float textSize, ITextviewClickable listener) {
        if (tv == null || UtilitySecurity.isEmpty(text) || UtilitySecurity.isEmpty(arrHotWords)) {
            return;
        }
        try {
            List<SpanModel> hotWordModels = new ArrayList<>();
            SpanModel spanModel;
            for (String arrHotWord : arrHotWords) {
                spanModel = new SpanModel();
                spanModel.text = arrHotWord;
                spanModel.isUnderline = false;
                spanModel.color = ContextCompat.getColor(LibrariesCons.getContext(), colorResourceID);
                spanModel.isBold = isBold;
                spanModel.textSize = textSize;
                hotWordModels.add(spanModel);
            }
            if (listener != null) {
                setSpanText(tv, text, hotWordModels, listener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param tv
     * @param text            Text
     * @param arrHotWords     关键词
     * @param colorResourceID 关键词颜色
     * @param isBold          关键词是否加粗
     * @param listener        关键字点击回调
     */
    public static void setHotWordsText(TextView tv, CharSequence text, String[] arrHotWords, int colorResourceID, boolean isBold, ITextviewClickable listener) {
        setHotWordsText(tv, text, arrHotWords, colorResourceID, isBold, -1, listener);
    }

    /**
     * @param tv
     * @param text            Text
     * @param arrHotWords     关键词
     * @param colorResourceID 关键词颜色
     * @param listener        关键字点击回调
     */
    public static void setHotWordsText(TextView tv, CharSequence text, String[] arrHotWords, int colorResourceID, ITextviewClickable listener) {
        setHotWordsText(tv, text, arrHotWords, colorResourceID, false, listener);
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
