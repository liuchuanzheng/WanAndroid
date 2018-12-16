package com.liuchuanzheng.wanandroid.modules.system.contracts;

import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseRetrofitView;
import com.liuchuanzheng.wanandroid.modules.system.beans.SystemListResponseBean;

public interface IContract {
    interface main {
        interface View extends IBaseRetrofitView {
            void onGetSysTemList(SystemListResponseBean responseBean, int resultType, String errorMsg);
        }

        interface Presenter {
            void getSysTemList();
        }
    }

}
