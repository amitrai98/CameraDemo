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
    public Call<ImageUploadResponse> uploadFile(String cid, MultipartBody.Part file, RequestBody name) {
        return apiTask.uploadFile(cid, file, name);
    }
}
