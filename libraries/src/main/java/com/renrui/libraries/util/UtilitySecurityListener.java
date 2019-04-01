package com.renrui.libraries.util;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;

/**
 * 安全方法
 */
public class UtilitySecurityListener {

    public static void setOnClickListener(View view, @Nullable View.OnClickListener listener) {

        if (view == null || listener == null) {
            return;
        }

        try {
            view.setOnClickListener(listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setOnClickListener(@Nullable View.OnClickListener listener, View... args) {

        if (args == null || listener == null) {
            return;
        }

        try {
            for (int i = 0; i < args.length; i++) {
                args[i].setOnClickListener(listener);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setOnFocusChangeListener(View view, @Nullable View.OnFocusChangeListener listener) {

        if (view == null || listener == null) {
            return;
        }

        try {
            view.setOnFocusChangeListener(listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setOnRefreshListener(SwipeRefreshLayout srl, @Nullable SwipeRefreshLayout.OnRefreshListener listener) {

        if (srl == null || listener == null) {
            return;
        }

        try {
            srl.setOnRefreshListener(listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setOnScrollListener(ListView lv, @Nullable AbsListView.OnScrollListener listener) {

        if (lv == null || listener == null) {
            return;
        }

        try {
            lv.setOnScrollListener(listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setOnItemClickListener(ListView lv, @Nullable AbsListView.OnItemClickListener listener) {

        if (lv == null || listener == null) {
            return;
        }

        try {
            lv.setOnItemClickListener(listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addTextChangedListener(EditText et, @Nullable TextWatcher listener) {

        if (et == null || listener == null) {
            return;
        }

        try {
            et.addTextChangedListener(listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}