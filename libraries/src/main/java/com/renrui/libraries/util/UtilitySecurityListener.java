package com.renrui.libraries.util;

import android.content.ComponentName;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

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

    public static void setOnLongClickListener(View view, @Nullable View.OnLongClickListener listener) {
        if (view == null || listener == null)
            return;
        try {
            view.setOnLongClickListener(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setOnLongClickListener(@Nullable View.OnLongClickListener listener, View... views) {
        if (views == null || listener == null) {
            return;
        }
        try {
            for (View view : views) {
                if (view == null) continue;
                view.setOnLongClickListener(listener);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void setOnCheckedChangeListener(CompoundButton cb, CompoundButton.OnCheckedChangeListener listener) {
        if (cb == null || listener == null) {
            return;
        }
        try {
            cb.setOnCheckedChangeListener(listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener, CompoundButton... cbs) {
        if (listener == null)
            return;
        try {
            for (CompoundButton cb : cbs) {
                if (cb == null) {
                    continue;
                }
                setOnCheckedChangeListener(cb, listener);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setOnTouchListener(View view, View.OnTouchListener listener) {
        if (view == null || listener == null)
            return;
        try {
            view.setOnTouchListener(listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setOnTouchListener(View.OnTouchListener listener, View... views) {
        if (listener == null)
            return;
        try {
            for (View view : views) {
                if (view == null)
                    continue;
                setOnTouchListener(view, listener);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (view == null || listener == null) return;
        try {
            view.getViewTreeObserver().addOnGlobalLayoutListener(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener listener, View... views) {
        if (views == null || listener == null) return;
        try {
            for (View view : views) {
                if (view == null) continue;
                addOnGlobalLayoutListener(view, listener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setOnEditorActionListener(EditText et, TextView.OnEditorActionListener listener) {
        if (et == null || listener == null) return;
        try {
            et.setOnEditorActionListener(listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setOnEditorActionListener(TextView.OnEditorActionListener listener, EditText... ets) {
        if (ets == null || listener == null) return;
        try {
            for (EditText et : ets) {
                if (et == null) continue;
                setOnEditorActionListener(et, listener);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addOnPageChangeListener(ViewPager viewPager, ViewPager.OnPageChangeListener listener) {
        if (viewPager == null || listener == null) return;
        try {
            viewPager.addOnPageChangeListener(listener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}