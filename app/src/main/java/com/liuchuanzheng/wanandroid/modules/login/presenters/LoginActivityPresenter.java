package com.liuchuanzheng.wanandroid.modules.login.presenters;

import android.content.Context;

import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.login.beans.LoginResponseBean;
import com.liuchuanzheng.wanandroid.modules.login.contracts.IContract;
import com.liuchuanzheng.wanandroid.modules.login.models.LoginActivityModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivityPresenter extends BasePresenter<IContract.Login.View, LoginActivityModel> implements IContract.Login.Presenter {


    public LoginActivityPresenter(Context context, IContract.Login.View mView) {
        super(context, mView);

    }

    @Override
    public void initModel() {
        mModel = new LoginActivityModel(this);
    }

    @Override
    public void login(String username, String password) {
        mModel.login(username, password).subscribe(new Observer<LoginResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginResponseBean responseBean) {
                if (responseBean.getErrorCode() == 0) {
                    if (responseBean.getData() == null) {
                        mView.onLogin(responseBean, IBaseView.SERVER_NORMAL_DATANO, context.getString(R.string.data_null));
                    } else {
                        mView.onLogin(responseBean, IBaseView.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onLogin(responseBean, IBaseView.SERVER_ERROR, responseBean.getErrorMsg());
                }

            }

            @Override
            public void onError(Throwable e) {
                mView.onLogin(null, IBaseView.NET_ERROR, e.toString());
            }

            @Override
            public void onComplete() {
                mView.onComplete();
            }
        });
    }
}
