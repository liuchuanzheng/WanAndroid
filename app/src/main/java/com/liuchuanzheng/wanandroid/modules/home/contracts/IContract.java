package com.liuchuanzheng.wanandroid.modules.home.contracts;

import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseRetrofitView;
import com.liuchuanzheng.wanandroid.modules.home.beans.BannerResponseBean;
import com.liuchuanzheng.wanandroid.modules.home.beans.HomeArticleListReaponseBean;

public interface IContract {
    interface main {
        interface View extends IBaseRetrofitView {
            void onGetHomeList(HomeArticleListReaponseBean responseBean, int resultType, String errorMsg, boolean isRefresh);

            void onGetBanner(BannerResponseBean responseBean, int resultType, String errorMsg);
        }

        interface Presenter {
            void getHomeList(int page, boolean isRefresh);

            void getBanner();
        }
    }

}
