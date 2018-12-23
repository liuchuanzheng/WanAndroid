package com.liuchuanzheng.wanandroid.modules.system.presenters;

import android.content.Context;

import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.mvp.model.BaseModel;
import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.system.beans.SystemListResponseBean;
import com.liuchuanzheng.wanandroid.modules.system.contracts.IContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SystemFragmentPresenter extends BasePresenter<IContract.main.View> implements IContract.main.Presenter {

    BaseModel.System system_model;
    public SystemFragmentPresenter(Context context, IContract.main.View mView) {
        super(context, mView);

    }

    @Override
    public void initModel() {
        system_model = new BaseModel().new System();
    }


    @Override
    public void getSysTemList() {
        system_model.getSystemList().subscribe(new Observer<SystemListResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SystemListResponseBean responseBean) {
                if (responseBean.getErrorCode() == 0) {
                    if (responseBean.getData() == null) {
                        mView.onGetSysTemList(responseBean, IBaseView.SERVER_NORMAL_DATANO, context.getString(R.string.data_null));
                    } else {
                        mView.onGetSysTemList(responseBean, IBaseView.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetSysTemList(responseBean, IBaseView.SERVER_ERROR, responseBean.getErrorMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.onGetSysTemList(null, IBaseView.NET_ERROR, e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
