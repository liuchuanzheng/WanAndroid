package com.liuchuanzheng.wanandroid.base.mvp.model;

import com.liuchuanzheng.wanandroid.base.mvp.BaseResponseBean;

public interface IBaseModel{
    /**
     * presenter和model的桥梁。因为是用retrofit。所以回调类型也是固定的
     * 这样写的好处是presenter层中会有很多请求，我们不能各个都写三种回调，到时候自己都分不清。
     * 其实说真的，此框架没有model层会更好，也就是model和presenter统一叫presenter，省去了model和presenter的通信
     * 但是，既然叫MVP框架，还是加上吧
     */
    interface PMListener<T extends BaseResponseBean>{
         void onCompleted();


         void onError(Throwable e) ;


         void onNext(T responseBean) ;
    }

}
