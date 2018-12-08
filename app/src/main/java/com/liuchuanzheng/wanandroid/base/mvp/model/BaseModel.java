package com.liuchuanzheng.wanandroid.base.mvp.model;

import com.liuchuanzheng.wanandroid.base.mvp.presenter.IBasePresenter;

public abstract class BaseModel<T extends IBasePresenter>  implements IBaseModel {
    private T mPresenter;

    public BaseModel(T mPresenter) {
        this.mPresenter = mPresenter;
    }
}
