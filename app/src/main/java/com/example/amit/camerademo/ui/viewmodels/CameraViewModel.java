package com.example.amit.camerademo.ui.viewmodels;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.runtimepermission.PermissionHandler;
import com.app.runtimepermission.PermissionListener;
import com.example.amit.camerademo.R;
import com.example.amit.camerademo.api.ApiServiceFactory;
import com.example.amit.camerademo.api.ApiTask;
import com.example.amit.camerademo.api.models.ImageUploadResponse;
import com.example.amit.camerademo.ui.activities.HomeScreen;
import com.example.amit.camerademo.ui.commonui.BaseActivity;
import com.example.amit.camerademo.ui.fragments.CameraFragment;
import com.example.amit.camerademo.util.Utility;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class CameraViewModel extends ViewModel {
    public static final int TAKE_PHOTO_CODE = 101;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 102;
    private final String TAG = getClass().getSimpleName();
    CameraFragment cameraFragment;
    public ObservableField<String> imageUrl = new ObservableField<>("");
    private BindableFieldTarget bindableFieldTarget;
    public ObservableField<Drawable> profileImage;
    public ObservableField<Integer> enableUpload = new ObservableField<>(new Integer(View.GONE));
    public ObservableField<Integer> uploading = new ObservableField<>(new Integer(View.GONE));
    public ObservableField<Uri> imageuri = new ObservableField<>();



    public CameraViewModel(CameraFragment cameraFragment){
        this.cameraFragment = cameraFragment;
        profileImage = new ObservableField<>();
        // Picasso keeps a weak reference to the target so it needs to be stored in a field
        bindableFieldTarget = new BindableFieldTarget(profileImage, cameraFragment.getResources());
        Picasso.get()
                .load(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(bindableFieldTarget);


    }
    public void openCamera(){
        Log.e(TAG, "i was called");
        if (ActivityCompat.checkSelfPermission(cameraFragment.getContext(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            cameraFragment.getCameraPermission();
        } else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            cameraFragment.startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
        }
    }


    public void uploadImage(){
        Log.e(TAG, "upload image");

        uploading.set(View.VISIBLE);

        ApiTask apiTask = ApiServiceFactory.getInstance().getApiTask();
//        apiTask.uploadFile(new File(imageUrl), "")

                //creating a file
        File file = new File(imageUrl.get());
        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Call<ImageUploadResponse> call = apiTask.uploadFile(fileToUpload, filename);

        call.enqueue(new Callback<ImageUploadResponse>() {
            @Override
            public void onResponse(Call<ImageUploadResponse> call, Response<ImageUploadResponse> response) {
                uploading.set(View.GONE);
                Log.e(TAG, ""+response.raw().message());
                if (cameraFragment.getActivity() instanceof HomeScreen){
                    HomeScreen activity = (HomeScreen) cameraFragment.getActivity();
                    activity.showSnackBarMessage("Image to uploaded successfully");
                }
            }
            @Override
            public void onFailure(Call<ImageUploadResponse> call, Throwable t) {
                uploading.set(View.GONE);
                if (cameraFragment.getActivity() instanceof HomeScreen){
                    HomeScreen activity = (HomeScreen) cameraFragment.getActivity();
                    activity.showSnackBarMessage("Unable to upload image");
                }

                Log.e(TAG, "Error " + t.getMessage());
            }
        });

    }





    public class BindableFieldTarget implements Target {

        private ObservableField<Drawable> observableField;
        private Resources resources;

        public BindableFieldTarget(ObservableField<Drawable> observableField, Resources resources) {
            this.observableField = observableField;
            this.resources = resources;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            observableField.set(new BitmapDrawable(resources, bitmap));
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            observableField.set(errorDrawable);
        }



        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            observableField.set(placeHolderDrawable);
        }
    }
}
