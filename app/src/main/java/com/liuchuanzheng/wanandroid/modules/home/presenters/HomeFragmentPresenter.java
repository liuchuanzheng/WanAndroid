package com.liuchuanzheng.wanandroid.modules.home.presenters;

import android.content.Context;

import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.mvp.model.BaseModel;
import com.liuchuanzheng.wanandroid.base.mvp.presenter.BasePresenter;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.home.beans.BannerResponseBean;
import com.liuchuanzheng.wanandroid.modules.home.beans.HomeArticleListReaponseBean;
import com.liuchuanzheng.wanandroid.modules.home.contracts.IContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeFragmentPresenter extends BasePresenter<IContract.main.View> implements IContract.main.Presenter {

    BaseModel.Home Model;

    public HomeFragmentPresenter(Context context, IContract.main.View mView) {
        super(context, mView);

    }

    @Override
    public void initModel() {
        Model = new BaseModel().new Home();
    }


    @Override
    public void getHomeList(int page, final boolean isRefresh) {
        Model.getArticleList(page).subscribe(new Observer<HomeArticleListReaponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeArticleListReaponseBean responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (responseBean.getErrorCode() == 0) {
                    if (responseBean.getData() == null) {
                        mView.onGetHomeList(responseBean, IBaseView.SERVER_NORMAL_DATANO, context.getString(R.string.data_null), isRefresh);
                    } else {
                        mView.onGetHomeList(responseBean, IBaseView.SERVER_NORMAL_DATAYES, "", isRefresh);
                    }
                } else {
                    mView.onGetHomeList(responseBean, IBaseView.SERVER_ERROR, responseBean.getErrorMsg(), isRefresh);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetHomeList(null, IBaseView.NET_ERROR, e.toString(), isRefresh);
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

    @Override
    public void getBanner() {
        Model.getBanner().subscribe(new Observer<BannerResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BannerResponseBean responseBean) {
                if (responseBean.getErrorCode() == 0) {
                    if (responseBean.getData() == null) {
                        mView.onGetBanner(responseBean, IBaseView.SERVER_NORMAL_DATANO, context.getString(R.string.data_null));
                    } else {
                        mView.onGetBanner(responseBean, IBaseView.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetBanner(responseBean, IBaseView.SERVER_ERROR, responseBean.getErrorMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.onGetBanner(null, IBaseView.NET_ERROR, e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
