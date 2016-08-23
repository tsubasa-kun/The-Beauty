package com.love_cookies.beauty.presenter;

import com.love_cookies.beauty.model.bean.BeautyBean;
import com.love_cookies.beauty.model.biz.BeautyBiz;
import com.love_cookies.beauty.view.interfaces.IBeauty;
import com.love_cookies.cookie_library.interfaces.CallBack;

/**
 * Created by xiekun on 2016/7/22 0022.
 *
 * 美女图片的Presenter
 */
public class BeautyPresenter {

    private BeautyBiz beautyBiz;
    private IBeauty iBeauty;

    public BeautyPresenter(IBeauty iBeauty) {
        beautyBiz = new BeautyBiz();
        this.iBeauty = iBeauty;
    }

    /**
     * 获取数据
     * @param page
     */
    public void getBeauty(int page) {
        beautyBiz.getBeauty(page, new CallBack() {
            @Override
            public void onSuccess(Object result) {
                iBeauty.fetchData((BeautyBean) result);
            }

            @Override
            public void onFailed(Object msg) {
                iBeauty.fetchDataFailed();
            }
        });
    }
}
