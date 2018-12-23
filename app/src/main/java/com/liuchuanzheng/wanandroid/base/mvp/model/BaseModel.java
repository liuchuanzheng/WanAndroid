package com.liuchuanzheng.wanandroid.base.mvp.model;

import com.liuchuanzheng.wanandroid.modules.login.beans.LoginResponseBean;
import com.liuchuanzheng.wanandroid.modules.system.beans.SystemListResponseBean;
import com.liuchuanzheng.wanandroid.net.ApiService;
import com.liuchuanzheng.wanandroid.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 刘传政
 * @date 2018/12/23 0023 10:39
 * QQ:1052374416
 * 电话:18501231486
 * 作用:这种model是反复斟酌定下来的. model不是每个类或每个模块有一个,而是全都放到这里.采用内部类的方式区分模块.
 * common代表一些通用的接口.考虑到一个presenter中可能用到多个model的方法,所以也不需要在presenter中强制
 * 定义到底采用那个model. 其实说白了,我这框架的model作用弱化了,有点类似于presenter和model合并的感觉.
 * 注意事项:
 */
public class BaseModel implements IBaseModel {
    public class Common {

    }

    public class Login {
        public Observable<LoginResponseBean> login(String username, String password) {
            Observable<LoginResponseBean> observable = RetrofitManager.create(ApiService.class)
                    .login(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            return observable;

        }
    }

    public class System {
        public Observable<SystemListResponseBean> getSystemList() {
            Observable<SystemListResponseBean> observable = RetrofitManager.create(ApiService.class)
                    .getSystemList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            return observable;

        }
    }
}
