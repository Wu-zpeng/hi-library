package com.wzp.u.hi.ui.banner.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

/**
 * 实现了自动翻页的 ViewPager
 */
public class HiViewPager extends ViewPager {
    /**
     * ViewPager 滚动的时间间隔
     */
    private int mIntervalTime;
    /**
     * 是否开启自动轮播
     */
    private boolean mAutoPlay = true;
    /**
     * ViewPager 是否已经绘制了页面
     */
    private boolean isLayout;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            //切换到下一个
            next();
            mHandler.postDelayed(this, mIntervalTime);
        }
    };

    public HiViewPager(@NonNull Context context) {
        super(context);
    }

    public HiViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAutoPlay(boolean autoPlay) {
        this.mAutoPlay = autoPlay;
        if (!mAutoPlay) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:         //如果用户松开手
            case MotionEvent.ACTION_CANCEL:     //如果用户触摸事件取消后
                start();
                break;
            default:
                stop();
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        isLayout = true;
    }

    /**
     * 为了解决viewpager嵌套在recycleview中，导致出现的卡viewpager问题
     * fix 使用RecycleView + ViewPager bug
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isLayout && getAdapter() != null && getAdapter().getCount() > 0) {
            try {
                //通过反射获取该参数:mFirstLayout;
                Field mScroller = ViewPager.class.getDeclaredField("mFirstLayout");
                //设置成可访问的
                mScroller.setAccessible(true);
                //将 mFirstLayout 设置成 false
                mScroller.set(this, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        start();
    }

    /**
     * fix 使用RecycleView + ViewPager bug
     * 解决：当viewpager自动播放的时候滑动Recyclview，Viewpager会卡在一半
     */
    @Override
    protected void onDetachedFromWindow() {
        if (((Activity) getContext()).isFinishing()) {
            super.onDetachedFromWindow();
        }
        stop();
    }

    /**
     * 设置ViewPager的滚动速度
     *
     * @param duration page切换的时间长度
     */
    public void setScrollDuration(int duration) {
        try {
            Field scrollerField = ViewPager.class.getField("mScroller");
            scrollerField.setAccessible(true);
            scrollerField.set(this, new HiBannerScroller(getContext(), duration));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置页面停留时间
     *
     * @param intervalTime 停留时间单位毫秒
     */
    public void setIntervalTime(int intervalTime) {
        this.mIntervalTime = intervalTime;
    }

    /**
     * 开始自动播放
     */
    public void start() {
        mHandler.removeCallbacksAndMessages(null);
        if (mAutoPlay) {
            mHandler.postDelayed(mRunnable, mIntervalTime);
        }
    }

    /**
     * 停止自动播放
     */
    public void stop() {
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 设置下一个要显示的item，并返回item的pos
     *
     * @return 下一个要显示item的pos
     */
    private int next() {
        int nextPosition = -1;
        if (getAdapter() == null || getAdapter().getCount() <= 1) {
            stop();
            return nextPosition;
        }
        nextPosition = getCurrentItem() + 1;
        //下一个索引大于adapter的view的最大数量时重新开始
        if (nextPosition >= getAdapter().getCount()) {
            //获取第一个item的索引
        }

        //true:是否圆滑的滚动
        setCurrentItem(nextPosition, true);
        return nextPosition;

    }
}
