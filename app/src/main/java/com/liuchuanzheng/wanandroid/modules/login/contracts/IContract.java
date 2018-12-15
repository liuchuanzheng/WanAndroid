package com.liuchuanzheng.wanandroid.modules.login.contracts;

import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseRetrofitView;
import com.liuchuanzheng.wanandroid.modules.login.beans.LoginResponseBean;

public interface IContract {
    interface Login {
        interface View extends IBaseRetrofitView {
            void onLogin(LoginResponseBean responseBean, int resultType, String errorMsg);
        }

        interface Presenter {
            void login(String username, String password);
        }
    }

}
