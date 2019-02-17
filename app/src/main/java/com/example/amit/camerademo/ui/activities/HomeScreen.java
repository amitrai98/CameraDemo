package com.example.amit.camerademo.ui.activities;

import android.os.Bundle;
import android.util.Log;

import com.example.amit.camerademo.R;
import com.example.amit.camerademo.ui.commonui.BaseActivity;
import com.example.amit.camerademo.ui.fragments.CameraFragment;

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
}
