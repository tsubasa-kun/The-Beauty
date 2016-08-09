package com.love_cookies.beauty.presenter;

import com.love_cookies.cookie_library.interfaces.CallBack;
import com.love_cookies.beauty.model.biz.DetailBiz;
import com.love_cookies.beauty.view.interfaces.IDetail;

/**
 * Created by xiekun on 2016/8/3 0003.
 *
 * 图片详情页Presenter
 */
public class DetailPresenter {

    private DetailBiz detailBiz;
    private IDetail iDetail;

    public DetailPresenter(IDetail iDetail) {
        detailBiz = new DetailBiz();
        this.iDetail = iDetail;
    }

    public void downloadFile(String url, String imagePath) {
        detailBiz.downloadFile(url, imagePath, new CallBack() {
            @Override
            public void onSuccess(Object result) {
                iDetail.downloadFileSuccess();
            }

            @Override
            public void onFailed(Object msg) {
                iDetail.downloadFileFailed();
            }
        });
    }

    public void getWallpaper(String url, String imagePath) {
        detailBiz.downloadFile(url, imagePath, new CallBack() {
            @Override
            public void onSuccess(Object result) {
                iDetail.getWallpaperSuccess();
            }

            @Override
            public void onFailed(Object msg) {
                iDetail.getWallpaperFailed();
            }
        });
    }

}
