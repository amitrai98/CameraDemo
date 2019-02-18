package com.example.amit.camerademo.ui.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.amit.camerademo.R;
import com.example.amit.camerademo.ui.commonui.BaseActivity;
import com.example.amit.camerademo.ui.fragments.CameraFragment;
import com.example.amit.camerademo.ui.viewmodels.CameraViewModel;

public class HomeScreen extends BaseActivity {

    private final String TAG = getClass().getSimpleName();
    CoordinatorLayout coordinator = null;
    private boolean exit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        coordinator= findViewById(R.id.coordinator);
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

    public void showSnackBarMessage(String message){
        Snackbar snackbar = Snackbar
                .make(coordinator, message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void onBackPressed() {

        int index = getSupportFragmentManager().getBackStackEntryCount();

        if (index >1){
            super.onBackPressed();
        }else{
            if (exit) {
                finish(); // finish activity
            } else {
                showSnackBarMessage("Press Back again to Exit");
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);

            }
        }

    }
}
