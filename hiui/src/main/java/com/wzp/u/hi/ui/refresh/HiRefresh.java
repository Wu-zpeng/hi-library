package com.wzp.u.hi.ui.refresh;

public interface HiRefresh {

    /**
     * 刷新时是否关闭滚动
     * @param disableRefreshScroll  是否禁止滚动
     */
    void setDisableRefreshScroll(boolean disableRefreshScroll);

    /**
     * 刷新完成
     */
    void refreshFinished();

    /**
     * 设置下拉刷新的监听器
     * @param hiRefreshListener     刷新的监听器
     */
    void setRefreshListener(HiRefresh.HiRefreshListener hiRefreshListener);

    /**
     * 设置下拉刷新的视图
     * @param hiOverView
     */
    void setRefreshOverView(HiOverView hiOverView);

    interface HiRefreshListener{
        //刷新
        void onRefresh();
        //是否启用下拉刷新
        boolean enableRefresh();
    }



}
