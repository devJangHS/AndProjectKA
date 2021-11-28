package com.jhs.sns.network;

import com.jhs.sns.data.Post;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostAPI {

    @POST("post")
    Call<Void> createPost(@Body Post post);

    @GET("post")
    Call<ArrayList<Post>> getPostList(@Query("type") int type);

    @GET("post/{id}")
    Call<Post> getPost(@Path("id") int id);

    @PUT("post/{id}")
    Call<Void> updatePost(@Path("id") int id, @Body Post post);

    @DELETE("post/{id}")
    Call<Void> deletePost(@Path("id") int id);

    @Multipart
    @POST("upload")
    Call<Void> uploadFile(@Part MultipartBody.Part file);

}