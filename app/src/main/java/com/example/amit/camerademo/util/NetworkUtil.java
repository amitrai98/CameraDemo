package com.example.amit.camerademo.util;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class NetworkUtil{

    private static NetworkUtil mInstance;
    private NetworkStatusReceiver mNetworkStatusReceiver;

    public static NetworkUtil getInstance(){
        if(mInstance == null){
            mInstance = new NetworkUtil();
        }
        return mInstance;
    }

    public static boolean isNetworkAvailable(Context context){
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void registerNetworkStatusReceiver(Context context, NetworkStatusReceiver.ConnectionCallback callback) {
        mNetworkStatusReceiver = new NetworkStatusReceiver();
        mNetworkStatusReceiver.setConnectionCallback(callback);
        context.registerReceiver(mNetworkStatusReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void unRegisterNetworkStatusReceiver(Context context) {
        context.unregisterReceiver(mNetworkStatusReceiver);
    }


}
