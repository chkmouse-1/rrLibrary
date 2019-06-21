package com.renrui.libraries.util;

import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.renrui.libraries.interfaces.ITextviewClickable;

public class SpanClickable extends ClickableSpan implements View.OnClickListener {
    private final ITextviewClickable clickListener;
    private int position;
    private boolean isUnderline;
    private int color;

    public SpanClickable(ITextviewClickable clickListener, int position, boolean isUnderline, int color) {
        this.clickListener = clickListener;
        this.position = position;
        this.isUnderline = isUnderline;
        this.color = color;
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
    }
}
