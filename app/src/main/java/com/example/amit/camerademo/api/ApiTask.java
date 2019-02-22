package com.example.amit.camerademo.api;


import com.example.amit.camerademo.api.models.ImageUploadResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiTask {

    @Multipart
    @POST("/api/beta/content.php")
    Call<ImageUploadResponse> uploadFile(@Query("cid") String cid,
                                         @Part MultipartBody.Part file,
                                         @Part("type") RequestBody name);

    }
