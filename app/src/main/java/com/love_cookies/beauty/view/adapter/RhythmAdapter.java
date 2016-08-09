package com.love_cookies.beauty.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.love_cookies.beauty.app.BeautyApplication;
import com.love_cookies.beauty.model.bean.BeautyBean;
import com.love_cookies.beauty.R;
import com.love_cookies.beauty.view.widget.RhythmLayout;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class RhythmAdapter extends BaseAdapter {

    /**
     * item的宽度
     */
    private float itemWidth;
    /**
     * 数据源
     */
    private List<BeautyBean.ResultsEntity> mBeautyList;

    private LayoutInflater mInflater;
    private Context mContext;
    private RhythmLayout mRhythmLayout;

    public RhythmAdapter(Context context, RhythmLayout rhythmLayout, List<BeautyBean.ResultsEntity> beautyList) {
        this.mContext = context;
        this.mRhythmLayout = rhythmLayout;
        this.mBeautyList = new ArrayList();
        this.mBeautyList.addAll(beautyList);
        if (context != null)
            this.mInflater = LayoutInflater.from(context);
    }

    public List<BeautyBean.ResultsEntity> getBeautyList() {
        return this.mBeautyList;
    }

    public void addCardList(List<BeautyBean.ResultsEntity> beautyList) {
        mBeautyList.addAll(beautyList);
    }

    public int getCount() {
        return this.mBeautyList.size();
    }

    public Object getItem(int position) {
        return this.mBeautyList.get(position);
    }

    public long getItemId(int paramInt) {
        return Long.parseLong((this.mBeautyList.get(paramInt)).get_id());
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout relativeLayout = (RelativeLayout) this.mInflater.inflate(R.layout.adapter_rhythm_icon, null);
        //设置item布局的大小以及Y轴的位置
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams((int) itemWidth, mContext.getResources().getDimensionPixelSize(R.dimen.rhythm_item_height)));
        relativeLayout.setTranslationY(itemWidth);

        //设置第二层RelativeLayout布局的宽和高
        RelativeLayout childRelativeLayout = (RelativeLayout) relativeLayout.getChildAt(0);
        int relativeLayoutWidth = (int) itemWidth - 2 * mContext.getResources().getDimensionPixelSize(R.dimen.rhythm_icon_margin);
        childRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(relativeLayoutWidth, mContext.getResources().getDimensionPixelSize(R.dimen.rhythm_item_height) - 2 * mContext.getResources().getDimensionPixelSize(R.dimen.rhythm_icon_margin)));

        ImageView imageIcon = (ImageView) relativeLayout.findViewById(R.id.image_icon);
        //计算ImageView的大小
        int iconSize = (relativeLayoutWidth - 2 * mContext.getResources().getDimensionPixelSize(R.dimen.rhythm_icon_margin));
        ViewGroup.LayoutParams iconParams = imageIcon.getLayoutParams();
        iconParams.width = iconSize;
        iconParams.height = iconSize;
        imageIcon.setLayoutParams(iconParams);
        //设置背景图片
        x.image().bind(imageIcon, mBeautyList.get(position).getUrl(), BeautyApplication.SquareRadiusImageOptions);

        return relativeLayout;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.mRhythmLayout.invalidateData();
    }

    public void setBeautyList(List<BeautyBean.ResultsEntity> paramList) {
        this.mBeautyList = paramList;
    }

    /**
     * 设置每个item的宽度
     */
    public void setItemWidth(float width) {
        this.itemWidth = width;
    }
}