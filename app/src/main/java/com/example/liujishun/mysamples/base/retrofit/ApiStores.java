package com.example.liujishun.mysamples.base.retrofit;


import com.example.liujishun.mysamples.mvp.MainModel;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 *
 */
public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = "http://www.weather.com.cn/";

    //加载天气
    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> loadData(@Path("cityId") String cityId);

    @POST("mobile/aidaudit/getJiuYeKunNanList")
    @FormUrlEncoded
    Observable<String> getList(
            @Header("Cookie") String sessioned,
            @Field("streetId") String streetId,
            @Field("communityId") String communityId,
            @Field("name") String name,
            @Field("time") String time,
            @Field("handicappedno") String handicappedno,
            @Field("page") String page,
            @Field("rows") String rows);
    //文件上传
    @Multipart
    @POST("register")
    Observable<ResponseBody> registerUser(
            @Part MultipartBody.Part photo,
            @Part("username") RequestBody username,
            @Part("password") RequestBody password);

    //文件下载
    @GET
    Observable<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);
}
