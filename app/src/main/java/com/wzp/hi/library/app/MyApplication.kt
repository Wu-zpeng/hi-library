package com.wzp.hi.library.app

import android.app.Application
import com.google.gson.Gson
import com.wzp.hi.library.log.*

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        HiLogManager.init(object : HiLogConfig(){

            override fun injectJsonParser(): JsonParser {
                return JsonParser { src -> Gson().toJson(src) }
            }

            override fun getGlobalTag(): String {
                return "MyApplication"
            }

            override fun enable(): Boolean {
                return true
            }
        }, HiConsolePrinter(), HiFilePrinter.getInstance(applicationContext.cacheDir.absolutePath, 0))
    }
}