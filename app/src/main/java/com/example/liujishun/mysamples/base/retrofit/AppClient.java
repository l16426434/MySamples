package com.example.liujishun.mysamples.base.retrofit;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  Retrofit 使用的基本封装
 */
public class AppClient {
    private static boolean isDebug = true;
    private static Retrofit mRetrofit;
    public static Retrofit retrofit() {
        
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addNetworkInterceptor(new StethoInterceptor());
            if (isDebug) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);

            }
            //设置cookie
//            CookieManager cookieManager = new CookieManager();
//            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
//            builder.cookieJar(new JavaNetCookieJar(cookieManager));
            //设置缓存
//            File cacheFile = new File("/NetCache");
//            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
//            Interceptor cacheInterceptor = new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request request = chain.request();
//                    if (!NetworkUtils.isConnected(BaseApp.getContext())) {
//                        request = request.newBuilder()
//                                .cacheControl(CacheControl.FORCE_CACHE)
//                                .build();
//                    }
//                    Response response = chain.proceed(request);
//                    if (NetworkUtils.isConnected(BaseApp.getContext())) {
//                        int maxAge = 0;
//                        // 有网络时, 不缓存, 最大保存时长为0
//                        response.newBuilder()
//                                .header("Cache-Control", "public, max-age=" + maxAge)
//                                .removeHeader("Pragma")
//                                .build();
//                    } else {
//                        // 无网络时，设置超时为4周
//                        int maxStale = 60 * 60 * 24 * 28;
//                        response.newBuilder()
//                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                                .removeHeader("Pragma")
//                                .build();
//                    }
//                    return response;
//                }
//            };
//            builder.addNetworkInterceptor(cacheInterceptor);
//            builder.addInterceptor(cacheInterceptor);
//            builder.cache(cache);
            //设置超时
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
//            //错误重连
            builder.retryOnConnectionFailure(true);
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiStores.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())//以gson为转换器
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//以Rxjava2为适配器
                    .client(okHttpClient)
                    .build();

        }
        return mRetrofit;
    }

}
