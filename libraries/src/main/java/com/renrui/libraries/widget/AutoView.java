package com.renrui.libraries.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.renrui.libraries.util.LibUtility;

public class AutoView extends ViewGroup {

    private final int PADDING_HOR = 0;// 水平方向padding
    private final int TEXT_MARGIN = 0;
    private int SIDE_MARGIN = 0;// 左右间距
    public int PADDING_VERTICAL = 0;// 垂直方向padding

    View childItem;
    int childItemMeasuredWidth = 0;
    int childItemMeasuredHeight = 0;
    int childItemCounts = 0;
    int x = 0;
    int y = 0;
    public int rows = 1;

    public int maxRows = 0;

    public AutoView(Context context) {
        super(context);
        initData();
    }

    public AutoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData();
    }

    public AutoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        setPadding_vertical(5);
        setSide_margin(5);
    }

    private int getPadding_vertical() {
        return PADDING_VERTICAL;
    }

    private void setPadding_vertical(int value) {
        PADDING_VERTICAL = LibUtility.dp2px(value);
    }

    private int getSide_margin() {
        return SIDE_MARGIN;
    }

    private void setSide_margin(int value) {
        SIDE_MARGIN = LibUtility.dp2px(value);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        childItemCounts = getChildCount();
        int autualWidth = r - l;
        x = getSide_margin();// 横坐标开始
        y = 0;// 纵坐标开始
        rows = 1;
        for (int i = 0; i < childItemCounts; i++) {
            childItem = getChildAt(i);
            childItem.setBackgroundColor(Color.TRANSPARENT);
            childItemMeasuredWidth = childItem.getMeasuredWidth();
            childItemMeasuredHeight = childItem.getMeasuredHeight();
            x += childItemMeasuredWidth + TEXT_MARGIN;

            if (i == 0) {
                x = childItemMeasuredWidth + getSide_margin();
                y = childItemMeasuredHeight;
            }
            // 换行
            if (x > autualWidth) {
                x = childItemMeasuredWidth + getSide_margin();
                rows++;
                y += (childItemMeasuredHeight + TEXT_MARGIN);
            }

            if (i == 0) {
                childItem.layout(x - childItemMeasuredWidth - TEXT_MARGIN, y - childItemMeasuredHeight, x - TEXT_MARGIN, y);
            } else {
                childItem.layout(x - childItemMeasuredWidth, y - childItemMeasuredHeight, x, y);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        x = 0;// 横坐标
        y = 0;// 纵坐标
        rows = 1;// 总行数

        // 实际宽度
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int actualWidth = specWidth;

        if (actualWidth == 0) {
            setMeasuredDimension(0, y);
            return;
        }

        childItemCounts = getChildCount();
        for (int index = 0; index < childItemCounts; index++) {
            childItem = getChildAt(index);
            childItem.setPadding(PADDING_HOR, getPadding_vertical(), PADDING_HOR, getPadding_vertical());
            childItem.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            childItemMeasuredWidth = childItem.getMeasuredWidth();
            childItemMeasuredHeight = childItem.getMeasuredHeight();
            x += childItemMeasuredWidth + TEXT_MARGIN;

            if (index == 0) {
                x = childItemMeasuredWidth + getSide_margin();
                y = (childItemMeasuredHeight + TEXT_MARGIN);
            }
            // 换行
            if (x > actualWidth) {
                rows++;
                x = childItemMeasuredWidth + getSide_margin();
                y += childItemMeasuredHeight + TEXT_MARGIN;
            }
        }

        if (maxRows > 0 && rows > maxRows) {
            y = (childItemMeasuredHeight + TEXT_MARGIN) * maxRows;
        }

        setMeasuredDimension(actualWidth, y);
    }
}