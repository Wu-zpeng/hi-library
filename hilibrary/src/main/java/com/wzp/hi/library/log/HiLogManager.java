package com.wzp.hi.library.log;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * HiLog管理类
 */
public class HiLogManager {

    //实现它的单例模式
    private HiLogConfig config;
    private static HiLogManager instance;

    //保存打印器
    private List<HiLogPrinter> printers = new ArrayList<>();

    private HiLogManager(HiLogConfig config, HiLogPrinter[] printers){
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static HiLogManager getInstance(){
        return instance;
    }

    public static void init(@NonNull HiLogConfig config, HiLogPrinter... printers){
        instance = new HiLogManager(config, printers);
    }

    public HiLogConfig getConfig(){
        return config;
    }

    public List<HiLogPrinter> getPrinters(){
        return printers;
    }

    //添加打印器
    public void addPrinter(HiLogPrinter printer){
        printers.add(printer);
    }

    //移除打印器
    public void removePrinter(HiLogPrinter printer){
        if (printer != null){
            printers.remove(printer);
        }
    }



}
