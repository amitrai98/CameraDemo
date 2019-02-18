package com.example.amit.camerademo.ui.commonui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.amit.camerademo.R;
import com.example.amit.camerademo.ui.activities.NetworkStatusCheckActivity;

public abstract class BaseActivity extends NetworkStatusCheckActivity {
    public FrameLayout mFrameLayout;
    private Toolbar mToolbar;
    protected TextView mToolbarTitle;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initViews();
        initNetworkErrorDialog();

    }



    private void initViews() {
        mFrameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
    }



    public void changeFragment(@NonNull Fragment fragment, @Nullable String tag){
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(tag).commit();
        } else {
            getSupportFragmentManager().popBackStack(tag, 0);
        }
    }



    @Override
    protected void onNetworkConnected() {
        networkErrorDialog.hide();
    }

    @Override
    protected void onNetworkDisconnected() {
        networkErrorDialog.show();

    }


}
