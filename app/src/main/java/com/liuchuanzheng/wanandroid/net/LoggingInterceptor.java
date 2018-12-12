package com.liuchuanzheng.wanandroid.net;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author 刘传政
 * @date 2018/12/12 0012 11:08
 * QQ:1052374416
 * 电话:18501231486
 * 作用: okhttp Log拦截器。统一打印log.这是aop思想
 * 注意事项:
 */
public final class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logRequestMessage(request, chain);
        Response response = chain.proceed(request);
        logResponseMessage(response, chain);
        return response;
    }


    /**
     * 打印请求信息
     *
     * @param request
     * @param chain
     */
    void logRequestMessage(Request request, Chain chain) throws IOException {
        if (request == null) {
            return;
        }
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("[请求开始]\n");
        logMessage.append("Url:" + request.url().url().toString() + "\n");
        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        logMessage.append("protocol:" + protocol + "\n");
        logMessage.append("Method:" + request.method() + "\n");
        //请求的类名方法名和参数
        logMessage.append("tags:" + request.tag() + "\n");
        logMessage.append("headers:.........start.........\n" + request.headers().toString());
        logMessage.append("headers:.........end.........\n");
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            logMessage.append("body:" + request.body().toString() + "\n");
        }
        logMessage.append("[请求结束]\n");
        Logger.i(logMessage.toString());
    }

    /**
     * 打印响应信息
     *
     * @param response
     * @param chain
     */
    private void logResponseMessage(Response response, Chain chain) {
        if (response == null) {
            return;
        }
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("[响应开始]\n");
        logMessage.append(response.toString() + "\n");
        logMessage.append("Method:" + response.request().method() + "\n");
        //请求的类名方法名和参数
        logMessage.append("tags:" + response.request().tag() + "\n");
        logMessage.append("headers:.........start.........\n" + response.headers().toString());
        logMessage.append("headers:.........end.........\n");
        logMessage.append("body:" + response.body().toString() + "\n");
        logMessage.append("[响应结束]\n");
        Logger.i(logMessage.toString());
    }
}
