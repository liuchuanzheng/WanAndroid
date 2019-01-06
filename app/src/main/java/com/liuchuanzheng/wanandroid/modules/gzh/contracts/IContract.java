package com.liuchuanzheng.wanandroid.modules.gzh.contracts;

import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseRetrofitView;
import com.liuchuanzheng.wanandroid.modules.gzh.beans.GzhDetailResponseBean;
import com.liuchuanzheng.wanandroid.modules.gzh.beans.TitlesResponseBean;

public interface IContract {
    interface main {
        interface View extends IBaseRetrofitView {
            void onGetTitleList(TitlesResponseBean responseBean, int resultType, String errorMsg);
        }

        interface Presenter {
            void getTitleList();
        }
    }

    interface main_detail {
        interface View extends IBaseRetrofitView {
            void onGetDetail(GzhDetailResponseBean responseBean, int resultType, String errorMsg, boolean isRefresh);
        }

        interface Presenter {
            void getDetail(int page, int id, boolean isRefresh);
        }
    }

}
