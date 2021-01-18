package com.example.taskplanner.api_interfaces;

import com.example.taskplanner.models.TaskInfo;
import com.example.taskplanner.models.UserInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface JsonPlaceHolderApi {

    @GET("getData")
    Call<List<UserInfo>> getPosts();

    @GET("getTask")
    Call<ArrayList<TaskInfo>> getTask();

    @GET("getFavTask")
    Call<ArrayList<TaskInfo>> getFavTask();



    @POST("postData")
    Call<UserInfo> createPost(@Body UserInfo userInfo);


    @POST("tasklist")
    Call<com.example.taskplanner.models.TaskInfo> addTask(@Body TaskInfo taskInfo);

    @POST("favtasklist")
    Call<com.example.taskplanner.models.TaskInfo> addFavTask(@Body com.example.taskplanner.models.TaskInfo taskInfo);


    @POST("login")
    Call<UserInfo> login(@Body UserInfo userInfo);

}
