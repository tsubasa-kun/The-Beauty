package com.love_cookies.beauty.model.biz.interfaces;

import com.love_cookies.beauty.model.bean.BeautyBean;
import com.love_cookies.cookie_library.interfaces.CallBack;

/**
 * Created by xiekun on 2016/8/3 0003.
 *
 * 图片详情页事件逻辑接口
 */
public interface IDetailBiz {

    /**
     * 是否喜欢
     * @param beauty
     */
    boolean isLove(BeautyBean.ResultsEntity beauty);

    /**
     * 下载文件
     * @param url
     * @param imagePath
     * @param callBack
     */
    void downloadFile(String url, String imagePath, CallBack callBack);

    /**
     * 喜欢
     * @param beauty
     * @param callBack
     */
    void doLove(BeautyBean.ResultsEntity beauty, CallBack callBack);

    /**
     * 不喜欢
     * @param beauty
     * @param callBack
     */
    void doUnLove(BeautyBean.ResultsEntity beauty, CallBack callBack);
}
