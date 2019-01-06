package com.liuchuanzheng.wanandroid.net;

import com.liuchuanzheng.wanandroid.modules.gzh.beans.GzhDetailResponseBean;
import com.liuchuanzheng.wanandroid.modules.gzh.beans.TitlesResponseBean;
import com.liuchuanzheng.wanandroid.modules.home.beans.BannerResponseBean;
import com.liuchuanzheng.wanandroid.modules.home.beans.HomeArticleListReaponseBean;
import com.liuchuanzheng.wanandroid.modules.login.beans.LoginResponseBean;
import com.liuchuanzheng.wanandroid.modules.system.beans.SystemListResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lw on 2018/1/23.
 */

public interface ApiService {
    /**
     * 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginResponseBean> login(@Field("username") String username, @Field("password") String password);

    /**
     * 体系数据
     */
    @GET("tree/json")
    Observable<SystemListResponseBean> getSystemList();

    /**
     * 主页
     */
    @GET("article/list/{page}/json")
    Observable<HomeArticleListReaponseBean> getArticleList(@Path("page") int page);

    /**
     * banner
     */
    @GET("banner/json")
    Observable<BannerResponseBean> getBanner();

    /**
     * 获取 微信公众号列表
     *
     * @return
     */
    @GET("/wxarticle/chapters/json")
    Observable<TitlesResponseBean> getTitleList();

    /**
     * 获取 微信公众号详细信息列表数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<GzhDetailResponseBean> getWXDetailList(@Path("page") int page, @Path("id") int id);

}
