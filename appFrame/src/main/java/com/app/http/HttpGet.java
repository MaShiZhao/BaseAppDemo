package com.app.http;

import com.app.utils.LogWriter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpGet {

    private InterfaceHttpResult mHttpResult;
    private Platform mPlatform;
    private Class<?> classObj;
    private String postUrl;
    private String[] params;
    private int requestCode;


    /**
     * 构造函数
     *
     * @param listener  服务器 回调接口
     * @param className 返回结果 强转对象
     * @param params    请求参数 第一位是url
     */
    public HttpGet(InterfaceHttpResult listener, Class<?> className, String... params) {
        this.mHttpResult = listener;
        this.mPlatform = Platform.getInstance();
        this.classObj = className;
        this.params = params;
//        onInit(params);
    }

    public void excute() {
        excute(0);
    }

    /**
     * @param requestCode 请求码默认是0
     */
    private void excute(int requestCode) {
        this.requestCode = requestCode;
        if (params == null || params.length == 0) {
            LogWriter.e("params is null or params length is 0");
            setHttpResultString("");
            return;
        }
        getRequestUrl(params);
        try {
            okHttpRequest();
        } catch (IOException e) {
            LogWriter.e("HttpGet okHttpRequest IOException " + e.toString());
            e.printStackTrace();
        }
    }

    private void getRequestUrl(String[] params) {
        String url = params[0];
        String[] keys = url.split(";");
        if (keys.length > params.length) {
            LogWriter.d("httpPost params is missing ");
            setHttpResultString("");
            return;
        }

        if (keys.length < params.length) {
            LogWriter.d("httpPost url  is missing ");
            setHttpResultString("");
            return;
        }

        postUrl = keys[0];
        //params 第一位是url  后面的是value
        //url 包含请求地址和请求需要的key
        for (int i = 1; i < params.length; i++) {
            String connectString;
            if (i == 1) {
                connectString = "?";
            } else {
                connectString = "&";
            }
            postUrl = postUrl + connectString + keys[i] + "=" + params[i];
        }
        LogWriter.d(postUrl);
    }

    /**
     * 调用ok请求返回结果
     *
     * @throws IOException
     */
    private void okHttpRequest() throws IOException {
        int timeoutConnection = 60;
        // OkHttpClient 初始化配置
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(timeoutConnection, TimeUnit.SECONDS);
        client.newBuilder().readTimeout(timeoutConnection, TimeUnit.SECONDS);

        //Request Builder 配置
        Request.Builder builder = new Request.Builder();
        builder.url(postUrl);
        //添加header
        builder.addHeader("token", "token");
        //通过build获取request
        Request request = builder.build();
        //Call 回去返回结果
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogWriter.d("call onFailure : " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (call.isCanceled()) {
                    LogWriter.e("call is canceled");
                } else {
                    sendSuccessResultCallback(response);
                }

            }
        });
    }


    /**
     * 设置回调 返回给主线程
     *
     * @param response response
     */
    private String resultString;

    private void sendSuccessResultCallback(final Response response) {
        if (mHttpResult == null) {
            LogWriter.e("HttpResult should not be null");
            return;
        }
        try {
            resultString = response.body().string();
            mPlatform.execute(new Runnable() {
                @Override
                public void run() {
                    setHttpResultString(resultString);
                }
            });
        } catch (IOException e) {
            LogWriter.e("mHttpResult result call back response.body().string " + e.toString());
            e.printStackTrace();
        }

    }

    /**
     * 设置回调内容
     */
    public void setHttpResultString(String resultString) {
        if (resultString == null) {
            resultString = "";
        }
        LogWriter.d("resultJson : " + resultString);
        Object bean = new Gson().fromJson(resultString, classObj);
        mHttpResult.getCallback(requestCode, bean);
    }
}
