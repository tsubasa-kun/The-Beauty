package com.love_cookies.beauty.model.biz;

import com.google.gson.Gson;
import com.love_cookies.cookie_library.application.ActivityCollections;
import com.love_cookies.cookie_library.interfaces.CallBack;
import com.love_cookies.beauty.config.AppConfig;
import com.love_cookies.beauty.model.bean.BeautyBean;
import com.love_cookies.beauty.model.biz.interfaces.IBeautyBiz;
import com.love_cookies.beauty.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by xiekun on 2016/7/22 0022.
 *
 * 获取美女图片的逻辑
 */
public class BeautyBiz implements IBeautyBiz {

    private Gson gson = new Gson();
    private String[] colorList;

    @Override
    public void getBeauty(int page, final CallBack callBack) {
        colorList = ActivityCollections.getInstance().currentActivity().getResources().getStringArray(R.array.color_list);
        RequestParams requestParams = new RequestParams(AppConfig.API + page);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                BeautyBean beautyBean = gson.fromJson(result, BeautyBean.class);
                for (int i = 0; i < beautyBean.getResults().size(); i++) {
                    beautyBean.getResults().get(i).setBackgroundColor(colorList[i % colorList.length]);
                }
                callBack.onSuccess(beautyBean);
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
