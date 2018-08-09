package com.renrui.libraries.util;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.renrui.libraries.interfaces.ITextviewClickable;

public class SpanClickable extends ClickableSpan implements View.OnClickListener {
    private final ITextviewClickable clickListener;

    public SpanClickable(ITextviewClickable clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View v) {
        if(clickListener != null)
            clickListener.onSpanClick();
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }
}
