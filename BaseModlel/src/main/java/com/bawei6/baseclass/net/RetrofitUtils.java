package com.bawei6.baseclass.net;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author fengchen
 * @date 2019/12/28.
 * @description：
 */
public class RetrofitUtils {
    private static volatile RetrofitUtils instance;
    private Retrofit mRetrofit;

    public static RetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }


    private RetrofitUtils() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(createRequestInterceptor())
                .addNetworkInterceptor(createLogInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl(ValPool.BASEURL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }





    /***
     * 创建 Log拦截器
     * @return Log拦截器
     */
    private HttpLoggingInterceptor createLogInterceptor() {
        HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(ValPool.TAG, message);
            }
        });
        mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return mLoggingInterceptor;
    }




    /***
     * 自定义拦截器
     * @return
     */
    private Interceptor createRequestInterceptor() {
        return new Interceptor() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拦截请求
                Request request = chain.request();
                //拦截响应
                Response response = chain.proceed(request);
                return null;
            }
        };
    }

    /***
     * 同步请求 token
     * @return 请求到的token
     */
    private String requestToken() {

        return "";
    }


    /***
     * @Desc 创建API
     * @param clazz
     * @param <T>
     * @return api
     */
    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}