package com.love_cookies.meilv.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.love_cookies.meilv.model.bean.MeiLvBean;
import com.love_cookies.meilv.view.fragment.CardFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeiLvPagerAdapter extends FragmentStatePagerAdapter {
    private List<MeiLvBean.ResultsEntity> mPostList;
    private List<Fragment> mFragments = new ArrayList();

    public MeiLvPagerAdapter(FragmentManager paramFragmentManager, List<MeiLvBean.ResultsEntity> paramList) {
        super(paramFragmentManager);
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            MeiLvBean.ResultsEntity localAppModel = (MeiLvBean.ResultsEntity) localIterator.next();
            this.mFragments.add(CardFragment.getInstance(localAppModel));
        }
        this.mPostList = paramList;
    }

    public void addMeiLvList(List<MeiLvBean.ResultsEntity> meiLvList) {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = meiLvList.iterator();
        while (localIterator.hasNext())
            localArrayList.add(CardFragment.getInstance((MeiLvBean.ResultsEntity) localIterator.next()));
        if (this.mFragments == null)
            this.mFragments = new ArrayList();
        this.mFragments.addAll(localArrayList);
        this.mPostList.addAll(meiLvList);
    }

    public List<MeiLvBean.ResultsEntity> getCardList() {
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

    public void setMeiLvList(List<MeiLvBean.ResultsEntity> meiLvList) {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = meiLvList.iterator();
        while (localIterator.hasNext())
            localArrayList.add(CardFragment.getInstance((MeiLvBean.ResultsEntity) localIterator.next()));
        this.mFragments = localArrayList;
        this.mPostList = meiLvList;
    }

    public void setFragments(List<Fragment> paramList) {
        this.mFragments = paramList;
    }
}