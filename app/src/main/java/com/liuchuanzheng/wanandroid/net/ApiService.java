package com.liuchuanzheng.wanandroid.net;

import com.liuchuanzheng.wanandroid.modules.login.beans.LoginResponseBean;
import com.liuchuanzheng.wanandroid.modules.main.beans.ArticleResponseBean;
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
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     *
     * @param page page
     */
    @GET("/article/list/{page}/json")
    Observable<ArticleResponseBean> getHomeArticles(@Path("page") int page);

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

}
