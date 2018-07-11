package com.renrui.libraries.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 支持zoomView
 */
public class ZoomCoordinatorLayout extends CoordinatorLayout {

    // 下拉位置
    private float y = 0f;
    // zoomView原本的宽高
    private int zoomViewWidth = 0;
    private int zoomViewHeight = 0;

    // 是否正在放大
    private boolean mScaling = false;

    // 放大的view
    private View zoomView;

    public void setZoomView(View zoomView) {
        this.zoomView = zoomView;
    }

    public ZoomCoordinatorLayout(Context context) {
        super(context);
    }

    public ZoomCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZoomCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 不可过度滚动，否则上移后下拉会出现部分空白的情况
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        try {
            // 默认大小
            if (zoomViewWidth <= 0 || zoomViewHeight <= 0) {
                zoomViewWidth = zoomView.getMeasuredWidth();
                zoomViewHeight = zoomView.getMeasuredHeight();
            }

            if (zoomView == null || zoomViewWidth <= 0 || zoomViewHeight <= 0) {
                return super.onTouchEvent(ev);
            }

            switch (ev.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    if (!mScaling) {
                        if (getScrollY() == 0) {
                            y = ev.getY();//滑动到顶部时，记录位置
                        } else {
                            break;
                        }
                    }
                    int distance = (int) ((ev.getY() - y) * ZoomScrollView.mScaleRatio);
                    if (distance < 0) break;//若往下滑动
                    mScaling = true;
                    ZoomScrollView.setZoom(zoomView,zoomViewWidth,zoomViewHeight, distance);
                    return true;
                case MotionEvent.ACTION_UP:
                    mScaling = false;
                    ZoomScrollView.replyView(zoomView,zoomViewWidth,zoomViewHeight);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return super.onTouchEvent(ev);
    }
}
