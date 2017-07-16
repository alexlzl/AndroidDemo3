package fingertip.creditease.com.aidlservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import fingertip.creditease.com.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {


    private IMyAidlInterface iMyAidlInterface;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(new Intent("cc.abto.server"), serviceConnection, BIND_AUTO_CREATE);
    }

    public void test(View view)
    {
        try
        {
            Toast.makeText(MainActivity.this, iMyAidlInterface.getName(), Toast.LENGTH_SHORT).show();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }


    public void next(View view){
        unbindService(serviceConnection);
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG","MainActivity===onDestroy");
//        unbindService(serviceConnection);
    }
}
