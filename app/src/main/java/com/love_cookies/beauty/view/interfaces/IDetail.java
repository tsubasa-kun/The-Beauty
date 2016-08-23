package com.love_cookies.beauty.view.interfaces;

import com.love_cookies.beauty.model.bean.BeautyBean;

/**
 * Created by xiekun on 2016/8/3 0003.
 *
 * 图片详情页 View接口
 */
public interface IDetail {
    void downloadFileSuccess();
    void downloadFileFailed();
    void getWallpaperSuccess();
    void getWallpaperFailed();
    void doLove(BeautyBean.ResultsEntity beauty);
    void doLoveSuccess();
    void doLoveFailed();
    void doUnLove(BeautyBean.ResultsEntity beauty);
    void doUnLoveSuccess();
    void doUnLoveFailed();
}
