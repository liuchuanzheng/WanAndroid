package com.liuchuanzheng.wanandroid.modules.main;

import android.Manifest;

import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.BaseMVPActivity;
import com.liuchuanzheng.wanandroid.modules.main.contracts.IContract;
import com.liuchuanzheng.wanandroid.modules.main.presenters.MainActivityPresenter;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseMVPActivity<IContract.main.View,MainActivityPresenter>{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        requestPermissions();
    }

    @Override
    protected void initData() {

    }
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(MainActivity.this);
    rxPermission .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe(new Consumer<Permission>() {
                @Override
                public void accept(Permission permission) throws Exception {
                    if (permission.granted) {
                        // 用户已经同意该权限

                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框

                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』

                    } }
            });
    }


    @Override
    protected void initMVP() {
       mView = new IContract.main.View() {
           @Override
           public void onGet() {

           }

           @Override
           public void onComplete() {

           }
       };

       mPresenter = new MainActivityPresenter(this,mView);
       mPresenter.get();
    }


}
