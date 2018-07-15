package com.love_cookies.beauty.config;

import java.net.URLEncoder;

/**
 * Created by xiekun on 2016/7/22 0022.
 *
 * app设置
 */
public class AppConfig {
    //来自干货集中营的福利api
    public static final String API = "http://gank.io/api/data/" + URLEncoder.encode("福利") + "/10/";
}
