package com.example.amit.camerademo.ui.activities;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.amit.camerademo.R;
import com.example.amit.camerademo.util.NetworkStatusReceiver;
import com.example.amit.camerademo.util.NetworkUtil;

public abstract class NetworkStatusCheckActivity extends AppCompatActivity {

    protected abstract void onNetworkConnected();

    protected abstract void onNetworkDisconnected();

    protected AlertDialog networkErrorDialog;

    private NetworkStatusReceiver mNetworkStatusReceiver;

    @Override
    protected void onStart() {
        super.onStart();
        registerNetworkStatusReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unRegisterNetworkStatusReceiver();
    }

    private void registerNetworkStatusReceiver() {
        mNetworkStatusReceiver = new NetworkStatusReceiver();
        mNetworkStatusReceiver.setConnectionCallback(new NetworkStatusReceiver.ConnectionCallback() {
            @Override
            public void onConnected() {
                onNetworkConnected();
            }

            @Override
            public void onDisConnected() {
                onNetworkDisconnected();
            }
        });
        registerReceiver(mNetworkStatusReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void unRegisterNetworkStatusReceiver() {
        unregisterReceiver(mNetworkStatusReceiver);
    }

    protected void initNetworkErrorDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.network_error, null);
        Button retryButton = layout.findViewById(R.id.retry_button);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkUtil.isNetworkAvailable(getBaseContext())) return;
                networkErrorDialog.hide();
                onNetworkRetryButtonClick();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(layout)
                .setCancelable(true)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                });
        networkErrorDialog = builder.create();
        networkErrorDialog.setCanceledOnTouchOutside(false);
    }

    protected abstract void onNetworkRetryButtonClick();

}
