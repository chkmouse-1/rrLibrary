package com.renrui.libraries.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 禁止加载时自动滑动
 */
public class FixChildAutoScrollView extends ScrollView {

    public interface OnScrollListener {
        void onScroll(int left, int top, int oldLeft, int oldTop);
    }

    private OnScrollListener onScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public FixChildAutoScrollView(Context context) {
        super(context);
    }

    public FixChildAutoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixChildAutoScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }

    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (null != onScrollListener) {
            onScrollListener.onScroll(l, t, oldl, oldt);
        }
    }
}
