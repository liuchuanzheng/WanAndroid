package com.liuchuanzheng.wanandroid.modules.main.presenters;

import android.content.Context;

import com.liuchuanzheng.wanandroid.base.mvp.model.IBaseModel;
import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.modules.main.beans.ArticleResponseBean;
import com.liuchuanzheng.wanandroid.modules.main.contracts.IContract;
import com.liuchuanzheng.wanandroid.modules.main.models.MainActivityModel;

public class MainActivityPresenter extends BasePresenter<IContract.main.View,MainActivityModel> implements IContract.main.Presenter {


    public MainActivityPresenter(Context context,IContract.main.View mView) {
        super(context,mView);

    }

    @Override
    public void initModel() {
        mModel = new MainActivityModel(this);
    }

    @Override
    public void get() {
    mModel.get(new IBaseModel.PMListener<ArticleResponseBean>() {
        @Override
        public void onCompleted() {
            mView.onComplete();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ArticleResponseBean responseBean) {
            mView.onGet();
        }
    });
    }
}
