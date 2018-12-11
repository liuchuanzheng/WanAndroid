package com.liuchuanzheng.wanandroid.net;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * okhttp Log拦截器。
 * 统一打印log.这是aop思想
 */
public final class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logRequestMessage(request);
        Response response = chain.proceed(request);
        logResponseMessage(response);
        return response;
    }


    /**
     * 打印请求信息
     *
     * @param request
     */
    void logRequestMessage(Request request) {
        if (request == null) {
            return;
        }
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("请求开始\n");
        logMessage.append("Url:" + request.url().url().toString() + "\n");
        logMessage.append("Method:" + request.method() + "\n");
        logMessage.append("Heads:" + request.headers() + "\n");

        RequestBody requestBody = request.body();
        if (requestBody != null) {

        }
        Logger.i(logMessage.toString());
    }

    /**
     * 打印响应信息
     *
     * @param response
     */
    private void logResponseMessage(Response response) {

    }
}
