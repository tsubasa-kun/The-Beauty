package com.love_cookies.beauty.model.biz.interfaces;

import com.love_cookies.cookie_library.interfaces.CallBack;

/**
 * Created by xiekun on 2016/7/22 0022.
 *
 * 获取美女图片的逻辑接口
 */
public interface IBeautyBiz {
    void getBeauty(int page, CallBack callBack);
}
