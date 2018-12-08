package com.liuchuanzheng.wanandroid.base.mvp.view;

/**
 * 针对retrofit网络库的view。只要区别在有一个oncomplete回调
 * 注意，这里不要定义onError()回调。因为你定义了也没用，一个view中有很多请求动作，错误后onError()是对应不同的
 * ui处理的，有的刷新界面，有的不处理ui。所以你得想法区分不同的请求动作。这种区分显然很麻烦。而且假如你的错误和正确回调里有一些
 * 联动处理，通信更麻烦。
 */
public interface IBaseRetrofitView extends IBaseView{
    //无论网络动作错或对，都会走到这里。可以进行一些特殊的设置或统计
    void onComplete();
}
