package com.liuchuanzheng.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.utils.activity.ActivityStackUtil;


import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {

    public ActivityStackUtil activityStackUtil = ActivityStackUtil.getScreenManager();

    protected BaseActivity activity;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        activityStackUtil.addActivity(this);
        activity = this;
        initStatusColor();
        initToolbar();
        initView();
        initData();
        doYourself();
    }

    private void initStatusColor() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)  //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
                .init();
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();

    protected abstract void doYourself();
    /**
     * 初始化toolbar
     */
    protected void initToolbar(){}


    @Override
    protected void onDestroy() {
        ImmersionBar.with(this).destroy();
        activityStackUtil.removeActivity(this);
        unbinder.unbind();
        super.onDestroy();

    }

}
