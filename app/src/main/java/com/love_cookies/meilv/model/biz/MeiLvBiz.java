package com.love_cookies.meilv.model.biz;

import com.google.gson.Gson;
import com.love_cookies.cookie_library.application.ActivityCollections;
import com.love_cookies.cookie_library.interfaces.CallBack;
import com.love_cookies.meilv.config.AppConfig;
import com.love_cookies.meilv.model.bean.MeiLvBean;
import com.love_cookies.meilv.model.biz.interfaces.IMeiLvBiz;
import com.love_cookies.meilv.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by xiekun on 2016/7/22 0022.
 *
 * 获取美女图片的逻辑
 */
public class MeiLvBiz implements IMeiLvBiz {

    private Gson gson = new Gson();
    private String[] colorList;

    @Override
    public void getMeiLv(int page, final CallBack callBack) {
        colorList = ActivityCollections.getInstance().currentActivity().getResources().getStringArray(R.array.color_list);
        RequestParams requestParams = new RequestParams(AppConfig.API + page);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                MeiLvBean meiLvBean = gson.fromJson(result, MeiLvBean.class);
                for (int i = 0; i < meiLvBean.getResults().size(); i++) {
                    meiLvBean.getResults().get(i).setBackgroundColor(colorList[i % colorList.length]);
                }
                callBack.onSuccess(meiLvBean);
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
