package com.wzp.u.hi.ui.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wzp.hi.library.util.HiDisplayUtil;

/**
 * 下拉刷新的 Overlay视图，可以重载这个类来定义自己的 Overlay
 */
public abstract class HiOverView extends FrameLayout {
    public enum HiRefreshState {
        /**
         * 初始态
         */
        STATE_INIT,
        /**
         * Header展示的状态
         */
        STATE_VISIBLE,
        /**
         * 刷新中的状态
         */
        STATE_REFRESH,
        /**
         * 超出可刷新距离的状态
         */
        STATE_OVER,
        /**
         * 超出刷新位置松开手后的状态
         */
        STATE_OVER_RELEASE
    }

    //给一个初始值
    protected HiRefreshState mState = HiRefreshState.STATE_INIT;

    /**
     * 触发下拉刷新需要的最小高度
     */
    public int mPullRefreshHeight;

    /**
     * 下拉刷新的最小阻尼
     * 阻尼：用户下拉的距离和视图滚动高度的比例系数
     */
    public float minDamp=1.6f;

    /**
     * 下拉刷新的最大阻尼
     */
    public float maxDamp=2.2f;

    public HiOverView(@NonNull Context context) {
        super(context);
        preInit();
    }

    public HiOverView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        preInit();
    }

    public HiOverView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        preInit();
    }

    protected void preInit(){
        mPullRefreshHeight = HiDisplayUtil.dp2px(66,getResources());
        init();
    }

    /**
     * 初始化
     */
    public abstract void init();

    /**
     * 滚动
     * @param scrollY               视图滚动的Y轴
     * @param pullRefreshHeight     下拉刷新的高度
     */
    protected abstract void onScroll(int scrollY, int pullRefreshHeight);

    /**
     * 下拉刷新显示 Overlay
     */
    protected abstract void onVisible();

    /**
     * 超过 Overlay 视图高度，释放就会加载
     */
    public abstract void onOver();

    /**
     * 开始刷新、正在刷新
     */
    public abstract void onRefresh();

    /**
     * 刷新完成、加载完成
     */
    public abstract void onFinish();

    /**
     * 设置下拉刷新的状态
     * @param state 状态
     */
    public void setState(HiRefreshState state) {
        this.mState = state;
    }

    /**
     * 获取下拉刷新的状态
     * @return  状态
     */
    public HiRefreshState getState() {
        return mState;
    }
}
