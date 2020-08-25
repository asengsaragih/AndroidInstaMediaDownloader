package com.suncode.bloggerapi.service;

import com.suncode.bloggerapi.base.Constant;
import com.suncode.bloggerapi.model.RetrieveBlog;
import com.suncode.bloggerapi.model.RetrieveComment;
import com.suncode.bloggerapi.model.RetrievePost;
import com.suncode.bloggerapi.model.RetrieveSpecificPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BloggerService {
    @Headers("key:" + Constant.API_KEY)
    @GET("/blogger/v3/blogs/{blogId}")
    public Call<RetrieveBlog> getBlog(@Path("blogId") String blogId);

//    @GET("/blogger/v3/blogs/{blogId}/posts")
//    public Call<RetrievePost> getAllPost(@Path("blogId") String blogId);
//
//    @GET("/blogger/v3/blogs/{blogId}/posts/{postId}")
//    public Call<RetrieveSpecificPost> getPost(@Path("blogId") String blogId, @Path("postId") String postId);
//
//    @GET("/blogger/v3/blogs/{blogId}/posts/{postId}/comments")
//    public Call<RetrieveComment> getCommentPost(@Path("blogId") String blogId, @Path("postId") String postId);
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       