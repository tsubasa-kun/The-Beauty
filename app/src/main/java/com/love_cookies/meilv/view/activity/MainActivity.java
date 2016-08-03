package com.love_cookies.meilv.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;

import com.love_cookies.cookie_library.activity.BaseFragmentActivity;
import com.love_cookies.cookie_library.application.ActivityCollections;
import com.love_cookies.cookie_library.utils.ToastUtils;
import com.love_cookies.meilv.R;
import com.love_cookies.meilv.view.fragment.MeiLvViewPagerFragment;

import org.xutils.view.annotation.ContentView;

/**
 * Created by xiekun on 2016/7/22 0022.
 *
 * 主页
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseFragmentActivity {

    private long exitTime;

    @Override
    public void initWidget(Bundle savedInstanceState) {
        MeiLvViewPagerFragment fragment = MeiLvViewPagerFragment.getInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public void widgetClick(View view) {

    }

    /**
     * 双击返回退出应用
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                ToastUtils.showWarning(this, R.string.exit_tip);
                exitTime = System.currentTimeMillis();
            } else {
                ActivityCollections.getInstance().finishAllActivity();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
