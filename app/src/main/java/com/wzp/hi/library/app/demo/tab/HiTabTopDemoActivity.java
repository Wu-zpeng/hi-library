package com.wzp.hi.library.app.demo.tab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.wzp.hi.library.app.R;
import com.wzp.u.hi.ui.tab.common.IHiTabLayout;
import com.wzp.u.hi.ui.tab.top.HiTabTopInfo;
import com.wzp.u.hi.ui.tab.top.HiTabTopLayout;

import java.util.ArrayList;
import java.util.List;

public class HiTabTopDemoActivity extends AppCompatActivity {

    String[] tabsStr = new String[]{
            "热门",
            "服装",
            "数码",
            "零食",
            "家电",
            "汽车",
            "百货",
            "家居",
            "装修",
            "运动"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi_tab_top_demo);

        initTabTop();
    }

    private void initTabTop() {
        HiTabTopLayout hiTabTopLayout = findViewById(R.id.tab_top_layout);
        List<HiTabTopInfo<?>> infoList = new ArrayList<>();
        int defaultColor = getResources().getColor(R.color.tabTopDefaultColor);
        int tintColor = getResources().getColor(R.color.tabTopTintColor);
        for (String s : tabsStr) {
            HiTabTopInfo<?> info = new HiTabTopInfo<Integer>(s, defaultColor, tintColor);
            infoList.add(info);
        }
        hiTabTopLayout.inflateInfo(infoList);
        hiTabTopLayout.addTabSelectedChangeListener(new IHiTabLayout.OnTabSelectedListener<HiTabTopInfo<?>>() {
            @Override
            public void onTabSelectedChange(int index, @Nullable HiTabTopInfo<?> prevInfo, @NonNull HiTabTopInfo<?> nextInfo) {
                Toast.makeText(HiTabTopDemoActivity.this, nextInfo.name, Toast.LENGTH_SHORT).show();
            }
        });
        hiTabTopLayout.defaultSelected(infoList.get(0));
    }
}