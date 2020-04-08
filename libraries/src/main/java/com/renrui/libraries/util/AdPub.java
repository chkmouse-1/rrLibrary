package com.renrui.libraries.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.renrui.libraries.R;
import com.renrui.libraries.interfaces.ICheckNetWorkListener;
import com.renrui.libraries.interfaces.IOneButtonListener;
import com.renrui.libraries.interfaces.ITwoButtonAddCloseListener;
import com.renrui.libraries.interfaces.ITwoButtonListener;

/**
 * 公共弹窗
 */
public class AdPub {

    public static void showExit(Context context, final IOneButtonListener listener) {

        if (context == null) {
            return;
        }

        AdPub.showViewTwoButton(context,
                context.getString(R.string.info_exit_unSave),
                context.getString(R.string.info_exit_cancel),
                context.getString(R.string.info_exit_ok),
                new ITwoButtonListener() {
                    @Override
                    public void onLeftButtonOnclick() {
                        if (listener != null) {
                            listener.onButtonOnclick();
                        }
                    }

                    @Override
                    public void onRightButtonOnclick() {
                    }
                });
    }

    /**
     * 1个按钮,只有内容
     */
    public static void showViewOneButton(Context context, String content, String buttonText, final IOneButtonListener listener) {
        show(context, false, "", content, "", buttonText, new ITwoButtonListener() {
            @Override
            public void onLeftButtonOnclick() {
            }

            @Override
            public void onRightButtonOnclick() {
                if (listener != null) {
                    listener.onButtonOnclick();
                }
            }
        });
    }

    /**
     * 1个按钮,只有内容
     */
    public static void showViewOneButton(Context context, String content, String buttonText, int gravity, final IOneButtonListener listener) {
        show(context, false, "", content, "", buttonText, gravity, false, new ITwoButtonAddCloseListener() {
            @Override
            public void onLeftButtonOnclick() {

            }

            @Override
            public void onRightButtonOnclick() {
                if (listener != null) {
                    listener.onButtonOnclick();
                }
            }

            @Override
            public void onClose() {

            }
        });
    }

    /**
     * 1个按钮,有标题和内容
     */
    public static void showViewOneButton(Context context, String title, CharSequence content, String buttonText, final IOneButtonListener listener) {
        show(context, false, title, content, "", buttonText, new ITwoButtonListener() {
            @Override
            public void onLeftButtonOnclick() {
            }

            @Override
            public void onRightButtonOnclick() {

                if (listener != null) {
                    listener.onButtonOnclick();
                }
            }
        });
    }

    /**
     * 1个按钮,有标题和内容
     */
    public static void showViewOneButton(Context context, String title, CharSequence content, String buttonText, int gravity, final IOneButtonListener listener) {
        show(context, false, title, content, "", buttonText, gravity, false, new ITwoButtonAddCloseListener() {
            @Override
            public void onLeftButtonOnclick() {

            }

            @Override
            public void onRightButtonOnclick() {
                if (listener != null) {
                    listener.onButtonOnclick();
                }
            }

            @Override
            public void onClose() {

            }
        });
    }

    /**
     * 1个按钮,只有标题
     */
    public static void showViewOneButtonAddClose(Context context, String title, String buttonText, final ITwoButtonAddCloseListener listener) {
        show(context, false, "", title, "", buttonText, Gravity.CENTER, true, new ITwoButtonAddCloseListener() {
            @Override
            public void onLeftButtonOnclick() {
                if (listener != null) {
                    listener.onLeftButtonOnclick();
                }
            }

            @Override
            public void onRightButtonOnclick() {
                if (listener != null) {
                    listener.onRightButtonOnclick();
                }
            }

            @Override
            public void onClose() {
                if (listener != null) {
                    listener.onClose();
                }
            }
        });
    }

    /**
     * 2个按钮,只有内容
     */
    public static void showViewTwoButton(Context context, CharSequence content, String leftButtonText, String rightButtonText, final ITwoButtonListener listener) {
        show(context, false, "", content, leftButtonText, rightButtonText, listener);
    }

    /**
     * 2个按钮,只有内容
     */
    public static void showViewTwoButton(Context context, boolean isCancel, CharSequence content, String leftButtonText, String rightButtonText, final ITwoButtonListener listener) {
        show(context, isCancel, "", content, leftButtonText, rightButtonText, listener);
    }

    /**
     * 2个按钮,有标题和内容
     */
    public static void showViewTwoButton(Context context, String title, CharSequence content, String leftButtonText, String rightButtonText, final ITwoButtonListener listener) {
        show(context, false, title, content, leftButtonText, rightButtonText, listener);
    }

    /**
     * @param context
     * @param cancel          是否允许点击外面关闭
     * @param title           标题
     * @param content         内容
     * @param leftButtonText  左侧按钮
     * @param rightButtonText 右侧按钮
     * @param listener
     */
    public static void show(Context context, boolean cancel, String title, CharSequence content, String leftButtonText, String rightButtonText, final ITwoButtonListener listener) {
        show(context, cancel, title, content, leftButtonText, rightButtonText, Gravity.CENTER, false, new ITwoButtonAddCloseListener() {
            @Override
            public void onLeftButtonOnclick() {
                if (listener != null) {
                    listener.onLeftButtonOnclick();
                }
            }

            @Override
            public void onRightButtonOnclick() {
                if (listener != null) {
                    listener.onRightButtonOnclick();
                }
            }

            @Override
            public void onClose() {

            }
        });
    }

    /**
     * 有主标题和副标题的2个按钮弹窗,右上角有关闭按钮
     *
     * @param title           副标题
     * @param leftButtonText  左侧按钮text
     * @param rightButtonText 右侧按钮tet
     */
    public static void showViewTwoButtonAddClose(Context context, String title, String leftButtonText, String rightButtonText, final ITwoButtonAddCloseListener listener) {

        show(context, false, "", title, leftButtonText, rightButtonText, Gravity.CENTER, true, new ITwoButtonAddCloseListener() {
            @Override
            public void onLeftButtonOnclick() {
                if (listener != null) {
                    listener.onLeftButtonOnclick();
                }
            }

            @Override
            public void onRightButtonOnclick() {
                if (listener != null) {
                    listener.onRightButtonOnclick();
                }
            }

            @Override
            public void onClose() {
                if (listener != null) {
                    listener.onClose();
                }
            }
        });
    }

    /**
     * @param context
     * @param cancel          是否允许点击外面关闭
     * @param title           标题
     * @param content         内容
     * @param leftButtonText  左侧按钮
     * @param rightButtonText 右侧按钮
     * @param gravity         内容gravity
     * @param listener
     */
    public static void show(Context context, final boolean cancel, String title, final CharSequence content, String leftButtonText, String rightButtonText, int gravity, boolean showClose, final ITwoButtonAddCloseListener listener) {
        try {
            if (context instanceof Activity && ((Activity) context).isFinishing()) {
                return;
            }

            final Dialog dialog = new Dialog(context, android.R.style.Theme_Holo_Dialog_NoActionBar);
            dialog.setCanceledOnTouchOutside(cancel);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (!cancel && keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            View view = View.inflate(context, R.layout.view_alertdialog_public_titleandcontent, null);

            TextView tvTitle = view.findViewById(R.id.tvTitle);
            TextView tvContent = view.findViewById(R.id.tvContent);
            TextView tvOnlyContent = view.findViewById(R.id.tvOnlyContent);

            // 只有标题
            if (!TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
                UtilitySecurity.resetVisibility(tvTitle, false);
                UtilitySecurity.resetVisibility(tvContent, false);
                tvOnlyContent.setGravity(gravity);
                UtilitySecurity.setTextEmptyIsGone(tvOnlyContent, title);
            }
            // 只有内容
            else if (TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
                UtilitySecurity.resetVisibility(tvTitle, false);
                UtilitySecurity.resetVisibility(tvContent, false);
                tvOnlyContent.setGravity(gravity);
                UtilitySecurity.setTextEmptyIsGone(tvOnlyContent, content);
            }
            // 有标题和内容
            else {
                tvTitle.getPaint().setFakeBoldText(true);
                UtilitySecurity.setText(tvTitle, title);
                UtilitySecurity.setText(tvContent, content);
                tvContent.setGravity(gravity);
                UtilitySecurity.resetVisibility(tvTitle, true);
                UtilitySecurity.resetVisibility(tvContent, true);
                UtilitySecurity.resetVisibility(tvOnlyContent, false);
            }

            TextView tvCancel = view.findViewById(R.id.tvCancel);
            UtilitySecurity.setTextEmptyIsGone(tvCancel, leftButtonText);
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();

                    if (listener != null) {
                        listener.onLeftButtonOnclick();
                    }
                }
            });

            TextView tvOk = view.findViewById(R.id.tvOk);
            UtilitySecurity.setTextEmptyIsGone(tvOk, rightButtonText);
            tvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();

                    if (listener != null) {
                        listener.onRightButtonOnclick();
                    }
                }
            });

            ImageView ivClose = view.findViewById(R.id.ivClose);
            UtilitySecurity.resetVisibility(ivClose, showClose);
            ivClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();

                    if (listener != null) {
                        listener.onClose();
                    }
                }
            });

            dialog.setContentView(view);
            dialog.show();

            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.width = LibUtility.dp2px(showClose ? 290 : 275);
            dialog.getWindow().setAttributes(params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 调试错误窗
     *
     * @param content 内容
     */
    public static void showDebugErrorMessage(final Context mContext, final String content) {
        try {
            final Dialog dialog = new Dialog(mContext, android.R.style.Theme_Holo_Dialog_NoActionBar);
            dialog.setCanceledOnTouchOutside(false);
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            View view = View.inflate(mContext, R.layout.view_alertdialog_debug_errormessage, null);

            TextView tvContent = view.findViewById(R.id.tvContent);
            tvContent.setText(content);

            TextView tvCopy = view.findViewById(R.id.tvCopy);
            tvCopy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        ClipboardManager cmb = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                        cmb.setText(content);
                        CustomToast.makeTextSucess("已复制到剪贴板!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            TextView tvOk = view.findViewById(R.id.tvOk);
            tvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) {
                        dialog.cancel();
                    }
                }
            });

            dialog.setContentView(view);
            dialog.show();

            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.width = LibUtility.dp2px(275);
            dialog.getWindow().setAttributes(params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showCheckNetWork(Activity activity, final ICheckNetWorkListener listener) {

        if (activity == null || activity.isFinishing())
            return;

        if (!UtilityNetWork.netWorkIsWifi()) {
            AdPub.showViewTwoButton(activity, "当前使用的是移动网络，播放可能会产生流量费用", "播放", "取消", new ITwoButtonListener() {
                @Override
                public void onLeftButtonOnclick() {
                    if (listener != null)
                        listener.onContinue();
                }

                @Override
                public void onRightButtonOnclick() {
                    if (listener != null)
                        listener.onCancel();
                }
            });
        } else {
            if (listener != null)
                listener.onContinue();
        }
    }
}