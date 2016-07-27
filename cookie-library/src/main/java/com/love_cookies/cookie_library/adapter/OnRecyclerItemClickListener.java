package com.love_cookies.cookie_library.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiekun on 2016/06/29.
 *
 * RecyclerView通用适配器的点击监听器接口
 */
public interface OnRecyclerItemClickListener<T> {
    void onItemClick(ViewGroup parent, View view, T t, int position);

    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}