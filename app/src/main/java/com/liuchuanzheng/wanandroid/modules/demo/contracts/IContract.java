package com.liuchuanzheng.wanandroid.modules.demo.contracts;

import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseRetrofitView;
import com.liuchuanzheng.wanandroid.modules.demo.beans.DemoDetailResponseBean;
import com.liuchuanzheng.wanandroid.modules.demo.beans.DemoTitlesResponseBean;

public interface IContract {
    interface main {
        interface View extends IBaseRetrofitView {
            void onGetTitleList(DemoTitlesResponseBean responseBean, int resultType, String errorMsg);
        }

        interface Presenter {
            void getTitleList();
        }
    }

    interface main_detail {
        interface View extends IBaseRetrofitView {
            void onGetDetail(DemoDetailResponseBean responseBean, int resultType, String errorMsg, boolean isRefresh);
        }

        interface Presenter {
            void getDetail(int page, int id, boolean isRefresh);
        }
    }

}
