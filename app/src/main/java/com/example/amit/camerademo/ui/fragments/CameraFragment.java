package com.example.amit.camerademo.ui.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amit.camerademo.R;
import com.example.amit.camerademo.api.models.ImageUploadModel;
import com.example.amit.camerademo.databinding.FragmentCameraBinding;
import com.example.amit.camerademo.ui.commonui.BaseFragment;
import com.example.amit.camerademo.ui.viewmodels.CameraViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static android.app.Activity.RESULT_OK;

public class CameraFragment extends BaseFragment {

    FragmentCameraBinding binding;
    CameraViewModel viewModel;

    public static String TAG = CameraFragment.class.getSimpleName();
    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_camera, container, false);
        viewModel = new CameraViewModel(this);
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CameraViewModel.MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "permission granged");
                    viewModel.openCamera();
                } else {
                    Log.e(TAG, "permission denied");
                }
                return;
            }
        }
    }

    public void getCameraPermission(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions( //Method of Fragment
                    new String[]{Manifest.permission.CAMERA},
                    CameraViewModel.MY_PERMISSIONS_REQUEST_CAMERA
            );
        } else {
            viewModel.openCamera();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CameraViewModel.TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
             if (photo != null){
                 viewModel.profileImage.set(new BitmapDrawable(getResources(), photo));
                 viewModel.enableUpload.set(View.VISIBLE);
             }
             else
                 Log.e(TAG, "photo null");

            try {Uri tempUri = getImageUri(getContext(), photo);
                viewModel.imageuri.set(tempUri);
                viewModel.imageUrl.set(getRealPathFromURI(tempUri));
            }catch (Exception exp){
                exp.printStackTrace();
            }


        }
    }



    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContext().getContentResolver() != null) {
            Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }
}
