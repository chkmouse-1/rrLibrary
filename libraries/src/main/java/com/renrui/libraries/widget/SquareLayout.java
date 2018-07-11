package com.renrui.libraries.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 长宽相等的相对布局
 */
public class SquareLayout extends RelativeLayout {

    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        try {
            View child = getChildAt(0);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childWidthSize = child.getMeasuredWidth();
            if (childWidthSize < getMinimumWidth()) {
                childWidthSize = getMinimumWidth();
            }
            heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
