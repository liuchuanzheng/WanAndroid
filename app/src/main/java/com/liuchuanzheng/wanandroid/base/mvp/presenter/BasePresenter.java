package com.liuchuanzheng.wanandroid.base.mvp.presenter;

import android.content.Context;

import com.liuchuanzheng.wanandroid.base.mvp.model.BaseModel;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseRetrofitView;

public abstract class BasePresenter<T extends IBaseRetrofitView,K extends BaseModel> implements IBasePresenter {
    public Context context;
    public T mView;
    public  K mModel;

    public BasePresenter(Context context,T mView) {
        this.context = context;
        this.mView = mView;
        initModel();
    }

     public abstract void initModel();
    /**
     * 记得要在销毁时调用。防止内存泄漏
     */
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
