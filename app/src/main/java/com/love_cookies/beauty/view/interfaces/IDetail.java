package com.love_cookies.beauty.view.interfaces;

import com.love_cookies.beauty.model.bean.BeautyBean;

/**
 * Created by xiekun on 2016/8/3 0003.
 *
 * 图片详情页 View接口
 */
public interface IDetail {

    /**
     * 下载文件成功
     */
    void downloadFileSuccess();

    /**
     * 下载文件失败
     */
    void downloadFileFailed();

    /**
     * 获取壁纸成功
     */
    void getWallpaperSuccess();

    /**
     * 获取壁纸失败
     */
    void getWallpaperFailed();

    /**
     * 喜欢
     * @param beauty
     */
    void doLove(BeautyBean.ResultsEntity beauty);

    /**
     * 喜欢成功
     */
    void doLoveSuccess();

    /**
     * 喜欢失败
     */
    void doLoveFailed();

    /**
     * 不喜欢
     * @param beauty
     */
    void doUnLove(BeautyBean.ResultsEntity beauty);

    /**
     * 不喜欢成功
     */
    void doUnLoveSuccess();

    /**
     * 不喜欢失败
     */
    void doUnLoveFailed();
}
