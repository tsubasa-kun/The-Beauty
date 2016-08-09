package com.love_cookies.cookie_library.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

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

	public static final void showSuccess(Context context, int resId) {
		showSuccess(context, context.getResources().getString(resId));
	}

	public static final void showSuccess(Context context, String text) {
		if (TextUtils.isEmpty(text)) {
			return;
		}
		TastyToast.makeText(context, text, TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
	}

	public static final void showWarning(Context context, int resId) {
		showWarning(context, context.getResources().getString(resId));
	}

	public static final void showWarning(Context context, String text) {
		if (TextUtils.isEmpty(text)) {
			return;
		}
		TastyToast.makeText(context, text, TastyToast.LENGTH_SHORT, TastyToast.WARNING);
	}

	public static final void showError(Context context, int resId) {
		showError(context, context.getResources().getString(resId));
	}

	public static final void showError(Context context, String text) {
		if (TextUtils.isEmpty(text)) {
			return;
		}
		TastyToast.makeText(context, text, TastyToast.LENGTH_SHORT, TastyToast.ERROR);
	}

	public static final void showInfo(Context context, int resId) {
		showInfo(context, context.getResources().getString(resId));
	}

	public static final void showInfo(Context context, String text) {
		if (TextUtils.isEmpty(text)) {
			return;
		}
		TastyToast.makeText(context, text, TastyToast.LENGTH_SHORT, TastyToast.INFO);
	}

	public static final void showDefault(Context context, int resId) {
		showDefault(context, context.getResources().getString(resId));
	}

	public static final void showDefault(Context context, String text) {
		if (TextUtils.isEmpty(text)) {
			return;
		}
		TastyToast.makeText(context, text, TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);
	}

}

