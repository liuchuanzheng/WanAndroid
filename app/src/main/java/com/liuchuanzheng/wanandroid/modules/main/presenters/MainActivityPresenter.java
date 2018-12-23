package com.liuchuanzheng.wanandroid.modules.main.presenters;

import android.content.Context;

import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.modules.main.contracts.IContract;

public class MainActivityPresenter extends BasePresenter<IContract.main.View> implements IContract.main.Presenter {


    public MainActivityPresenter(Context context, IContract.main.View mView) {
        super(context, mView);

    }

    @Override
    public void initModel() {
    }

    @Override
    public void get() {

    }
}
