package com.liuchuanzheng.wanandroid.modules.gzh.presenters;

import android.content.Context;

import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.mvp.model.BaseModel;
import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.gzh.beans.GzhDetailResponseBean;
import com.liuchuanzheng.wanandroid.modules.gzh.contracts.IContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GzhDetailFragmentPresenter extends BasePresenter<IContract.main_detail.View> implements IContract.main_detail.Presenter {

    BaseModel.Gzh model;

    public GzhDetailFragmentPresenter(Context context, IContract.main_detail.View mView) {
        super(context, mView);

    }

    @Override
    public void initModel() {
        model = new BaseModel().new Gzh();
    }


    @Override
    public void getDetail(int page, int id, final boolean isRefresh) {
        model.getWXDetailList(page, id).subscribe(new Observer<GzhDetailResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GzhDetailResponseBean responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
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
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetDetail(null, IBaseView.NET_ERROR, e.toString(), isRefresh);
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
