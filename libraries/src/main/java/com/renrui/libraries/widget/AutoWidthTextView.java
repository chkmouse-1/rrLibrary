package com.renrui.libraries.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;

public class AutoWidthTextView extends AppCompatTextView {

    public AutoWidthTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 在此方法中学习到：getTextSize返回值是以像素(px)为单位的，而setTextSize()是以sp为单位的，
     * 因此要这样设置setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
     */
    private void refitText(String text, int textWidth) {
        if (textWidth > 0) {
            Paint paint = new Paint();
            paint.set(this.getPaint());
            //获得当前TextView的有效宽度
            int availableWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();
            float[] widths = new float[text.length()];
            Rect rect = new Rect();
            paint.getTextBounds(text, 0, text.length(), rect);
            //所有字符串所占像素宽度
            int textWidths = rect.width();
            float textSize = this.getTextSize();
            while (textWidths > availableWidth) {
                textSize = textSize - 1;
                paint.setTextSize(textSize);//这里传入的单位是px
                textWidths = paint.getTextWidths(text, widths);
            }
            this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);//这里制定传入的单位是px
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        refitText(getText().toString(), this.getWidth());
    }
}