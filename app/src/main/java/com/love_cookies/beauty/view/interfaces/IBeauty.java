package com.love_cookies.beauty.view.interfaces;

import com.love_cookies.beauty.model.bean.BeautyBean;

/**
 * Created by jk on 2016/7/22 0022.
 */
public interface IBeauty {
    void fetchData(BeautyBean beautyBean);
    void fetchDataFailed();
}
