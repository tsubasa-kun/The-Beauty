package com.love_cookies.meilv.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.love_cookies.cookie_library.fragment.BaseFragment;
import com.love_cookies.cookie_library.utils.CircularAnimUtils;
import com.love_cookies.meilv.app.MeiLvApplication;
import com.love_cookies.meilv.model.bean.MeiLvBean;
import com.love_cookies.meilv.R;
import com.love_cookies.meilv.view.activity.DetailActivity;

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

    private MeiLvBean.ResultsEntity mMeiLv;

    @ViewInject(R.id.card_root_view)
    private RelativeLayout cardRootView;
    @ViewInject(R.id.image_cover)
    private ImageView coverImageView;

    public static CardFragment getInstance(MeiLvBean.ResultsEntity meiLv) {
        CardFragment localCardFragment = new CardFragment();
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("meiLv", meiLv);
        localCardFragment.setArguments(localBundle);
        return localCardFragment;
    }

    @Override
    public void initWidget(Bundle savedInstanceState) {
        mMeiLv = getArguments().getParcelable("meiLv");
        x.image().bind(coverImageView, mMeiLv.getUrl(), MeiLvApplication.NormalImageOptions);
        cardRootView.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("meiLv", mMeiLv);
        intent.putExtras(bundle);
        CircularAnimUtils.startActivity(getActivity(), intent, view, Color.parseColor(mMeiLv.getBackgroundColor()));
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