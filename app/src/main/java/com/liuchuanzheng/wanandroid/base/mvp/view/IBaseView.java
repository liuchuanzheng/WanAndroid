package com.liuchuanzheng.wanandroid.base.mvp.view;

/**
 * 最顶级的。什么都不定义，就是为了能有个总领导
 * 其实把view理解成在ui类中直接与ui有直接联系，且带回调的工具类
 */
public interface IBaseView {
    public static final int NET_ERROR = 1;//对应网络请求没走通,通常是网络错误
    public static final int SERVER_NORMAL_DATANO = 2;//对应网络请求走通,服务器也正常,但是返回内容就是为空或null,其实这样也相当于没请求一样
    public static final int SERVER_NORMAL_DATAYES = 3;//网络走通,返回有数据.但是里边的细节数据我就不管是不是空了.管不过来
    public static final int SERVER_ERROR = 4;//网络走通,但是服务器处理异常,也就是code不等于0
}
