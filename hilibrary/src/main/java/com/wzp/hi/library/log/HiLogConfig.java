package com.wzp.hi.library.log;


/**
 * HiLog配置类
 */
public abstract class HiLogConfig {

    //日志最长的长度是512
    static int MAX_LEN = 512;

    static HiThreadFormatter HI_THREAD_FORMATTER = new HiThreadFormatter();
    static HiStackTraceFormatter HI_STACK_TRACE_FORMATTER = new HiStackTraceFormatter();

    public JsonParser injectJsonParser(){
        return null;
    }

    //如果不传tag，默认用全局的tag：HiLog
    public String getGlobalTag(){
        return "HiLog";
    }

    //是否启用HiLog，默认启动HiLog
    public boolean enable(){
        return true;
    }

    //日志里面是否包含我们的线程信息
    public boolean includeTread(){
        return false;
    }

    //堆栈信息的深度
    public int stackTraceDepth(){
        return 5;
    }

    //允许用户注册打印器
    public HiLogPrinter[] printers(){
        return null;
    }

    //序列化,为了打印任何数据
    public interface JsonParser{
        String toJson(Object src);
    }

}
