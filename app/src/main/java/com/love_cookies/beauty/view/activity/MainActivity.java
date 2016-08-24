package com.love_cookies.beauty.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.love_cookies.beauty.R;
import com.love_cookies.beauty.app.BeautyApplication;
import com.love_cookies.beauty.view.fragment.BeautyViewPagerFragment;
import com.love_cookies.beauty.view.interfaces.IMain;
import com.love_cookies.cookie_library.activity.BaseFragmentActivity;
import com.love_cookies.cookie_library.application.ActivityCollections;
import com.love_cookies.cookie_library.utils.CircularAnimUtils;
import com.love_cookies.cookie_library.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by xiekun on 2016/7/22 0022.
 *
 * 主页
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseFragmentActivity implements IMain {

    private long exitTime;

    @ViewInject(R.id.menu_btn)
    private ImageView menuBtn;
    @ViewInject(R.id.drawer_layout)
    private DrawerLayout drawerLayout;
    @ViewInject(R.id.navigation_view)
    private NavigationView navigationView;

    /**
     * 初始化控件
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        menuBtn.setOnClickListener(this);
        initContent();
        initNavigationView();
    }

    /**
     * 控件点击事件
     *
     * @param v
     */
    @Override
    public void widgetClick(View v) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    /**
     * 初始化内容
     */
    @Override
    public void initContent() {
        BeautyViewPagerFragment fragment = BeautyViewPagerFragment.getInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, fragment);
        transaction.commit();
    }

    /**
     * 初始化菜单视图
     */
    @Override
    public void initNavigationView() {
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int index = (int)(Math.random() * BeautyApplication.colorList.length);
                        switch (menuItem.getItemId()) {
                            case R.id.menu_home:
                                break;
                            case R.id.menu_love:
                                Intent intent = new Intent(MainActivity.this, MyLoveActivity.class);
                                int color = Color.parseColor(BeautyApplication.colorList[index]);
                                intent.putExtra("color", color);
                                CircularAnimUtils.startActivity(MainActivity.this, intent, navigationView, color);
                                break;
                            case R.id.menu_setting:
                                break;
                            case R.id.menu_about:
                                break;
                            default:
                                break;
                        }
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    /**
     * 菜单关闭和点两次返回退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (drawerLayout.isDrawerOpen(navigationView)) {//如果菜单打开就收起菜单
                drawerLayout.closeDrawers();
            } else if ((System.currentTimeMillis() - exitTime) > 2000) {//点击两次退出逻辑
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
