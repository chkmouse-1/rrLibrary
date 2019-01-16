package com.renrui.libraries.util;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.renrui.libraries.interfaces.ITextviewClickable;

public class SpanClickable extends ClickableSpan implements View.OnClickListener {
    private final ITextviewClickable clickListener;
    private int position;

    public SpanClickable(ITextviewClickable clickListener, int position) {
        this.clickListener = clickListener;
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        if (clickListener != null)
            clickListener.onSpanClick(position);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }
}
