package com.renrui.libraries.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * 支持zoom
 */
public class ZoomScrollView extends ScrollView {

    private OnScrollListener onScrollListener;

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

    // 滑动放大系数，系数越大，滑动时放大程度越大
    public static final float mScaleRatio = 0.5f;

    // 最大的放大倍数
    public static final float mScaleTimes = 1.5f;

    // 回弹时间系数，系数越小，回弹越快
    public static final float mReplyRatio = 1f;

    private boolean enable = true;

    public ZoomScrollView(Context context) {
        super(context);
    }

    public ZoomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZoomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEnable(boolean value) {
        this.enable = value;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 不可过度滚动，否则上移后下拉会出现部分空白的情况
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (!this.enable) {
            return super.onTouchEvent(ev);
        }

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
                    int distance = (int) ((ev.getY() - y) * mScaleRatio);
                    if (distance < 0) break;//若往下滑动
                    mScaling = true;
                    setZoom(zoomView, zoomViewWidth, zoomViewHeight, distance);
                    return true;
                case MotionEvent.ACTION_UP:
                    mScaling = false;
                    replyView(zoomView, zoomViewWidth, zoomViewHeight);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return super.onTouchEvent(ev);
    }

    /**
     * 放大view
     */
    public static void setZoom(View zoomView, int zoomViewWidth, int zoomViewHeight, float s) {

        if (zoomView == null) {
            return;
        }

        try {
            float scaleTimes = (float) ((zoomViewWidth + s) / (zoomViewWidth * 1.0));
            // 如超过最大放大倍数，直接返回
            if (scaleTimes > mScaleTimes) return;

            ViewGroup.LayoutParams layoutParams = zoomView.getLayoutParams();
            layoutParams.width = (int) (zoomViewWidth + s);
            layoutParams.height = (int) (zoomViewHeight * ((zoomViewWidth + s) / zoomViewWidth));
            // 设置控件水平居中
            ((MarginLayoutParams) layoutParams).setMargins(-(layoutParams.width - zoomViewWidth) / 2, 0, 0, 0);
            zoomView.setLayoutParams(layoutParams);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 回弹
     */
    public static void replyView(final View zoomView, final int zoomViewWidth, final int zoomViewHeight) {

        if (zoomView == null) {
            return;
        }

        try {
            final float distance = zoomView.getMeasuredWidth() - zoomViewWidth;
            ValueAnimator anim = ObjectAnimator.ofFloat(distance, 0.0F).setDuration((long) (distance * mReplyRatio));
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    setZoom(zoomView, zoomViewWidth, zoomViewHeight, (Float) animation.getAnimatedValue());
                }
            });
            anim.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) onScrollListener.onScroll(l, t, oldl, oldt);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public interface OnScrollListener {
        void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }
}
