package com.liuchuanzheng.wanandroid.base;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.utils.activity.ActivityStackUtil;
import com.liuchuanzheng.wanandroid.utils.net.NetUtil;
import com.liuchuanzheng.wanandroid.utils.net.NetWorkBroadcastReceiver;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity implements NetWorkBroadcastReceiver.NetEvent{
    public static NetWorkBroadcastReceiver.NetEvent netEvent;
    NetWorkBroadcastReceiver netBroadcastReceiver;
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
        netEvent = this;
        initStatusColor();
        initToolbar();
        initNetReceiver();
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
    public void onNetChange(int netMobile) {
        if (netMobile == NetUtil.NETWORK_NONE) {
            Logger.i("网络类型变化啦！类型：NETWORK_NONE");
        } else if(netMobile == NetUtil.NETWORK_MOBILE){
            Logger.i("网络类型变化啦！类型：NETWORK_MOBILE");
        }else if(netMobile == NetUtil.NETWORK_WIFI){
            Logger.i("网络类型变化啦！类型：NETWORK_WIFI");
        }
    }


    @Override
    protected void onDestroy() {
        ImmersionBar.with(this).destroy();
        activityStackUtil.removeActivity(this);
        unbinder.unbind();
        unregisterReceiver(netBroadcastReceiver);
//        Logger.i("注销广播："+netBroadcastReceiver);
        super.onDestroy();

    }
    private void initNetReceiver() {
        //实例化IntentFilter对象
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netBroadcastReceiver = new NetWorkBroadcastReceiver();
        //注册广播接收
        registerReceiver(netBroadcastReceiver, filter);
//        Logger.i("注册广播："+netBroadcastReceiver);

    }
}
