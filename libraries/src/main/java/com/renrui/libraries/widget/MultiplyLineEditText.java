package com.renrui.libraries.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * 分割线
 */
public class MultiplyLineEditText extends AppCompatEditText {

    private Paint linePaint;
    private Rect mRect;
    private float lineSpacingExtra;

    public MultiplyLineEditText(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        mRect = new Rect();
        linePaint = new Paint();
        linePaint.setColor(Color.parseColor("#CCCCCC"));
        lineSpacingExtra = getLineSpacingExtra() / 3 * 2;
    }

    public void onDraw(Canvas paramCanvas) {
        int count = getLineCount();
        for (int i = 0; i < count; i++) {
            int baseline = getLineBounds(i, mRect);
            paramCanvas.drawLine(mRect.left, baseline + lineSpacingExtra,
                    mRect.right, baseline + lineSpacingExtra, linePaint);
        }
        super.onDraw(paramCanvas);
    }
}
