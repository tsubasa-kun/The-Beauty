package com.love_cookies.cookie_library.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by xiekun on 2016/3/27.
 *
 * 显示Toast的工具类
 */
public class ToastUtils {

	public static final void show(Context context, int resId) {
		show(context, context.getResources().getString(resId));
	}

	public static final void show(Context context, String text) {
		if (TextUtils.isEmpty(text)) {
			return;
		}
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

}
