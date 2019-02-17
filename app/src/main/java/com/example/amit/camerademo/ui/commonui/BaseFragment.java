package com.example.amit.camerademo.ui.commonui;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
    public BaseFragment() {
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
