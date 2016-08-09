package com.love_cookies.beauty.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.love_cookies.beauty.model.bean.BeautyBean;
import com.love_cookies.beauty.view.fragment.CardFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BeautyPagerAdapter extends FragmentStatePagerAdapter {
    private List<BeautyBean.ResultsEntity> mPostList;
    private List<Fragment> mFragments = new ArrayList();

    public BeautyPagerAdapter(FragmentManager paramFragmentManager, List<BeautyBean.ResultsEntity> paramList) {
        super(paramFragmentManager);
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            BeautyBean.ResultsEntity localAppModel = (BeautyBean.ResultsEntity) localIterator.next();
            this.mFragments.add(CardFragment.getInstance(localAppModel));
        }
        this.mPostList = paramList;
    }

    public void addBeautyList(List<BeautyBean.ResultsEntity> beautyList) {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = beautyList.iterator();
        while (localIterator.hasNext())
            localArrayList.add(CardFragment.getInstance((BeautyBean.ResultsEntity) localIterator.next()));
        if (this.mFragments == null)
            this.mFragments = new ArrayList();
        this.mFragments.addAll(localArrayList);
        this.mPostList.addAll(beautyList);
    }

    public List<BeautyBean.ResultsEntity> getCardList() {
        return this.mPostList;
    }

    public int getCount() {
        return this.mFragments.size();
    }

    public List<Fragment> getFragments() {
        return this.mFragments;
    }

    public Fragment getItem(int paramInt) {
        return this.mFragments.get(paramInt);
    }

    public void setBeautyList(List<BeautyBean.ResultsEntity> beautyList) {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = beautyList.iterator();
        while (localIterator.hasNext())
            localArrayList.add(CardFragment.getInstance((BeautyBean.ResultsEntity) localIterator.next()));
        this.mFragments = localArrayList;
        this.mPostList = beautyList;
    }

    public void setFragments(List<Fragment> paramList) {
        this.mFragments = paramList;
    }
}