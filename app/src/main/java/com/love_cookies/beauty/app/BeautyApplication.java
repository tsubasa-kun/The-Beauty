package com.love_cookies.beauty.app;

import android.widget.ImageView;

import com.love_cookies.beauty.R;
import com.love_cookies.cookie_library.application.BaseApplication;
import com.love_cookies.cookie_library.utils.SDCardUtils;
import com.love_cookies.cookie_library.utils.ScreenUtils;

import org.xutils.DbManager;
import org.xutils.image.ImageOptions;

import java.io.File;

/**
 * Created by xiekun on 2016/7/22 0022.
 *
 * 应用的Application
 */
public class BeautyApplication extends BaseApplication {

    //一般图片设置
    public static ImageOptions NormalImageOptions = null;
    //圆形图片设置
    public static ImageOptions RoundImageOptions = null;
    //方形圆角图片设置
    public static ImageOptions SquareRadiusImageOptions = null;
    //图片文件夹路径
    public static final String FILE_PATH = SDCardUtils.getSDCardPath() + "The_Beauty/Images/";
    //数据库路径
    public static final String DB_PATH = SDCardUtils.getSDCardPath() + "The_Beauty/DateBase/";
    //全局数据库
    public static DbManager.DaoConfig daoConfig;
    //全局颜色列表
    public static String[] colorList;

    @Override
    public void onCreate() {
        super.onCreate();
        colorList = getResources().getStringArray(R.array.color_list);
        initImageOptions();
        initFolder();
        initDbConfig();
    }

    /**
     * 初始化图片设置
     */
    public void initImageOptions() {
        NormalImageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();

        RoundImageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(true)
                .build();

        SquareRadiusImageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setSquare(true)
                .setRadius(ScreenUtils.dp2px(this, 12))
                .build();
    }

    /**
     * 初始化文件夹
     */
    public void initFolder() {
        File dir = new File(FILE_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 初始化本地数据库
     */
    public void initDbConfig() {
        daoConfig = new DbManager.DaoConfig()
                .setDbName("the_beauty")
                .setDbDir(new File(DB_PATH))
                .setDbVersion(1)
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                    }
                });
    }
}
