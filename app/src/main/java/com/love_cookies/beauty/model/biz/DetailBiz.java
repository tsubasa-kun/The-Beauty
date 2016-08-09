package com.love_cookies.beauty.model.biz;

import com.love_cookies.cookie_library.interfaces.CallBack;
import com.love_cookies.beauty.model.biz.interfaces.IDetailBiz;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by xiekun on 2016/8/3 0003.
 *
 * 图片详情页事件逻辑
 */
public class DetailBiz implements IDetailBiz {

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

}
