package com.example.amit.camerademo.api;

import com.example.amit.camerademo.api.models.ImageUploadResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

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

    @Override
    public Call<ImageUploadResponse> uploadFile(MultipartBody.Part file, RequestBody name) {
        return apiTask.uploadFile(file, name);
    }
}
