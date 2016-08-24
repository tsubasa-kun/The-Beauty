package com.love_cookies.beauty.presenter;

import com.love_cookies.beauty.model.bean.BeautyBean;
import com.love_cookies.beauty.model.biz.MyLoveBiz;
import com.love_cookies.beauty.view.interfaces.IMyLove;
import com.love_cookies.cookie_library.interfaces.CallBack;

import java.util.List;

/**
 * Created by xiekun on 2016/8/24 0024.
 *
 * 我的喜爱 Presenter
 */
public class MyLovePresenter {

    private MyLoveBiz myLoveBiz;
    private IMyLove iMyLove;

    public MyLovePresenter(IMyLove iMyLove) {
        myLoveBiz = new MyLoveBiz();
        this.iMyLove = iMyLove;
    }

    public void getBeauty(int page) {
        myLoveBiz.getBeauty(page, new CallBack() {
            @Override
            public void onSuccess(Object result) {
                iMyLove.getBeautySuccess((List<BeautyBean.ResultsEntity>)result);
            }

            @Override
            public void onFailed(Object msg) {
                iMyLove.getBeautyFailed();
            }
        });
    }
}
