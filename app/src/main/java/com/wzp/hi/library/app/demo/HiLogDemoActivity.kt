package com.wzp.hi.library.app.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wzp.hi.library.app.R
import com.wzp.hi.library.log.*

class HiLogDemoActivity : AppCompatActivity() {

    var viewPrinter: HiViewPrinter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_log_demo)

        viewPrinter = HiViewPrinter(this)

        findViewById<View>(R.id.btn_log).setOnClickListener(){
//            printLog();
            printLogT()
        }

        viewPrinter!!.viewProvider.showFloatingView()

    }

    private fun printLog(){
        HiLog.a("9900");
    }

    private fun printLogT(){
        HiLogManager.getInstance().addPrinter(viewPrinter)
        //自定义Log配置
        HiLog.log(object : HiLogConfig(){
            override fun includeTread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        }, HiLogType.E, "wzp", "5566")

        HiLog.a("888888");
    }


}