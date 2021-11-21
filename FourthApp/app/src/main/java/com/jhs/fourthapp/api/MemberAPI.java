package com.jhs.fourthapp.api;

import com.jhs.fourthapp.data.Member;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MemberAPI {

    @POST("member")
    Call<Void> regist(@Body Member param);

    @POST("login")
    Call<Void> login(@Body Member param);

    @GET("member")
    Call<List<Member>> getMemberList(@Query("type") int type);

}
