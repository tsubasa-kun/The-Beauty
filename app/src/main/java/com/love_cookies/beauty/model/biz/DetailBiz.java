package com.love_cookies.beauty.model.biz;

import com.love_cookies.beauty.app.BeautyApplication;
import com.love_cookies.beauty.model.bean.BeautyBean;
import com.love_cookies.beauty.model.biz.interfaces.IDetailBiz;
import com.love_cookies.cookie_library.interfaces.CallBack;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by xiekun on 2016/8/3 0003.
 *
 * 图片详情页事件逻辑
 */
public class DetailBiz implements IDetailBiz {

    /**
     * 下载文件
     * @param url
     * @param imagePath
     * @param callBack
     */
    @Override
    public void downloadFile(String url, String imagePath, final CallBack callBack) {
        RequestParams requestParams = new RequestParams(url);
        requestParams.setSaveFilePath(imagePath);
        x.http().get(requestParams, new Callback.CommonCallback<File>() {
            @Override
            public void onSuccess(File result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onFailed(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 喜欢
     * @param beauty
     * @param callBack
     */
    @Override
    public void doLove(BeautyBean.ResultsEntity beauty, CallBack callBack) {
        try {
            DbManager db = x.getDb(BeautyApplication.daoConfig);
            db.save(beauty);
            callBack.onSuccess(0);
        } catch (Exception e) {
            callBack.onFailed(1);
        }
    }

    /**
     * 不喜欢
     * @param beauty
     * @param callBack
     */
    @Override
    public void doUnLove(BeautyBean.ResultsEntity beauty, CallBack callBack) {
        try {
            DbManager db = x.getDb(BeautyApplication.daoConfig);
            db.delete(BeautyBean.ResultsEntity.class, WhereBuilder.b("_id", "=", beauty.get_id()));
            callBack.onSuccess(0);
        } catch (Exception e) {
            callBack.onFailed(1);
        }
    }

}
