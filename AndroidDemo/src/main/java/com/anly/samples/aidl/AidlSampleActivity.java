package com.anly.samples.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.anly.samples.IRemoteService;
import com.anly.samples.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mingjun on 2016/11/28.
 */

public class AidlSampleActivity extends AppCompatActivity {

    @BindView(R.id.result)
    TextView mResult;

    private IRemoteService mRemoteService;
    private boolean isBound;

    private int mCurrentPrice = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aidl);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onClick() {

        if (!isBound) {
            bindRemoteService();
        }
        else {
            if (mRemoteService != null) {
                try {
                    mCurrentPrice = mRemoteService.signing(mCurrentPrice);
                    mResult.setText("Result:" + mCurrentPrice);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void bindRemoteService() {
        Intent intent = new Intent(this, RemoteService.class);
        bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteService = IRemoteService.Stub.asInterface(service);
            isBound = true;

            if (mRemoteService != null) {
                try {
                    mCurrentPrice = mRemoteService.signing(mCurrentPrice);
                    mResult.setText("Result:" + mCurrentPrice);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mServiceConn);
    }
}
