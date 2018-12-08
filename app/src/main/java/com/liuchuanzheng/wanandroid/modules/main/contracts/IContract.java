package com.liuchuanzheng.wanandroid.modules.main.contracts;

import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseRetrofitView;

public interface IContract {
    interface main  {
        interface View extends IBaseRetrofitView{
            void onGet();
        }
        interface Presenter  {
            void get();
        }
    }

}
