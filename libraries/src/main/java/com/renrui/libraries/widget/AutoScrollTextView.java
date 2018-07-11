package com.renrui.libraries.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

/**
 * 自动垂直滚动的TextView
 */
public class AutoScrollTextView extends TextSwitcher implements ViewSwitcher.ViewFactory {

    private static final int FLAG_START_AUTO_SCROLL = 0;
    private static final int FLAG_STOP_AUTO_SCROLL = 1;

    private float mTextSize = 14;
    private int mPadding = 5;
    private int mTextColor = Color.BLACK;

    /**
     * @param textSize  字号
     * @param padding   内边距
     * @param textColor 字体颜色
     */
    public void setText(float textSize, int padding, int textColor) {
        mTextSize = textSize;
        mPadding = padding;
        mTextColor = textColor;
    }

    private OnScrollItemClickListener itemClickListener;

    /**
     * @param itemClickListener 点击回调事件
     */
    public void setOnScrollItemClickListener(OnScrollItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private Context mContext;
    private int currentId = -1;
    private ArrayList<String> textList;
    private Handler handler;

    //mInUp,mOutUp分别构成向下翻页的进出动画
    private Rotate3dAnimation mInUp;
    private Rotate3dAnimation mOutUp;

    public AutoScrollTextView(Context context) {
        this(context, null);
    }

    public AutoScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();

    }

    private void init() {
        textList = new ArrayList<>();

        mInUp = createAnim(true, true);
        mOutUp = createAnim(false, true);

        setInAnimation(mInUp);//当View显示时动画资源ID
        setOutAnimation(mOutUp);//当View隐藏是动画资源ID。
    }

    private Rotate3dAnimation createAnim(boolean turnIn, boolean turnUp) {

        Rotate3dAnimation rotation = new Rotate3dAnimation(turnIn, turnUp);
        rotation.setDuration(600);//执行动画的时间
        rotation.setFillAfter(false);//是否保持动画完毕之后的状态
        rotation.setInterpolator(new AccelerateInterpolator());//设置加速模式

        return rotation;
    }

    /**
     * @param time 间隔时间(毫秒)
     */
    @SuppressLint("HandlerLeak")
    public void setTextStillTime(final long time) {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case FLAG_START_AUTO_SCROLL:
                        if (textList.size() > 0) {
                            //数据大于一条滑动
                            if (textList.size() > 1) {
                                currentId++;
                                setText(textList.get(currentId % textList.size()));
                                handler.sendEmptyMessageDelayed(FLAG_START_AUTO_SCROLL, time);
                            } else {
                                currentId++;
                                setText(textList.get(currentId % textList.size()));
                                stopAutoScroll();
                            }
                        }
                        break;
                    case FLAG_STOP_AUTO_SCROLL:
                        handler.removeMessages(FLAG_START_AUTO_SCROLL);
                        break;
                }
            }
        };
    }

    /**
     * @param titles 数据源
     */
    public void setTextList(ArrayList<String> titles) {
        textList.clear();
        textList.addAll(titles);
        setFactory(this);
    }

    /**
     * 开始滚动
     */
    public void startAutoScroll() {
        if (null != handler) {
            setText("");
            handler.removeCallbacksAndMessages(null);
            handler.sendEmptyMessage(FLAG_START_AUTO_SCROLL);
        }
    }

    /**
     * 停止滚动
     */
    public void stopAutoScroll() {
        if (handler != null) {
            handler.sendEmptyMessage(FLAG_STOP_AUTO_SCROLL);
        }
    }

    //这里返回的TextView，就是我们看到的View,可以设置自己想要的效果
    @SuppressLint("RtlHardcoded")
    public View makeView() {
        TextView t = new TextView(mContext);
        t.setGravity(Gravity.CENTER_VERTICAL);
        t.setSingleLine(true);
        t.setEllipsize(TextUtils.TruncateAt.END);
        t.setPadding(mPadding, mPadding, mPadding, mPadding);
        t.setTextColor(mTextColor);
        t.setTextSize(mTextSize);
        t.setClickable(true);
        t.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null && textList.size() > 0 && currentId != -1) {
                    itemClickListener.onScrollItemClick(currentId % textList.size());
                }
            }
        });
        return t;
    }

    private class Rotate3dAnimation extends Animation {
        private float mCenterX;
        private float mCenterY;
        private final boolean mTurnIn;
        private final boolean mTurnUp;
        private Camera mCamera;

        Rotate3dAnimation(boolean turnIn, boolean turnUp) {
            mTurnIn = turnIn;
            mTurnUp = turnUp;
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mCamera = new Camera();
            mCenterY = getHeight();
            mCenterX = getWidth();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            final float centerX = mCenterX;
            final float centerY = mCenterY;
            final Camera camera = mCamera;
            final int direction = mTurnUp ? 1 : -1;

            final Matrix matrix = t.getMatrix();

            camera.save();
            if (mTurnIn) {
                camera.translate(0.0f, (direction * mCenterY * (interpolatedTime - 1.0f)), 0.0f);
                t.setAlpha(interpolatedTime);
            } else {
                camera.translate(0.0f, (direction * mCenterY * (interpolatedTime)), 0.0f);
                t.setAlpha(1 - interpolatedTime);
            }
            camera.getMatrix(matrix);
            camera.restore();

            matrix.preTranslate(-centerX, -centerY);
            matrix.postTranslate(centerX, centerY);
        }
    }

    /**
     * 轮播文本点击监听器
     */
    public interface OnScrollItemClickListener {
        void onScrollItemClick(int position);
    }
}