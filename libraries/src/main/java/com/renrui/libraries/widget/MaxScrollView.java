package com.renrui.libraries.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 可以设置maxHeight
 */
public class MaxScrollView extends ScrollView {

    private int maxHeight = 0;

    public MaxScrollView(Context context) {
        super(context);
    }

    public MaxScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MaxScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setMaxHeight(int value) {
        maxHeight = value;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            if (maxHeight > 0 && heightMeasureSpec > MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST)) {
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
            }

            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
