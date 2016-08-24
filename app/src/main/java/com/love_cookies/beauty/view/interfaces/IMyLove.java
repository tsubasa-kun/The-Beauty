package com.love_cookies.beauty.view.interfaces;

import com.love_cookies.beauty.model.bean.BeautyBean;

import java.util.List;

/**
 * Created by xiekun on 2016/8/24 0024.
 *
 * 我的喜爱 View接口
 */
public interface IMyLove {

    /**
     * 获取图片
     * @param page
     */
    void getBeauty(int page);

    /**
     * 获取图片成功
     * @param beautyList
     */
    void getBeautySuccess(List<BeautyBean.ResultsEntity> beautyList);

    /**
     * 获取图片失败
     */
    void getBeautyFailed();
}
