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

public interface ApiTask {

        @Multipart
        @POST("/api/beta/content.php?cid=cb92564d4062bae4e5cde57650b88c9a")
        Observable<ImageUploadResponse> uploadImage(
                @Header("user-session-token") String loginToken, @Header("user-id") String userId,
                @Part MultipartBody.Part[] receiptImages
        );

    @Multipart
    @POST("/api/beta/content.php?cid=cb92564d4062bae4e5cde57650b88c9a")
    Call<ImageUploadResponse> uploadFile(@Part MultipartBody.Part file,
                                         @Part("file") RequestBody name);

    }
