package com.general.android.zhihudaily;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by GeneralAndroid
 */
public abstract  class BaseActivity extends AppCompatActivity{
    protected Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext=BaseActivity.this;
        getSupportActionBar().hide();
    }
    protected abstract void initData();
    protected abstract void initView();
    protected abstract void setUI();
    protected abstract void setListener();


}
