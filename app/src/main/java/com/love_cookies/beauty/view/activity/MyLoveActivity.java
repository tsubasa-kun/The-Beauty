package com.love_cookies.beauty.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.love_cookies.beauty.R;
import com.love_cookies.beauty.app.BeautyApplication;
import com.love_cookies.beauty.model.bean.BeautyBean;
import com.love_cookies.beauty.presenter.MyLovePresenter;
import com.love_cookies.beauty.view.interfaces.IMyLove;
import com.love_cookies.beauty.view.widget.SpacesItemDecoration;
import com.love_cookies.cookie_library.activity.BaseActivity;
import com.love_cookies.cookie_library.adapter.CommonRecyclerAdapter;
import com.love_cookies.cookie_library.adapter.CommonRecyclerViewHolder;
import com.love_cookies.cookie_library.adapter.OnRecyclerItemClickListener;
import com.love_cookies.cookie_library.utils.CircularAnimUtils;
import com.love_cookies.cookie_library.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiekun on 2016/8/24 0024.
 *
 * 个人喜爱页
 */
@ContentView(R.layout.activity_my_love)
public class MyLoveActivity extends BaseActivity implements IMyLove {

    @ViewInject(R.id.drawer_layout)
    private DrawerLayout drawerLayout;
    @ViewInject(R.id.tool_bar)
    private Toolbar toolbar;
    @ViewInject(R.id.recycler_view)
    private RecyclerView recyclerView;
    private CommonRecyclerAdapter recyclerAdapter;
    private List<BeautyBean.ResultsEntity> mData = new ArrayList<>();
    private int page = 0;

    MyLovePresenter myLovePresenter = new MyLovePresenter(this);

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

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new SpacesItemDecoration(6));
        recyclerAdapter = new CommonRecyclerAdapter<BeautyBean.ResultsEntity>(this, R.layout.item_my_love_list, mData) {
            @Override
            public void setData(CommonRecyclerViewHolder holder, BeautyBean.ResultsEntity resultsEntity) {
                x.image().bind((ImageView) holder.getView(R.id.image_view), resultsEntity.getUrl(), BeautyApplication.NormalImageOptions);
            }
        };
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Intent intent = new Intent(MyLoveActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("beauty", mData.get(position));
                intent.putExtras(bundle);
                CircularAnimUtils.startActivity(MyLoveActivity.this, intent, view, Color.parseColor(mData.get(position).getBackgroundColor()));
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return true;
            }
        });

        getBeauty(page);
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

    /**
     * 获取图片
     * @param page
     */
    @Override
    public void getBeauty(int page) {
        myLovePresenter.getBeauty(page);
    }

    /**
     * 获取图片成功
     * @param beautyList
     */
    @Override
    public void getBeautySuccess(List<BeautyBean.ResultsEntity> beautyList) {
        if (page == 0) {
            mData.clear();
        }
        mData.addAll(beautyList);
        recyclerAdapter.notifyDataSetChanged();
    }

    /**
     * 获取图片失败
     */
    @Override
    public void getBeautyFailed() {
        ToastUtils.showError(this, R.string.load_failed);
    }
}
