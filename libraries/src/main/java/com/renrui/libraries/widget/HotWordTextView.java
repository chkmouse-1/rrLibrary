package com.renrui.libraries.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;

import com.renrui.libraries.interfaces.IHotWordTextViewListener;
import com.renrui.libraries.model.strand.NumberHotWordModel;

import java.util.ArrayList;

/**
 * 热词textview
 */
public class HotWordTextView extends AppCompatTextView implements IHotWordTextViewListener {

    private IHotWordTextViewListener iOnClick = null;

    public HotWordTextView(Context context) {
        super(context);
    }

    public HotWordTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HotWordTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setListener(IHotWordTextViewListener iOnClick) {
        this.iOnClick = iOnClick;
    }

    /**
     * @param text
     * @param hotwordText            热词text
     * @param hotwordColorResourceID 热词颜色
     */
    public void setText(String text, String hotwordText, boolean isShowUnderLine, int hotwordColorResourceID) {
        try {
            int start = text.indexOf(hotwordText);
            int end = start + hotwordText.length();

            if (start == -1) {
                setText(text);
                return;
            }

            SpannableString str = new SpannableString(text);
            str.setSpan(new MyClickText(getContext(), hotwordColorResourceID, isShowUnderLine, this), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(str);

            //不设置 没有点击事件
            setMovementMethod(LinkMovementMethod.getInstance());
            //设置点击后的颜色
            setHighlightColor(Color.parseColor("#00000000"));
            setFocusable(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param text                      完整text
     * @param lisHotWord                热词的分词集合
     * @param isShowUnderLine           热词是否显示下划线
     * @param hotwordColorResourceID
     */
    public void setText(String text, ArrayList<NumberHotWordModel> lisHotWord, boolean isShowUnderLine, int hotwordColorResourceID) {
        try {
            SpannableString str = new SpannableString(text);
            for (int i = 0; i < lisHotWord.size(); i++) {
                str.setSpan(new MyClickText(getContext(), hotwordColorResourceID, isShowUnderLine, this), lisHotWord.get(i).startIndex, lisHotWord.get(i).startIndex + lisHotWord.get(i).text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            setText(str);

            //不设置 没有点击事件
            setMovementMethod(LinkMovementMethod.getInstance());
            //设置点击后的颜色
            setHighlightColor(Color.parseColor("#00000000"));
            setFocusable(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onHotWordTextViewOnClick() {
        if (this.iOnClick != null) {
            this.iOnClick.onHotWordTextViewOnClick();
        }
    }

    class MyClickText extends ClickableSpan {

        private Context context;
        private int colorResourceID = 0;
        private boolean isShowUnderLine = true;
        private IHotWordTextViewListener iOnClick;

        /**
         * @param context
         * @param colorResourceID 颜色ResourceID
         * @param isShowUnderLine 是否显示下划线
         * @param iOnClick
         */
        public MyClickText(Context context, int colorResourceID, boolean isShowUnderLine, IHotWordTextViewListener iOnClick) {
            this.context = context;
            this.colorResourceID = colorResourceID;
            this.isShowUnderLine = isShowUnderLine;
            this.iOnClick = iOnClick;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);

            // 设置文本的颜色
            ds.setColor(getResources().getColor(this.colorResourceID));
            // 是否显示下划线
            ds.setUnderlineText(this.isShowUnderLine);
        }

        @Override
        public void onClick(View widget) {

            if (this.iOnClick != null) {
                this.iOnClick.onHotWordTextViewOnClick();
            }
        }
    }
}
