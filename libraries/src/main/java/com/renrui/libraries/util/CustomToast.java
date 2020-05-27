package com.renrui.libraries.util;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.renrui.libraries.R;

/**
 * 公共弹窗消息
 */
public class CustomToast {

    private static final int TYPE_SUCCESS = 1;
    private static final int TYPE_ERROR = 2;
    private static final int TYPE_WARN = 3;

    /**
     * 显示吐司
     *
     * @param type     1：成功 2：失败 3：警告
     * @param title    标题
     * @param content  内容
     * @param duration Toast.LENGTH_SHORT or Toast.LENGTH_LONG
     */
    private static void show(int type, String title, String content, int duration) {
        View toastView;
        if (duration == Toast.LENGTH_SHORT) {
            toastView = ToastUtils.showCustomShort(R.layout.view_mytoast);
        } else {
            toastView = ToastUtils.showCustomLong(R.layout.view_mytoast);
        }
        ToastUtils.setGravity(-1, -1, -1);

        ImageView ivStat = toastView.findViewById(R.id.ivStat);
        TextView tvTitle = toastView.findViewById(R.id.tvTitle);
        TextView tvContent = toastView.findViewById(R.id.tvContent);

        int drawableId = R.drawable.toast_sucess_icon;

        switch (type) {
            case TYPE_SUCCESS:
                drawableId = R.drawable.toast_sucess_icon;
                break;
            case TYPE_ERROR:
                drawableId = R.drawable.toast_error_icon;
                break;
            case TYPE_WARN:
                drawableId = R.drawable.toast_warn_icon;
                break;
            default:
                break;
        }

        UtilitySecurity.setBackgroundResource(ivStat, drawableId);
        UtilitySecurity.setText(tvTitle, title);
        UtilitySecurity.resetVisibility(tvContent, !TextUtils.isEmpty(content));
        UtilitySecurity.setText(tvContent, content);
    }

    /**
     * 显示吐司
     *
     * @param title    标题
     * @param content  内容
     * @param duration Toast.LENGTH_SHORT or Toast.LENGTH_LONG
     */
    private static void showCenter(int drawableId, String title, String content, int duration) {
        View toastView;
        if (duration == Toast.LENGTH_SHORT) {
            toastView = ToastUtils.showCustomShort(R.layout.view_mytoast);
        } else {
            toastView = ToastUtils.showCustomLong(R.layout.view_mytoast);
        }
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);

        ImageView ivStat = toastView.findViewById(R.id.ivStat);
        TextView tvTitle = toastView.findViewById(R.id.tvTitle);
        TextView tvContent = toastView.findViewById(R.id.tvContent);

        if (drawableId == 0) {
            UtilitySecurity.resetVisibility(ivStat, false);
        } else {
            UtilitySecurity.resetVisibility(ivStat, true);
            UtilitySecurity.setBackgroundResource(ivStat, drawableId);
        }
        UtilitySecurity.setText(tvTitle, title);
        UtilitySecurity.resetVisibility(tvContent, !TextUtils.isEmpty(content));
        UtilitySecurity.setText(tvContent, content);
    }

    public static void showCenter(String title) {
        showCenter(0, title, "", Toast.LENGTH_LONG);
    }

    public static void showCenter(int drawableId, String title) {
        showCenter(drawableId, title, "", Toast.LENGTH_LONG);
    }

    public static void showCenterSuccess(String title) {
        showCenter(R.drawable.toast_sucess_icon, title, "", Toast.LENGTH_LONG);
    }

    public static void showCenterError(String title) {
        showCenter(R.drawable.toast_error_icon, title, "", Toast.LENGTH_LONG);
    }

    public static void showCenterWarn(String title) {
        showCenter(R.drawable.toast_warn_icon, title, "", Toast.LENGTH_LONG);
    }

    /**
     * 提示成功信息
     *
     * @param title 标题，不能为空
     */
    public static void makeTextSucess(String title) {
        makeTextSucess(title, "");
    }

    /**
     * 提示成功信息
     *
     * @param resourceID String资源ID
     */
    public static void makeTextSucess(int resourceID) {
        makeTextSucess(LibrariesCons.getContext().getString(resourceID));
    }

    /**
     * 提示成功信息
     *
     * @param title   标题，不能为空
     * @param content 内容可以为空
     */
    public static void makeTextSucess(String title, String content) {
        makeTextSucess(title, content, Toast.LENGTH_LONG);
    }

    /**
     * 提示成功信息
     *
     * @param resourceID 标题资源ID
     * @param content    内容可以为空
     */
    public static void makeTextSucess(int resourceID, String content) {
        makeTextSucess(LibrariesCons.getContext().getString(resourceID), content);
    }

    public static void makeTextSucess(String title, String content, int duration) {
        show(TYPE_SUCCESS, title, content, duration);
    }

    /**
     * 提示失败信息
     *
     * @param title 标题
     */
    public static void makeTextError(String title) {
        makeTextError(title, "");
    }

    /**
     * 提示失败信息
     *
     * @param resourceID String资源ID
     */
    public static void makeTextError(int resourceID) {
        makeTextError(LibrariesCons.getContext().getString(resourceID));
    }

    /**
     * 提示失败信息
     *
     * @param title   标题
     * @param content 内容
     */
    public static void makeTextError(String title, String content) {
        makeTextError(title, content, Toast.LENGTH_LONG);
    }

    /**
     * 提示失败信息
     *
     * @param resourceID String资源ID
     */
    public static void makeTextError(int resourceID, String content) {
        makeTextError(LibrariesCons.getContext().getString(resourceID), content, Toast.LENGTH_LONG);
    }

    /**
     * 提示失败信息
     *
     * @param title   标题
     * @param content 内容
     */
    public static void makeTextError(String title, String content, int duration) {
        show(TYPE_ERROR, title, content, duration);
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(String title) {
        makeTextWarn(title, "");
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(int resourceID) {
        makeTextWarn(LibrariesCons.getContext().getString(resourceID));
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(int resourceID, String content) {
        makeTextWarn(LibrariesCons.getContext().getString(resourceID), content);
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(int resourceID, int duration) {
        makeTextWarn(LibrariesCons.getContext().getString(resourceID), "", Toast.LENGTH_LONG);
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(String title, String content) {
        makeTextWarn(title, content, Toast.LENGTH_LONG);
    }

    /**
     * 提示警告信息
     */
    public static void makeTextWarn(String title, String content, int duration) {
        show(TYPE_WARN, title, content, duration);
    }
}
