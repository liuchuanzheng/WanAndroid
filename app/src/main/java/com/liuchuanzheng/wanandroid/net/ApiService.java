package com.liuchuanzheng.wanandroid.net;

import com.liuchuanzheng.wanandroid.modules.main.beans.ArticleResponseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
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

}
