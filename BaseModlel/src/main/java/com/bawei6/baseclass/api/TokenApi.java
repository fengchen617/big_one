package com.bawei6.baseclass.api;

import com.bawei6.baseclass.bean.TokenBean;
import com.bawei6.baseclass.bean.UserInfoBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/**
 * @author fengchen
 * @date 2019/12/28.
 * @description：Beas——Api
 */
public interface TokenApi {
    @FormUrlEncoded
    @POST("token")
    Call<TokenBean> getToken(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

    @POST("api/User/getUserInfo/")
    Observable<UserInfoBean> getUserInfo(@Body int id);
}
