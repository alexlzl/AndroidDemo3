package fingertip.creditease.com.aidlc1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import fingertip.creditease.com.aidlc.IImoocAidl;
import fingertip.creditease.com.aidlc.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEtNum1, mEtNum2;
    private TextView mTvResult;
    private Button mBtnAdd;
    private IImoocAidl iImoocAidl;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("TAG","onServiceConnected");
            //拿到了远程的服务代理
            iImoocAidl = IImoocAidl.Stub.asInterface(service);
        }

        //断开服务的时候
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("TAG","onServiceDisconnected");
            //回收资源
            iImoocAidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //应用一启动就绑定服务
        bindSersvice();
    }

    private void initView() {
        mEtNum1 = (EditText) findViewById(R.id.et_num1);
        mEtNum2 = (EditText) findViewById(R.id.et_num2);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int num1 = Integer.parseInt(mEtNum1.getText().toString());
        int num2 = Integer.parseInt(mEtNum2.getText().toString());
        try {
            //调用远程的服务
            int res = iImoocAidl.add(num1, num2);
            mTvResult.setText(String.valueOf(res));
            Person person=new Person("alex",100);
            List<Person> persons=iImoocAidl.addPerson(person);

            mTvResult.setText(mTvResult.getText().toString()+"===="+persons.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
            mTvResult.setText("错误了");
        }
        try {
            List<Person> persons = iImoocAidl.addPerson(new Person("atwal", 21));
            Log.d("TAG", persons.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bindSersvice() {
        Log.e("TAG","bindSersvice");
        //获取到服务端
        Intent intent = new Intent();
//        //新版本（5.0以上）必须显示Intent启动绑定服务
        intent.setComponent(new ComponentName("fingertip.creditease.com.aidlc", "fingertip.creditease.com.aidlc.IRemoteService"));
//        bindService(new Intent("cc.abto.server.action"), conn, Context.BIND_AUTO_CREATE);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

}