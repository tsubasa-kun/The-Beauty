package com.love_cookies.beauty.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.love_cookies.beauty.R;
import com.love_cookies.cookie_library.activity.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by xiekun on 2016/8/24 0024.
 *
 * 个人喜爱页
 */
@ContentView(R.layout.activity_my_love)
public class MyLoveActivity extends BaseActivity {

    @ViewInject(R.id.drawer_layout)
    private DrawerLayout drawerLayout;
    @ViewInject(R.id.tool_bar)
    private Toolbar toolbar;
    @ViewInject(R.id.recycler_view)
    private RecyclerView recyclerView;

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        int color = getIntent().getIntExtra("color", Color.parseColor("#FF4444"));
        toolbar.setTitle("");
        toolbar.setBackgroundColor(color);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 控件的点击事件
     * @param view
     */
    @Override
    public void widgetClick(View view) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
