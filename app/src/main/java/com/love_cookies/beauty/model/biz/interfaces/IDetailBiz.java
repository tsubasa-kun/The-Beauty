package com.love_cookies.beauty.model.biz.interfaces;

import com.love_cookies.cookie_library.interfaces.CallBack;

/**
 * Created by xiekun on 2016/8/3 0003.
 *
 * 图片详情页事件逻辑接口
 */
public interface IDetailBiz {
    void downloadFile(String url, String imagePath, CallBack callBack);
}
