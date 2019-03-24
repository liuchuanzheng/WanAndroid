package com.liuchuanzheng.wanandroid.modules.demo.presenters;

import android.content.Context;

import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.mvp.model.BaseModel;
import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.demo.beans.DemoTitlesResponseBean;
import com.liuchuanzheng.wanandroid.modules.demo.contracts.IContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DemoFragmentPresenter extends BasePresenter<IContract.main.View> implements IContract.main.Presenter {

    BaseModel.Demo model;

    public DemoFragmentPresenter(Context context, IContract.main.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        model = new BaseModel().new Demo();
    }

    @Override
    public void getTitleList() {
        model.getTitleList().subscribe(new Observer<DemoTitlesResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DemoTitlesResponseBean responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (responseBean.getErrorCode() == 0) {
                    if (responseBean.getData() == null) {
                        mView.onGetTitleList(responseBean, IBaseView.SERVER_NORMAL_DATANO, context.getString(R.string.data_null));
                    } else {
                        mView.onGetTitleList(responseBean, IBaseView.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetTitleList(responseBean, IBaseView.SERVER_ERROR, responseBean.getErrorMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetTitleList(null, IBaseView.NET_ERROR, e.toString());
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
