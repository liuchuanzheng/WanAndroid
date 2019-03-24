package com.liuchuanzheng.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseRetrofitView;

public abstract class BaseMVPActivity<T extends IBaseRetrofitView,K extends BasePresenter> extends BaseActivity {
    public T mView;
    public K mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initMVP();
        super.onCreate(savedInstanceState);
    }

    /**
     * 创建view，不要用实现的方式，要new一个，方便直接查看
     * 创建presenter
     */
    protected abstract void initMVP();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
