package com.renrui.libraries.util;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.renrui.libraries.interfaces.IHotWordTextViewListener;
import com.renrui.libraries.interfaces.ITextviewClickable;

public class SpanClickable extends ClickableSpan implements View.OnClickListener {
    private final ITextviewClickable clickListener;
    private int position;
    private boolean isUnderline;
    private int color;
    private boolean isBold = false;
    private float textSize;

    public SpanClickable(ITextviewClickable clickListener, int position, boolean isUnderline, int color, boolean isBold, float textSize) {
        this.clickListener = clickListener;
        this.position = position;
        this.isUnderline = isUnderline;
        this.color = color;
        this.isBold = isBold;
        this.textSize = textSize;
    }

    @Override
    public void onClick(@NonNull View v) {
        if (clickListener != null)
            clickListener.onSpanClick(position);
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(isUnderline);
        ds.setColor(color);
        if (this.isBold) {
            ds.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (textSize > 0) {
            ds.setTextSize(textSize);
        }
    }
}
