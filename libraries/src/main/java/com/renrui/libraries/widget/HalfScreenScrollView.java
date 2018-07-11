package com.renrui.libraries.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 半屏幕的滑动控件
 */
public class HalfScreenScrollView extends FixChildAutoScrollView {

    public HalfScreenScrollView(Context context) {
        super(context);
    }

    public HalfScreenScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (heightMeasureSpec > MeasureSpec.makeMeasureSpec(getHalfScreenHeight(), MeasureSpec.AT_MOST)) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(getHalfScreenHeight(), MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int getHalfScreenHeight() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int heightPixels = displayMetrics.heightPixels;
        int titlePixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50.5f, displayMetrics);
        return (heightPixels - titlePixels) / 3;
    }
}
