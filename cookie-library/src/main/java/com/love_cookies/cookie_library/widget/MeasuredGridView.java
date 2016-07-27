package com.love_cookies.cookie_library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by xiekun on 2016/7/8 0008.
 *
 * 自己适配高度的GridView，适用于嵌套在ScrollView，ListView内
 */
public class MeasuredGridView extends GridView {

    public MeasuredGridView(Context context) {
        super(context);
    }

    public MeasuredGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MeasuredGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
