package com.liuchuanzheng.wanandroid.utils.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.liuchuanzheng.wanandroid.base.BaseActivity;


/**
 * 监听 网络变化
 *
 */

public class NetWorkBroadcastReceiver extends BroadcastReceiver {
    public NetEvent netEvent = BaseActivity.netEvent;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            NetUtil.init(context);
            int netWorkState = NetUtil.getNetWorkState();
            // 接口回调传过去状态的类型
            netEvent.onNetChange(netWorkState);
        }
    }

    // 自定义接口
    public interface NetEvent {
        void onNetChange(int netMobile);
    }
}

