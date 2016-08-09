package com.love_cookies.beauty.view.activity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.love_cookies.beauty.R;
import com.love_cookies.beauty.app.BeautyApplication;
import com.love_cookies.beauty.model.bean.BeautyBean;
import com.love_cookies.cookie_library.activity.BaseActivity;
import com.love_cookies.cookie_library.utils.ProgressDialogUtils;
import com.love_cookies.cookie_library.utils.ToastUtils;
import com.love_cookies.cookie_library.widget.PinchImageView;
import com.love_cookies.beauty.presenter.DetailPresenter;
import com.love_cookies.beauty.view.interfaces.IDetail;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * Created by xiekun on 2016/7/27 0027.
 *
 * 图片详情页
 */
@ContentView(R.layout.activity_detail)
public class DetailActivity extends BaseActivity implements IDetail, View.OnLongClickListener {

    private BeautyBean.ResultsEntity mBeauty;
    private ActionSheetDialog actionSheetDialog = null;
    private String imagePath;

    @ViewInject(R.id.root_view)
    private RelativeLayout rootView;
    @ViewInject(R.id.image_iv)
    private PinchImageView imageView;

    private DetailPresenter detailPresenter = new DetailPresenter(this);

    @Override
    public void initWidget(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
        mBeauty = getIntent().getExtras().getParcelable("beauty");
        rootView.setBackgroundColor(Color.parseColor(mBeauty.getBackgroundColor()));
        x.image().bind(imageView, mBeauty.getUrl(), BeautyApplication.NormalImageOptions);
        imageView.setOnLongClickListener(this);

        imagePath = BeautyApplication.FILE_PATH + mBeauty.get_id() + ".jpg";

        String[] stringItems = getResources().getStringArray(R.array.image_item);
        actionSheetDialog = new ActionSheetDialog(this, stringItems, null);
        actionSheetDialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, final View view, int position, long id) {
                ProgressDialogUtils.showProgress(DetailActivity.this, true);
                switch (position) {
                    case 0:
                        downloadFile(mBeauty.getUrl());
                        actionSheetDialog.dismiss();
                        break;
                    case 1:
                        getAndSetWallpaper(mBeauty.getUrl());
                        actionSheetDialog.dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void widgetClick(View view) {

    }

    @Override
    public boolean onLongClick(View v) {
        actionSheetDialog.isTitleShow(false).show();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (actionSheetDialog != null && actionSheetDialog.isShowing()) {
            actionSheetDialog.dismiss();
        }
    }

    /**
     * 下载文件
     * @param url
     */
    public void downloadFile(String url) {
        File file = new File(imagePath);
        if (!file.exists()) {
            detailPresenter.downloadFile(url, imagePath);
        } else {
            ProgressDialogUtils.hideProgress();
            ToastUtils.showInfo(DetailActivity.this, R.string.image_exists);
        }
    }

    /**
     * 获取设置壁纸的资源
     */
    public void getAndSetWallpaper(String url) {
        File file = new File(imagePath);
        if (!file.exists()) {
            detailPresenter.getWallpaper(url, imagePath);
        } else {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            setPhoneWallpaper(bitmap);
        }
    }

    /**
     * 设置为壁纸
     * @param bitmap
     */
    public void setPhoneWallpaper(Bitmap bitmap) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setBitmap(bitmap);
            ProgressDialogUtils.hideProgress();
            ToastUtils.showSuccess(DetailActivity.this, R.string.wallpaper_success);
        } catch (Exception e) {
            ProgressDialogUtils.hideProgress();
            ToastUtils.showError(DetailActivity.this, R.string.wallpaper_faile);
        }
    }

    @Override
    public void downloadFileSuccess() {
        ProgressDialogUtils.hideProgress();
        ToastUtils.showSuccess(DetailActivity.this, R.string.image_save_success);
    }

    @Override
    public void downloadFileFailed() {
        ProgressDialogUtils.hideProgress();
        ToastUtils.showError(DetailActivity.this, R.string.image_save_faile);
    }

    @Override
    public void getWallpaperSuccess() {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        setPhoneWallpaper(bitmap);
    }

    @Override
    public void getWallpaperFailed() {
        ProgressDialogUtils.hideProgress();
        ToastUtils.showError(DetailActivity.this, R.string.wallpaper_faile);
    }
}
