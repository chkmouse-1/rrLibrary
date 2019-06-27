
// 香草 此控件会崩溃
//package com.renrui.libraries.widget;
//
//import android.content.Context;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.Message;
//import android.support.v7.widget.AppCompatTextView;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//
//import java.text.DecimalFormat;
//import java.util.Date;
//
//public class TimeTextView extends AppCompatTextView {
//
//    private DecimalFormat mDecimalFormat = new DecimalFormat();
//    private String mPlaceHolderString = "请在 %s 内提交任务";
//    private OnCountDownFinishListener mOnCountDownFinishListener;
//
//    @Override
//    protected void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//        // 停止计时
//        stop();
//    }
//
//    long Time;
//
//    private Handler handler = new Handler(Looper.getMainLooper()) {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//                    long mTime = Time;
//                    if (mTime > 0) {
//                        setText(String.format(mPlaceHolderString, getShowDate(mTime)));
//                        Time = Time - 1000;
//                        handler.sendEmptyMessageDelayed(0, 1000);
//                    } else {
//                        stop();
//                        setText(String.format(mPlaceHolderString, "00:00:00"));
//                        if (null != mOnCountDownFinishListener) {
//                            mOnCountDownFinishListener.onFinish();
//                        }
//                    }
//                    break;
//            }
//        }
//    };
//
//    private String getShowDate(long mTime) {
//        try {
//            long ss = 1000;
//            long mi = ss * 60;
//            long hh = mi * 60;
//
//            long hour = mTime / hh;
//            long minute = (mTime - hour * hh) / mi;
//            double second = Math.rint(
//                    Double.longBitsToDouble((mTime - hour * hh - minute * mi)) / Double.longBitsToDouble(ss));
//
//            mDecimalFormat.setMaximumFractionDigits(2);
//            mDecimalFormat.setMinimumIntegerDigits(2);
//
//            return mDecimalFormat.format(hour).concat(":")
//                    .concat(mDecimalFormat.format(minute)).concat(":")
//                    .concat(mDecimalFormat.format(second));
//        } catch (Exception e) {
//            return "00:00:00";
//        }
//    }
//
//    public TimeTextView(Context context) {
//        super(context);
//    }
//
//    public TimeTextView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    public void setTimes(long mT) {
//        setTimes(mT, null, null);
//    }
//
//    public void setTimes(long mT, String placeHolderString, OnCountDownFinishListener listener) {
//        if (!TextUtils.isEmpty(placeHolderString)) {
//            mPlaceHolderString = placeHolderString;
//        }
//
//        if (null != listener) {
//            mOnCountDownFinishListener = listener;
//        }
//
//        // 标示已经启动
//        Date date = new Date();
//        long t2 = date.getTime();
//        Time = mT - t2;
//
//        if (Time > 0) {
//            stop();
//            handler.sendEmptyMessage(0);
//        } else {
//            stop();
//            setText(String.format(mPlaceHolderString, "00:00:00"));
//        }
//    }
//
//    public void stop() {
//        // 移除消息
//        handler.removeMessages(0);
//    }
//
//    public interface OnCountDownFinishListener {
//        void onFinish();
//    }
//}