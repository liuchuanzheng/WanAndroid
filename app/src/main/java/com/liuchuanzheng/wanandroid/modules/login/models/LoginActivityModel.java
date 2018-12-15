package com.liuchuanzheng.wanandroid.modules.login.models;

import com.liuchuanzheng.wanandroid.base.mvp.model.BaseModel;
import com.liuchuanzheng.wanandroid.modules.login.beans.LoginResponseBean;
import com.liuchuanzheng.wanandroid.modules.login.presenters.LoginActivityPresenter;
import com.liuchuanzheng.wanandroid.net.ApiService;
import com.liuchuanzheng.wanandroid.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivityModel extends BaseModel<LoginActivityPresenter> {

    public LoginActivityModel(LoginActivityPresenter mPresenter) {
        super(mPresenter);

    }

    public Observable<LoginResponseBean> login(String username, String password) {
        Observable<LoginResponseBean> observable = RetrofitManager.create(ApiService.class)
                .login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;

    }
}
