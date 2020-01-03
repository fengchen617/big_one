package com.bawei6.usercenter.api;

import com.bawei6.baseclass.bean.UserInfoBean;
import com.bawei6.usercenter.bean.FindFriendBean;
import com.bawei6.usercenter.bean.LoginBean;
import com.bawei6.usercenter.bean.RegisterBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author fengchen
 * @date 2019/12/30.
 * @description：
 */
public interface RxApi {
    @POST("api/User/login")
    @FormUrlEncoded
    Observable<UserInfoBean<LoginBean>> ob_login(
            @Field("username")String username ,
            @Field("pwd")String pwd);

    @POST("api/User/register")
    Observable<UserInfoBean<RegisterBean>> re_ob(@Body RegisterBean registerBean);

    @GET("api/Friend/getFriends")
    Observable<UserInfoBean<List<FindFriendBean>>> ob_find(
            @Query("usercode") String username );
}
