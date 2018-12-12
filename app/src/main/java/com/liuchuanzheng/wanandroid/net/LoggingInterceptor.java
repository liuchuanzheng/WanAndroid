package com.liuchuanzheng.wanandroid.net;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author 刘传政
 * @date 2018/12/12 0012 11:08
 * QQ:1052374416
 * 电话:18501231486
 * 作用: okhttp Log拦截器。统一打印log.这是aop思想
 * 注意事项:这是一个完美的log输出。特别喜欢它的简洁且详细
 * 平时使用 tag:WanAndroid 过滤。使用info级别。你会看到相对完整的请求和响应内容。并知道是哪个类哪个方法请求的
 * 如果你想进一步看格式化后的json。那就用debug级别过滤，你会看到更漂亮的风景。
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
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(Charset.forName("UTF-8"));
            }
            String bodyString = buffer.clone().readString(charset);
            Logger.json(bodyString);//打印json格式默认是debug级别。正好我们可以利用info级别多虑掉。省的一大篇全是。
            logMessage.append("body:-----\n" + bodyString+ "\n");
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
    private void logResponseMessage(Response response, Chain chain) throws IOException {
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
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(Charset.forName("UTF-8"));
            }
            String bodyString = buffer.clone().readString(charset);
            Logger.json(bodyString);//打印json格式默认是debug级别。正好我们可以利用info级别多虑掉。省的一大篇全是。
            logMessage.append("body:-----\n" + bodyString+ "\n");

        }

        logMessage.append("[响应结束]\n");
        Logger.i(logMessage.toString());

    }
}
