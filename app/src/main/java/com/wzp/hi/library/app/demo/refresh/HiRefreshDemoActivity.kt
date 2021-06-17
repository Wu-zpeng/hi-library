package com.wzp.hi.library.app.demo.refresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.wzp.hi.library.app.R
import com.wzp.u.hi.ui.refresh.HiRefresh
import com.wzp.u.hi.ui.refresh.HiRefreshLayout
import com.wzp.u.hi.ui.refresh.HiTextOverView

class HiRefreshDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_refresh_demo)

        val refreshLayout = findViewById<HiRefreshLayout>(R.id.refresh_layout)
        val xOverView = HiTextOverView(this)
        refreshLayout.setRefreshOverView(xOverView)

        refreshLayout.setRefreshListener(object : HiRefresh.HiRefreshListener {
            override fun enableRefresh(): Boolean {
                return true
            }

            override fun onRefresh() {
                Handler().postDelayed({ refreshLayout.refreshFinished() }, 1000)
            }

        })
    }


}