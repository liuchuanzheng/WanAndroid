package com.liuchuanzheng.wanandroid.modules.main.models;

import com.liuchuanzheng.wanandroid.base.mvp.model.BaseModel;
import com.liuchuanzheng.wanandroid.modules.main.beans.ArticleResponseBean;
import com.liuchuanzheng.wanandroid.modules.main.presenters.MainActivityPresenter;
import com.liuchuanzheng.wanandroid.net.ApiService;
import com.liuchuanzheng.wanandroid.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivityModel extends BaseModel<MainActivityPresenter> {

    public MainActivityModel(MainActivityPresenter mPresenter) {
        super(mPresenter);

    }

    public Observable<ArticleResponseBean> get() {
        Observable<ArticleResponseBean> observable = RetrofitManager.create(ApiService.class)
                .getHomeArticles(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;

    }
}
