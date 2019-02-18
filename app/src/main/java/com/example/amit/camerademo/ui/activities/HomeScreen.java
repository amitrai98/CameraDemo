package com.example.amit.camerademo.ui.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.amit.camerademo.R;
import com.example.amit.camerademo.ui.commonui.BaseActivity;
import com.example.amit.camerademo.ui.fragments.CameraFragment;
import com.example.amit.camerademo.ui.viewmodels.CameraViewModel;

public class HomeScreen extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initNetworkErrorDialog();
        changeFragment(new CameraFragment(), CameraFragment.TAG);

    }

    @Override
    protected void onNetworkRetryButtonClick() {
        Log.e(TAG, "check again for network connectivity");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
