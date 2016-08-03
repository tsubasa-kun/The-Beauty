package com.love_cookies.meilv.presenter;

import com.love_cookies.cookie_library.interfaces.CallBack;
import com.love_cookies.meilv.model.bean.MeiLvBean;
import com.love_cookies.meilv.model.biz.MeiLvBiz;
import com.love_cookies.meilv.view.interfaces.IMeiLv;

/**
 * Created by xiekun on 2016/7/22 0022.
 *
 * 美女图片的Presenter
 */
public class MeiLvPresenter {

    private MeiLvBiz meiLvBiz;
    private IMeiLv iMeiLv;

    public MeiLvPresenter(IMeiLv iMeiLv) {
        meiLvBiz = new MeiLvBiz();
        this.iMeiLv = iMeiLv;
    }

    public void getMeiLv(int page) {
        meiLvBiz.getMeiLv(page, new CallBack() {
            @Override
            public void onSuccess(Object result) {
                iMeiLv.fetchData((MeiLvBean) result);
            }

            @Override
            public void onFailed(Object msg) {
                iMeiLv.fetchDataFailed();
            }
        });
    }
}
