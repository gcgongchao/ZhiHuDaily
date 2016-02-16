package com.general.android.zhihudaily;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.general.android.zhihudaily.adapter.MessageListFragmentAdapter;
import com.general.android.zhihudaily.utils.DateTimeTools;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private ArrayList<String> dates;
    private MessageListFragmentAdapter messageListFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        setListener();
        setUI();

    }

    @Override
    protected void initData() {
        dates= DateTimeTools.getWeekDate();
        messageListFragmentAdapter=new MessageListFragmentAdapter(getSupportFragmentManager(),dates);
    }

    @Override
    protected void initView() {

        mToolbar=(Toolbar)findViewById(R.id.tb_title);
        mTabLayout=(TabLayout)findViewById(R.id.tl_date);
        mViewPager=(ViewPager)findViewById(R.id.vp_message_list);
        mViewPager.setAdapter(messageListFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);//setupWithViewPager必须在ViewPager.setAdapter()之后调用
    }

    @Override
    protected void setUI() {

        mToolbar.setTitle("首页");
    }

    @Override
    protected void setListener() {

    }
}
