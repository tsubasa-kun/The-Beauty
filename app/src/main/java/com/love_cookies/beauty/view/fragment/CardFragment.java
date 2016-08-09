package com.love_cookies.beauty.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.love_cookies.cookie_library.fragment.BaseFragment;
import com.love_cookies.cookie_library.utils.CircularAnimUtils;
import com.love_cookies.beauty.app.BeautyApplication;
import com.love_cookies.beauty.model.bean.BeautyBean;
import com.love_cookies.beauty.R;
import com.love_cookies.beauty.view.activity.DetailActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by xiekun on 2016/7/22 0022.
 *
 * 每张卡片Fragment
 */
@ContentView(R.layout.fragment_card)
public class CardFragment extends BaseFragment {

    private BeautyBean.ResultsEntity mBeauty;

    @ViewInject(R.id.card_root_view)
    private RelativeLayout cardRootView;
    @ViewInject(R.id.image_cover)
    private ImageView coverImageView;

    public static CardFragment getInstance(BeautyBean.ResultsEntity beauty) {
        CardFragment localCardFragment = new CardFragment();
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("beauty", beauty);
        localCardFragment.setArguments(localBundle);
        return localCardFragment;
    }

    @Override
    public void initWidget(Bundle savedInstanceState) {
        mBeauty = getArguments().getParcelable("beauty");
        x.image().bind(coverImageView, mBeauty.getUrl(), BeautyApplication.NormalImageOptions);
        cardRootView.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("beauty", mBeauty);
        intent.putExtras(bundle);
        CircularAnimUtils.startActivity(getActivity(), intent, view, Color.parseColor(mBeauty.getBackgroundColor()));
    }

    public void onDestroy() {
        coverImageView.setImageBitmap(null);
        super.onDestroy();
    }

    public void onDestroyView() {
        coverImageView.setImageBitmap(null);
        super.onDestroyView();
    }
}