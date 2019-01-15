package com.renrui.libraries.util;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.renrui.libraries.R;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 公共弹窗消息
 */
public class CustomToast {
    private static Toast myToast;
    private static View viewMyToast;
    private static ImageView ivStat;
    private static TextView tvTitle;
    private static TextView tvContent;

    private static Timer timer = null;

    /**
     * 默认时长
     */
    private static final int defaultDuration = 20000;

    private static Toast getMyToast(String title, String content) {
        try {
            if (myToast == null) {
                myToast = new Toast(LibrariesCons.getContext());
                viewMyToast = View.inflate(LibrariesCons.getContext(), R.layout.view_mytoast, null);
                ivStat = viewMyToast.findViewById(R.id.ivStat);
                tvTitle = viewMyToast.findViewById(R.id.tvTitle);
                tvContent = viewMyToast.findViewById(R.id.tvContent);
            }

            // 标题，不会为空
            UtilitySecurity.resetVisibility(tvTitle, true);
            UtilitySecurity.setText(tvTitle, title);

            // 内容有可能为空
            if (TextUtils.isEmpty(content)) {
                UtilitySecurity.resetVisibility(tvContent, View.GONE);
            } else {
                UtilitySecurity.resetVisibility(tvContent, View.VISIBLE);
                UtilitySecurity.setText(tvContent, content);
            }
            myToast.setView(viewMyToast);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return myToast;
    }

    /**
     * 提示成功信息
     *
     * @param title 标题，不能为空
     */
    public static void makeTextSucess(String title) {

        try {
            makeTextSucess(title, "", Toast.LENGTH_SHORT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示成功信息
     *
     * @param title   标题，不能为空
     * @param content 内容可以为空
     */
    public static void makeTextSucess(String title, String content) {

        try {
            makeTextSucess(title, content, Toast.LENGTH_SHORT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示成功信息
     *
     * @param resourceID String资源ID
     */
    public static void makeTextSucess(int resourceID) {

        try {
            makeTextSucess(LibrariesCons.getContext().getString(resourceID), "", Toast.LENGTH_SHORT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示成功信息
     *
     * @param resourceID 标题资源ID
     * @param content    内容可以为空
     */
    public static void makeTextSucess(int resourceID, String content) {

        try {
            makeTextSucess(LibrariesCons.getContext().getString(resourceID), content, Toast.LENGTH_SHORT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void makeTextSucess(String title, String content, int duration) {
        try {
            myToast = CustomToast.getMyToast(title, content);
            ivStat.setBackgroundResource(R.drawable.toast_sucess_icon);

//            myToast.setDuration(duration);

            UtilitySecurity.resetVisibility(tvTitle, !TextUtils.isEmpty(title));

            show(duration);
//            myToast.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    /**
//     * 提示失败信息 （只有调试时弹）
//     *
//     * @param title 标题
//     */
//    public static void makeDebugTextError(String title) {
//        try {
//            if (AppConstant.isDebug) {
//                makeTextError(title, "", 8000);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    /**
     * 提示失败信息
     *
     * @param title 标题
     */
    public static void makeTextError(String title) {
        try {
            makeTextError(title, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示失败信息
     *
     * @param title   标题
     * @param content 内容
     */
    public static void makeTextError(String title, String content) {
        try {
            makeTextError(title, content, Toast.LENGTH_LONG);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示失败信息
     *
     * @param title   标题
     * @param content 内容
     */
    public static void makeTextError(String title, String content, int duration) {
        try {
            myToast = CustomToast.getMyToast(title, content);
            ivStat.setBackgroundResource(R.drawable.toast_error_icon);

//            myToast.setDuration(duration);

            UtilitySecurity.resetVisibility(tvTitle, !TextUtils.isEmpty(title));

            show(duration);
//            myToast.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示失败信息
     *
     * @param resourceID String资源ID
     */
    public static void makeTextError(int resourceID) {
        try {
            makeTextError(LibrariesCons.getContext().getString(resourceID), "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示失败信息
     *
     * @param resourceID String资源ID
     */
    public static void makeTextError(int resourceID, String content) {
        try {
            makeTextError(LibrariesCons.getContext().getString(resourceID), content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(String title) {
        try {
            makeTextWarn(title, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(int resourceID) {
        try {
            makeTextWarn(LibrariesCons.getContext().getString(resourceID), "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(int resourceID, String content) {
        try {
            makeTextWarn(LibrariesCons.getContext().getString(resourceID), content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(int resourceID, int duration) {
        try {
            makeTextWarn(LibrariesCons.getContext().getString(resourceID), "", duration);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(String title, String content) {
        makeTextWarn(title, content, Toast.LENGTH_SHORT);
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(String title, String content, int duration) {
        try {
            myToast = CustomToast.getMyToast(title, content);
            ivStat.setBackgroundResource(R.drawable.toast_warn_icon);

//            myToast.setDuration(duration);

            if (TextUtils.isEmpty(title)) {
                UtilitySecurity.resetVisibility(tvTitle, View.GONE);
            } else {
                UtilitySecurity.resetVisibility(tvTitle, View.VISIBLE);
            }

            show(duration);
//            myToast.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 防止重复反射
    private static boolean isReflectedHandler;

    public static void show(int duration) {

//        // android 7.1系统，反射Toast 的handler，加try catch防止badException
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && Build.VERSION.SDK_INT < Build.VERSION_CODES.O && !isReflectedHandler) {
//            reflectTNHandler(myToast);
//            isReflectedHandler = true;
//        }
//
//        myToast.show();

        if (duration < defaultDuration)
            duration = defaultDuration;

        if (timer != null)
            timer.cancel();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (myToast == null) {
                    return;
                }

                try {
                    // android 7.1系统，反射Toast 的handler，加try catch防止badException
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && Build.VERSION.SDK_INT < Build.VERSION_CODES.O && !isReflectedHandler) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                reflectTNHandler(myToast);
                                isReflectedHandler = true;
                                myToast.show();
                            }
                        });
                    } else {
                        myToast.show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 0, duration);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                try {
                    if (myToast != null)
                        myToast.cancel();

                    if (timer != null)
                        timer.cancel();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, duration);
    }

    public static void hide() {
        try {
            if (myToast != null)
                myToast.cancel();

            if (timer != null)
                timer.cancel();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void reflectTNHandler(final Toast toast) {

        try {
            Field tNField = toast.getClass().getDeclaredField("mTN");
            if (tNField == null) {
                return;
            }
            tNField.setAccessible(true);
            Object TN = tNField.get(toast);
            if (TN == null) {
                return;
            }
            Field handlerField = TN.getClass().getDeclaredField("mHandler");
            if (handlerField == null) {
                return;
            }

            handlerField.setAccessible(true);
            handlerField.set(TN, new ProxyTNHandler(TN));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
