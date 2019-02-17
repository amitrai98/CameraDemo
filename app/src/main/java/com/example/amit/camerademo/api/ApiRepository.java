package com.example.amit.camerademo.api;

import com.example.amit.camerademo.api.models.ImageUploadResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

public class ApiRepository implements ApiTask {
    private final ApiTask apiTask;
    public ApiRepository(ApiTask apiTask) {
        this.apiTask = apiTask;
    }

    @Override
    public Observable<ImageUploadResponse> uploadImage(String loginToken, String userId,
                                                         MultipartBody.Part[] receiptImages) {
        return apiTask.uploadImage(loginToken, userId, receiptImages);
    }
}
