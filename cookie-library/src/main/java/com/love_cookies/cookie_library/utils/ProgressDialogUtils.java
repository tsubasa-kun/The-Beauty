package com.love_cookies.cookie_library.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.love_cookies.cookie_library.R;

/**
 * Created by xiekun on 2016/3/27.
 *
 * 显示ProgressDialog的提示工具类
 */
public class ProgressDialogUtils {

	public static ProgressDialog mDialog = null;

	public static void showProgress(Context context) {
        showProgress(context, R.string.waiting_tip, false);
	}

	public static void showProgress(Context context, int textRes) {
		showProgress(context, context.getString(textRes), false);
	}

    public static void showProgress(Context context, String text) {
        showProgress(context, text, true);
    }

    public static void showProgress(Context context, boolean cancelable) {
        showProgress(context, R.string.waiting_tip, cancelable);
    }

    public static void showProgress(Context context, int textRes, boolean cancelable) {
        showProgress(context, context.getString(textRes), cancelable);
    }

    public static void showProgress(Context context, String textRes, boolean cancelable) {
        try {
            if (mDialog == null) {
                mDialog = new ProgressDialog(context);
            }
            mDialog.setCancelable(cancelable);
            mDialog.setMessage(textRes);
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static void hideProgress() {
		try {
			if (mDialog != null) {
				mDialog.dismiss();
				mDialog = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
