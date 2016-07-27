package com.love_cookies.cookie_library.network;

import com.love_cookies.cookie_library.utils.NetUtils;

/**
 * Created by xiekun on 2016/03/31.
 *
 * 网络状况发生改变的时候的回调接口
 */
public interface NetChangeCallBack {
    void onNetConnected(NetUtils.NetType type);//网络连接

    void onNetDisConnected();//网络断开
}
