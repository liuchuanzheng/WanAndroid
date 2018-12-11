package com.liuchuanzheng.wanandroid.modules.main.presenters;

import android.content.Context;

import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.modules.main.beans.ArticleResponseBean;
import com.liuchuanzheng.wanandroid.modules.main.contracts.IContract;
import com.liuchuanzheng.wanandroid.modules.main.models.MainActivityModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivityPresenter extends BasePresenter<IContract.main.View, MainActivityModel> implements IContract.main.Presenter {


    public MainActivityPresenter(Context context, IContract.main.View mView) {
        super(context, mView);

    }

    @Override
    public void initModel() {
        mModel = new MainActivityModel(this);
    }

    @Override
    public void get() {
        mModel.get()
                .subscribe(new Observer<ArticleResponseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArticleResponseBean articleResponseBean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
