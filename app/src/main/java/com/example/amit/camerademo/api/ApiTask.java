package com.example.amit.camerademo.api;


import com.example.amit.camerademo.api.models.ImageUploadResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiTask {

        @Multipart
        @POST("parser")
        Observable<ImageUploadResponse> uploadImage(
                @Header("user-session-token") String loginToken, @Header("user-id") String userId,
                @Part MultipartBody.Part[] receiptImages
        );

    }
