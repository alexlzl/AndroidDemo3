package fingertip.creditease.com.messengerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/6 下午2:52
 */

public class MyService extends Service {
    private static final int RECEIVE_MESSAGE_CODE = 0x0001;

    private static final int SEND_MESSAGE_CODE = 0x0002;

    //clientMessenger表示的是客户端的Messenger，可以通过来自于客户端的Message的replyTo属性获得，
    //其内部指向了客户端的ClientHandler实例，可以用clientMessenger向客户端发送消息
    private Messenger clientMessenger = null;

    //serviceMessenger是Service自身的Messenger，其内部指向了ServiceHandler的实例
    //客户端可以通过IBinder构建Service端的Messenger，从而向Service发送消息，
    //并由ServiceHandler接收并处理来自于客户端的消息
    private Messenger serviceMessenger = new Messenger(new ServiceHandler());

    //MyService用ServiceHandler接收并处理来自于客户端的消息
    private class ServiceHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.i("DemoLog", "ServiceHandler -> handleMessage");
            if(msg.what == RECEIVE_MESSAGE_CODE){
                Bundle data = msg.getData();
                if(data != null){
                    String str = data.getString("msg");
                    Log.i("DemoLog", "MyService收到客户端如下信息: " + str);
                    Toast.makeText(MyService.this,"MyService收到客户端如下信息: " + str,Toast.LENGTH_LONG).show();
                }
                //通过Message的replyTo获取到客户端自身的Messenger，
                //Service可以通过它向客户端发送消息
                clientMessenger = msg.replyTo;
                if(clientMessenger != null){
                    Log.i("DemoLog", "MyService向客户端回信");
                    Message msgToClient = Message.obtain();
                    msgToClient.what = SEND_MESSAGE_CODE;
                    //可以通过Bundle发送跨进程的信息
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "你好，客户端，我是MyService");
                    msgToClient.setData(bundle);
                    try{
                        clientMessenger.send(msgToClient);
                    }catch (RemoteException e){
                        e.printStackTrace();
                        Log.e("DemoLog", "MyService向客户端发送信息失败: " + e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void onCreate() {
        Log.i("DemoLog", "MyService -> onCreate");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("DemoLog", "MyServivce -> onBind");
        //获取Service自身Messenger所对应的IBinder，并将其发送共享给所有客户端
        return serviceMessenger.getBinder();
    }

    @Override
    public void onDestroy() {
        Log.i("DemoLog", "MyService -> onDestroy");
        clientMessenger = null;
        super.onDestroy();
    }
}
