package com.love_cookies.cookie_library.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by xiekun on 2016/3/27.
 *
 * Application基类
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

}
