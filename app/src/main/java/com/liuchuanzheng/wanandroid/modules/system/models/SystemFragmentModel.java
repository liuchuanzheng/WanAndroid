package com.liuchuanzheng.wanandroid.modules.system.models;

import com.liuchuanzheng.wanandroid.base.mvp.model.BaseModel;
import com.liuchuanzheng.wanandroid.modules.system.beans.SystemListResponseBean;
import com.liuchuanzheng.wanandroid.modules.system.presenters.SystemFragmentPresenter;
import com.liuchuanzheng.wanandroid.net.ApiService;
import com.liuchuanzheng.wanandroid.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SystemFragmentModel extends BaseModel<SystemFragmentPresenter> {

    public SystemFragmentModel(SystemFragmentPresenter mPresenter) {
        super(mPresenter);

    }

    public Observable<SystemListResponseBean> getSystemList() {
        Observable<SystemListResponseBean> observable = RetrofitManager.create(ApiService.class)
                .getSystemList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;

    }
}
