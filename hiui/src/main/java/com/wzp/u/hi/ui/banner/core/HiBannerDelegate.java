package com.wzp.u.hi.ui.banner.core;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.wzp.u.hi.ui.banner.HiBanner;
import com.wzp.u.hi.ui.banner.indicator.HiIndicator;
import com.wzp.u.hiui.R;

import java.util.List;

/**
 * HiBanner 的控制器
 * 辅助HiBanner 完成各种的控制
 * 将HiBanner的一些逻辑内聚在这，保证暴露给使用者的 HiBanner干净整洁
 */
public class HiBannerDelegate implements ViewPager.OnPageChangeListener, IHiBanner {
    private Context mContext;
    private HiBanner mHiBanner;
    private HiBannerAdapter mAdapter;
    private HiIndicator<?> mHiIndicator;
    private boolean mAutoPlay;
    private boolean mLoop;
    private List<? extends HiBannerMo> mHiBannerMos;
    private ViewPager.OnPageChangeListener mOnPagerChangeListener;
    private int mIntervalTime = 5000;
    private IHiBanner.OnBannerClickListener mOnBannerClickListener;
    private HiViewPager mHiViewPager;
    private int mScrollDuration = -1;


    public HiBannerDelegate(Context context, HiBanner mHiBanner) {
        this.mContext = context;
        this.mHiBanner = mHiBanner;
    }

    @Override
    public void setBannerData(int layoutResId, @NonNull List<? extends HiBannerMo> models) {
        mHiBannerMos = models;
        init(layoutResId);
    }

    @Override
    public void setBannerData(@NonNull List<? extends HiBannerMo> models) {
        setBannerData(R.layout.hi_banner_item_image, models);
    }

    @Override
    public void setHiIndicator(HiIndicator<?> hiIndicator) {
        this.mHiIndicator = hiIndicator;
    }

    @Override
    public void setAutoPlay(boolean autoPlay) {
        this.mAutoPlay = autoPlay;
        if (mAdapter != null) {
            mAdapter.setAutoPlay(autoPlay);
        }
        if (mHiViewPager != null) {
            mHiViewPager.setAutoPlay(autoPlay);
        }
    }

    @Override
    public void setLoop(boolean loop) {
        this.mLoop = loop;
    }

    @Override
    public void setIntervalTime(int intervalTime) {
        if (mIntervalTime > 0) {
            this.mIntervalTime = intervalTime;
        }
    }

    @Override
    public void setScrollDuration(int duration) {
        this.mScrollDuration = duration;
        if (mHiViewPager != null && duration > 0) {
            mHiViewPager.setScrollDuration(duration);
        }
    }

    @Override
    public void setBindAdapter(IBindAdapter bindAdapter) {
        mAdapter.setBindAdapter(bindAdapter);
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPagerChangeListener) {

    }

    @Override
    public void setOnBannerClickListener(OnBannerClickListener onBannerClickListener) {

    }

    private void init(@LayoutRes int layoutResId) {
        if (mAdapter == null) {
            mAdapter = new HiBannerAdapter(mContext);
        }
        if (mHiIndicator == null) {
            //todo 创建mHiIndicator实例
        }
        mHiIndicator.onInflate(mHiBannerMos.size());
        mAdapter.setLayoutResId(layoutResId);
        mAdapter.setBannerData(mHiBannerMos);
        mAdapter.setAutoPlay(mAutoPlay);
        mAdapter.setLoop(mLoop);
        mAdapter.setOnBannerClickListener(mOnBannerClickListener);

        mHiViewPager = new HiViewPager(mContext);
        mHiViewPager.setIntervalTime(mIntervalTime);
        mHiViewPager.setAutoPlay(mAutoPlay);
        mHiViewPager.addOnPageChangeListener(this);
        if (mScrollDuration > 0) mHiViewPager.setScrollDuration(mScrollDuration);
        FrameLayout.LayoutParams layoutParams =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mHiViewPager.setAdapter(mAdapter);

        if ((mLoop || mAutoPlay) && mAdapter.getRealCount() != 0) {
            //无限轮播关键点：使第一张能反向滑动到最后一张，已达到无限滚动的效果
            int firstItem = mAdapter.getFirstItem();
            mHiViewPager.setCurrentItem(firstItem);
        }

        mHiBanner.removeAllViews();
        mHiBanner.addView(mHiViewPager, layoutParams);
        mHiBanner.addView(mHiIndicator.get(), layoutParams);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (null != mOnPagerChangeListener && mAdapter.getRealCount() != 0) {
            mOnPagerChangeListener.onPageScrolled(position % mAdapter.getRealCount(), positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (mAdapter.getRealCount() == 0) {
            return;
        }
        position = position % mAdapter.getRealCount();
        if (mOnPagerChangeListener != null) {
            mOnPagerChangeListener.onPageSelected(position);
            if (mHiIndicator != null) {
                mHiIndicator.onPaintChange(position, mAdapter.getRealCount());
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mOnPagerChangeListener != null) {
            mOnPagerChangeListener.onPageScrollStateChanged(state);
        }
    }

}
