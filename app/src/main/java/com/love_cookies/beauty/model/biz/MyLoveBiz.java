package com.love_cookies.beauty.model.biz;

import com.love_cookies.beauty.app.BeautyApplication;
import com.love_cookies.beauty.model.bean.BeautyBean;
import com.love_cookies.beauty.model.biz.interfaces.IMyLoveBiz;
import com.love_cookies.cookie_library.interfaces.CallBack;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.List;

/**
 * Created by xiekun on 2016/8/24 0024.
 *
 * 我的喜爱逻辑
 */
public class MyLoveBiz implements IMyLoveBiz {
    @Override
    public void getBeauty(int page, CallBack callBack) {
        try {
            DbManager db = x.getDb(BeautyApplication.daoConfig);
            List<BeautyBean.ResultsEntity> beauty = db.selector(BeautyBean.ResultsEntity.class).limit(10).offset(page * 10).findAll();
            if (beauty != null) {
                callBack.onSuccess(beauty);
            } else {
                callBack.onFailed(1);
            }
        } catch (Exception e) {
            callBack.onFailed(1);
        }
    }
}
