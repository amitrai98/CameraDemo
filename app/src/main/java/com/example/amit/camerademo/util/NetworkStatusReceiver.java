package com.example.amit.camerademo.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkStatusReceiver extends BroadcastReceiver {

    private final String TAG = NetworkStatusReceiver.class.getSimpleName();
    private ConnectionCallback mCallback;

    public interface ConnectionCallback {
        void onConnected();

        void onDisConnected();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) return;
            NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
            boolean noNetwork = intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
                    || (ni != null && ni.getState() != NetworkInfo.State.CONNECTED);
            if (noNetwork) {
                Log.d(TAG, "There's no network connectivity");
                mCallback.onDisConnected();
            } else {
                if (ni != null)
                    Log.i(TAG, "Network " + ni.getTypeName() + " connected");
                mCallback.onConnected();
            }
        }
    }

    public void setConnectionCallback(ConnectionCallback callback) {
        mCallback = callback;
    }
}

