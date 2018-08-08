package com.renrui.libraries.util;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.renrui.libraries.interfaces.ITextviewClickable;

import java.util.List;

public class UtilityControl {

    /**
     * @param tv
     * @param text                       Text
     * @param hotWords              关键词
     * @param colorResourceID   关键词颜色
     * @param listener                  关键字点击回调
     */
    public static void setHotWordsText(TextView tv, CharSequence text, String hotWords, int colorResourceID, ITextviewClickable listener) {
        if (tv == null) {
            return;
        }

        try {
            SpannableStringBuilder textBuilder = new SpannableStringBuilder(text);
            ForegroundColorSpan hotWordsSpanColor;

            List<Integer> lis = LibUtility.getStrIndex(text + "", hotWords);
            for (int i = 0; i < lis.size(); i++) {
                // 关键字颜色
                hotWordsSpanColor = new ForegroundColorSpan(ContextCompat.getColor(LibrariesCons.getContext(), colorResourceID));
                textBuilder.setSpan(hotWordsSpanColor, lis.get(i), lis.get(i) + hotWords.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                // 点击事件
                if (listener != null)
                    textBuilder.setSpan(new SpanClickable(listener), lis.get(i), lis.get(i) + hotWords.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
     * @param text                        Text
     * @param hotWords              关键词
     * @param colorResourceID   关键词颜色
     */
    public static void setHotWordsText(TextView tv, CharSequence text, String hotWords, int colorResourceID) {
        setHotWordsText(tv, text, hotWords, colorResourceID, null);
    }
}
