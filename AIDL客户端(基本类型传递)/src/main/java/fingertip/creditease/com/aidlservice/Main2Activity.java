package fingertip.creditease.com.aidlservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import fingertip.creditease.com.aidl.IMyAidlInterface;

public class Main2Activity extends AppCompatActivity {
    private IMyAidlInterface iMyAidlInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bindService(new Intent("cc.abto.server"), new ServiceConnection()
        {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service)
            {

                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name)
            {

            }
        }, BIND_AUTO_CREATE);
    }
    public void testn(View view)
    {
        try
        {
            Toast.makeText(Main2Activity.this, iMyAidlInterface.getName(), Toast.LENGTH_SHORT).show();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

}
