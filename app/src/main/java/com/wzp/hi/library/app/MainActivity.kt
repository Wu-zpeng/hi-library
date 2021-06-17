package com.wzp.hi.library.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wzp.hi.library.app.demo.HiLogDemoActivity
import com.wzp.hi.library.app.demo.refresh.HiRefreshDemoActivity
import com.wzp.hi.library.app.demo.tab.HiActivity
import com.wzp.hi.library.app.demo.tab.HiTabBottomDemoActivity
import com.wzp.hi.library.app.demo.tab.HiTabTopDemoActivity

/**
 * kotlin 和 java 进行混编，混合开发
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        when (v !!.id){
            R.id.tv_hilog -> {
                startActivity(Intent(this, HiLogDemoActivity::class.java))
            }
            R.id.tv_tab_bottom -> {
                startActivity(Intent(this, HiTabBottomDemoActivity::class.java))
            }

            R.id.tv_tab_top -> {
                startActivity(Intent(this, HiTabTopDemoActivity::class.java))
            }

            R.id.tv_tab_refresh -> {
                startActivity(Intent(this, HiRefreshDemoActivity::class.java))
            }

        }
    }
}