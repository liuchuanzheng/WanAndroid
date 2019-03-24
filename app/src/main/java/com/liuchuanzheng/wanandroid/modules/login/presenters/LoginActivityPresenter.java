package com.liuchuanzheng.wanandroid.modules.login.presenters;

import android.content.Context;

import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.mvp.model.BaseModel;
import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.login.beans.LoginResponseBean;
import com.liuchuanzheng.wanandroid.modules.login.contracts.IContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivityPresenter extends BasePresenter<IContract.Login.View> implements IContract.Login.Presenter {

    BaseModel.Login login_model;
    public LoginActivityPresenter(Context context, IContract.Login.View mView) {
        super(context, mView);

    }

    @Override
    public void initModel() {
        login_model = new BaseModel().new Login();
    }

    @Override
    public void login(String username, String password) {
        login_model.login(username, password).subscribe(new Observer<LoginResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginResponseBean responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
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
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onLogin(null, IBaseView.NET_ERROR, e.toString());
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onComplete();
            }
        });

    }
}
