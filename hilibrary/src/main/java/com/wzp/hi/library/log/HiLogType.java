package com.wzp.hi.library.log;

import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 日志的类型
 */
public class HiLogType {

    //添加注解
    @IntDef({V, D, I, W, E, A})         //注解所接收的类型
    @Retention(RetentionPolicy.SOURCE)  //注解的保留时效，保留在源码级别
    public @interface TYPE{}            //注解名称TYPE

    public final static int V= Log.VERBOSE;
    public final static int D= Log.DEBUG;
    public final static int I= Log.INFO;
    public final static int W= Log.WARN;
    public final static int E= Log.ERROR;
    public final static int A= Log.ASSERT;
}
