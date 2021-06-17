package com.wzp.hi.library.log;

import android.util.Log;

import androidx.annotation.NonNull;

import static com.wzp.hi.library.log.HiLogConfig.MAX_LEN;

/**
 * 控制台的打印器
 */
public class HiConsolePrinter implements HiLogPrinter{

    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString) {
        //获取打印信息的长度
        int len = printString.length();
        //获取打印的行数
        int countOfSub = len / MAX_LEN;

        if (countOfSub > 0){
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                Log.println(level, tag, printString.substring(index, index+MAX_LEN));
                index += MAX_LEN;
            }
            if (index != len){
                Log.println(level, tag, printString.substring(index, len));
            }
        }else {
            Log.println(level, tag, printString);
        }
    }
}
