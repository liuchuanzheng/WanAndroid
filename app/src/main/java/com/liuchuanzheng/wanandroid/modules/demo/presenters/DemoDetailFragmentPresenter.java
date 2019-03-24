package com.liuchuanzheng.wanandroid.modules.demo.presenters;

import android.content.Context;

import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.mvp.model.BaseModel;
import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.demo.beans.DemoDetailResponseBean;
import com.liuchuanzheng.wanandroid.modules.demo.contracts.IContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DemoDetailFragmentPresenter extends BasePresenter<IContract.main_detail.View> implements IContract.main_detail.Presenter {

    BaseModel.Demo model;

    public DemoDetailFragmentPresenter(Context context, IContract.main_detail.View mView) {
        super(context, mView);

    }

    @Override
    public void initModel() {
        model = new BaseModel().new Demo();
    }


    @Override
    public void getDetail(int page, int id, final boolean isRefresh) {
        model.getDetailList(page, id).subscribe(new Observer<DemoDetailResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DemoDetailResponseBean responseBean) {
                if (responseBean.getErrorCode() == 0) {
                    if (responseBean.getData() == null) {
                        mView.onGetDetail(responseBean, IBaseView.SERVER_NORMAL_DATANO, context.getString(R.string.data_null), isRefresh);
                    } else {
                        mView.onGetDetail(responseBean, IBaseView.SERVER_NORMAL_DATAYES, "", isRefresh);
                    }
                } else {
                    mView.onGetDetail(responseBean, IBaseView.SERVER_ERROR, responseBean.getErrorMsg(), isRefresh);
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.onGetDetail(null, IBaseView.NET_ERROR, e.toString(), isRefresh);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
