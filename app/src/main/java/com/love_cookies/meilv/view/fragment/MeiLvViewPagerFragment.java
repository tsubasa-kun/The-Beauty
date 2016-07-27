package com.love_cookies.meilv.view.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.extras.viewpager.PullToRefreshViewPager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.love_cookies.cookie_library.fragment.BaseFragment;
import com.love_cookies.cookie_library.utils.ProgressDialogUtils;
import com.love_cookies.meilv.model.bean.MeiLvBean;
import com.love_cookies.meilv.presenter.MeiLvPresenter;
import com.love_cookies.meilv.utils.AnimatorUtils;
import com.love_cookies.meilv.utils.HexUtils;
import com.love_cookies.meilv.utils.ISODateUtils;
import com.love_cookies.meilv.utils.NetWorkHelper;
import com.love_cookies.meilv.view.adapter.MeiLvPagerAdapter;
import com.love_cookies.meilv.view.adapter.RhythmAdapter;
import com.love_cookies.meilv.view.interfaces.IMeiLv;
import com.love_cookies.meilv.view.interfaces.IRhythmItemListener;
import com.love_cookies.meilv.view.widget.ProgressHUD;
import com.love_cookies.meilv.view.widget.RhythmLayout;
import com.love_cookies.meilv.view.widget.ViewPagerScroller;
import com.love_cookies.meilv.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiekun on 2016/7/22 0022.
 *
 * 主页的内嵌Fragment
 */
@ContentView(R.layout.fragment_meilv_viewpager)
public class MeiLvViewPagerFragment extends BaseFragment implements IMeiLv,PullToRefreshBase.OnRefreshListener<ViewPager> {

    @ViewInject(R.id.text_time_first)
    private TextView mTimeFirstText;
    @ViewInject(R.id.text_time_second)
    private TextView mTimeSecondText;

    /**
     * 最外层的View，为了设置背景颜色而使用
     */
    @ViewInject(R.id.main_view)
    private View mMainView;
    @ViewInject(R.id.btn_rocket_to_head)
    private ImageButton mRocketToHeadBtn;
    @ViewInject(R.id.btn_side_menu_or_back)
    private Button mSideMenuOrBackBtn;
    /**
     * 钢琴布局
     */
    @ViewInject(R.id.box_rhythm)
    private RhythmLayout mRhythmLayout;
    /**
     * 可以侧拉刷新的ViewPager，其实是一个LinearLayout控件
     */
    @ViewInject(R.id.pager)
    private PullToRefreshViewPager mPullToRefreshViewPager;
    /**
     * 接收PullToRefreshViewPager中的ViewPager控件
     */
    private ViewPager mViewPager;
    /**
     * ViewPager的适配器
     */
    private MeiLvPagerAdapter mMeiLvPagerAdapter;
    /**
     * 记录上一个选项卡的颜色值
     */
    private int mPreColor;

    private boolean mHasNext = true;

    private boolean mIsRequesting;

    private boolean isAdapterUpdated;

    private int mCurrentViewPagerPage;


    private List<MeiLvBean.ResultsEntity> mMeiLvList = new ArrayList<>();

    private ProgressHUD mProgressHUD;

    private Handler mHandler = new Handler();

    /**
     * 钢琴布局的适配器
     */
    private RhythmAdapter mRhythmAdapter;

    private static MeiLvViewPagerFragment mFragment;

    private MeiLvPresenter meiLvPresenter = new MeiLvPresenter(this);

    private int page = 1;

    public static MeiLvViewPagerFragment getInstance() {
        if (mFragment == null) {
            mFragment = new MeiLvViewPagerFragment();
        }
        return mFragment;
    }

    @Override
    public void initWidget(Bundle savedInstanceState) {
        mViewPager = mPullToRefreshViewPager.getRefreshableView();
        //设置ViewPager的滚动速度
        setViewPagerScrollSpeed(mViewPager, 400);
        //设置ScrollView滚动动画延迟执行的时间
        mRhythmLayout.setScrollRhythmStartDelayTime(400);
        //设置钢琴布局的高度 高度为钢琴布局item的宽度+10dp
        int height = (int) mRhythmLayout.getRhythmItemWidth() + (int) TypedValue.applyDimension(1, 10.0F, getResources().getDisplayMetrics());
        mRhythmLayout.getLayoutParams().height = height;
        ((RelativeLayout.LayoutParams) mPullToRefreshViewPager.getLayoutParams()).bottomMargin = height;

        ProgressDialogUtils.showProgress(getContext(), true);
        meiLvPresenter.getMeiLv(page);

        mRhythmLayout.setRhythmListener(rhythmItemListener);
        mPullToRefreshViewPager.setOnRefreshListener(this);
        mViewPager.setOnPageChangeListener(onPageChangeListener);
        mRocketToHeadBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                MeiLvViewPagerFragment.this.mViewPager.setCurrentItem(0, true);
            }
        });
    }

    @Override
    public void widgetClick(View view) {

    }

    /**
     * 配置数据
     * @param meiLvBean
     */
    @Override
    public void fetchData(MeiLvBean meiLvBean) {
        List<MeiLvBean.ResultsEntity> meiLvList = meiLvBean.getResults();
        mPreColor = HexUtils.getHexColor(meiLvList.get(0).getBackgroundColor());
        updateAppAdapter(meiLvList);
        if (page == 1) {
            onAppPagerChange(0);
        }
        ProgressDialogUtils.hideProgress();
    }

    /**
     * 自定义钢琴控件的监听器
     */
    private IRhythmItemListener rhythmItemListener = new IRhythmItemListener() {
        public void onRhythmItemChanged(int paramInt) {
        }

        public void onSelected(final int paramInt) {
            MeiLvViewPagerFragment.this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    mViewPager.setCurrentItem(paramInt);
                }
            }, 100L);
        }

        public void onStartSwipe() {
        }
    };

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        public void onPageScrollStateChanged(int paramInt) {
        }

        public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
        }

        public void onPageSelected(int position) {
            onAppPagerChange(position);
            if (mHasNext && (position > -10 + mMeiLvList.size()) && !mIsRequesting && NetWorkHelper.isWifiDataEnable(getActivity())) {
                meiLvPresenter.getMeiLv(++page);
            }
        }
    };

    /**
     * 设置ViewPager的滚动速度，即每个选项卡的切换速度
     *
     * @param viewPager ViewPager控件
     * @param speed     滚动速度，毫秒为单位
     */
    private void setViewPagerScrollSpeed(ViewPager viewPager, int speed) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            ViewPagerScroller viewPagerScroller = new ViewPagerScroller(viewPager.getContext(), new OvershootInterpolator(0.6F));
            field.set(viewPager, viewPagerScroller);
            viewPagerScroller.setDuration(speed);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 改变当前选中钢琴按钮
     *
     * @param position viewPager的位置
     */
    private void onAppPagerChange(int position) {
        //执行动画，改变升起的钢琴按钮
        mRhythmLayout.showRhythmAtPosition(position);
        toggleRocketBtn(position);
        MeiLvBean.ResultsEntity post = mMeiLvList.get(position);
        //得到当前的背景颜色
        int currColor = HexUtils.getHexColor(post.getBackgroundColor());
        //执行颜色转换动画
        AnimatorUtils.showBackgroundColorAnimation(this.mMainView, mPreColor, currColor, 400);
        mPreColor = currColor;
        mTimeSecondText.setText(ISODateUtils.ISO8601Formater(post.getCreatedAt()));
    }

    /**
     * 更新适配器
     * @param meiLvList
     */
    private void updateAppAdapter(List<MeiLvBean.ResultsEntity> meiLvList) {
        if ((getActivity() == null) || (getActivity().isFinishing())) {
            return;
        }
        if (mProgressHUD != null && mProgressHUD.isShowing()) {
            this.mProgressHUD.dismiss();
            this.isAdapterUpdated = true;
        }
        if (meiLvList.isEmpty()) {
            this.mMainView.setBackgroundColor(this.mPreColor);
            return;
        }
        int size = meiLvList.size();

        if (mMeiLvPagerAdapter == null) {
            mCurrentViewPagerPage = 0;
            mMeiLvPagerAdapter = new MeiLvPagerAdapter(getActivity().getSupportFragmentManager(), meiLvList);
            mViewPager.setAdapter(mMeiLvPagerAdapter);
        } else {
            mMeiLvPagerAdapter.addMeiLvList(meiLvList);
            mMeiLvPagerAdapter.notifyDataSetChanged();
        }
        addMeiLvIconsToDock(meiLvList);

        this.mMeiLvList = mMeiLvPagerAdapter.getCardList();

        if (mViewPager.getCurrentItem() == size - 1) {
            mViewPager.setCurrentItem(1 + mViewPager.getCurrentItem(), true);
        }
    }

    /**
     * 添加小图标
     * @param meiLvList
     */
    private void addMeiLvIconsToDock(final List<MeiLvBean.ResultsEntity> meiLvList) {
        if (mRhythmAdapter == null) {
            resetRhythmLayout(meiLvList);
            return;
        }
        mRhythmAdapter.addCardList(meiLvList);
        mRhythmAdapter.notifyDataSetChanged();
    }

    /**
     * 重置钢琴控件数据源
     * @param meiLvList
     */
    private void resetRhythmLayout(List<MeiLvBean.ResultsEntity> meiLvList) {
        if (getActivity() == null)
            return;
        if (meiLvList == null)
            meiLvList = new ArrayList<>();
        mRhythmAdapter = new RhythmAdapter(getActivity(), mRhythmLayout, meiLvList);
        mRhythmLayout.setAdapter(mRhythmAdapter);
    }

    /**
     * viewPager刷新或加载更多监听
     *
     * @param pullToRefreshBase
     */
    public void onRefresh(PullToRefreshBase<ViewPager> pullToRefreshBase) {
        if (this.mIsRequesting)
            return;
        if (pullToRefreshBase.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {//最右
            mIsRequesting = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    page = 1;
                    meiLvPresenter.getMeiLv(page);
                    mPullToRefreshViewPager.onRefreshComplete();
                    mIsRequesting = false;
                }
            }, 2000);

        } else if (pullToRefreshBase.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {//最左
            mIsRequesting = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPullToRefreshViewPager.onRefreshComplete();
                    mIsRequesting = false;
                }
            }, 2000);
        }
    }

    /**
     * 根据当前viewPager的位置决定右上方的火箭图案是否显示
     *
     * @param position
     */
    private void toggleRocketBtn(int position) {
        if (position > 1) {
            if (mRocketToHeadBtn.getVisibility() == View.GONE) {
                mRocketToHeadBtn.setVisibility(View.VISIBLE);
                AnimatorUtils.animViewFadeIn(this.mRocketToHeadBtn);
            }
        } else if (this.mRocketToHeadBtn.getVisibility() == View.VISIBLE) {
            AnimatorUtils.animViewFadeOut(this.mRocketToHeadBtn).addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator paramAnimator) {
                }

                public void onAnimationEnd(Animator paramAnimator) {
                    MeiLvViewPagerFragment.this.mRocketToHeadBtn.setVisibility(View.GONE);
                }

                public void onAnimationRepeat(Animator paramAnimator) {
                }

                public void onAnimationStart(Animator paramAnimator) {
                }
            });
        }
//        mTimeFirstText.setText((position + 1) + "");
    }
}
