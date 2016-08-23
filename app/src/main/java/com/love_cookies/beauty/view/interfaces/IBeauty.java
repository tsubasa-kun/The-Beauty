package com.love_cookies.beauty.view.interfaces;

import com.love_cookies.beauty.model.bean.BeautyBean;

/**
 * Created by jk on 2016/7/22 0022.
 */
public interface IBeauty {
    /**
     * 加载数据
     * @param beautyBean
     */
    void fetchData(BeautyBean beautyBean);

    /**
     * 加载数据失败
     */
    void fetchDataFailed();
}
