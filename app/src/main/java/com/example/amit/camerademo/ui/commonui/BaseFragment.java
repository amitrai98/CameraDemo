package com.example.amit.camerademo.ui.commonui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.amit.camerademo.util.Utility;

public class BaseFragment extends Fragment {

    public Utility utility;
    public BaseFragment() {
    }


    @Override
    public void onStart() {
        super.onStart();
        utility = new Utility();

    }

    public void onNetworkConnected() {
        //child fragment has to provide the implementation
    }

    public void onNetworkDisconnected() {
        //child fragment has to provide the implementation
    }

    public void onNetworkRetryButtonClick() {
        //child fragment has to provide the implementation
    }

}
