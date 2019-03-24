package com.liuchuanzheng.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseRetrofitView;

/**
 * @author 刘传政
 * @date 2018/12/16 0016 12:35
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public abstract class BaseMVPLoadFragment<T extends IBaseRetrofitView, K extends BasePresenter> extends BaseLoadFragment {
    public T mView;
    public K mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMVP();
    }

    /**
     * 创建view，不要用实现的方式，要new一个，方便直接查看
     * 创建presenter
     */
    protected abstract void initMVP();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
