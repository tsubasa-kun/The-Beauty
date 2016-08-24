package com.love_cookies.beauty.model.biz.interfaces;

import com.love_cookies.cookie_library.interfaces.CallBack;

/**
 * Created by xiekun on 2016/8/24 0024.
 *
 * 我的喜爱逻辑接口
 */
public interface IMyLoveBiz {
    /**
     * 获取图片
     * @param page
     * @param callBack
     */
    void getBeauty(int page, CallBack callBack);
}
