package fingertip.creditease.com.async;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.test_tv);


    }

      public void test(View view){
          Log.e("TAG","threadid===="+Thread.currentThread().getId());
          Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
//          new MyThread().start();
          runOnUiThread(new MyThread());
//          for(int i=0;i<100000;){
//              i++;
//              ((TextView)view).setText(i+"");
//              Log.e("TAG","i===="+i);
//          }

          Intent intent=new Intent();
          intent.setClass(this,Main2Activity.class);
          startActivity(intent);
      }

    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i=0;i<50000;){
                i++;
                final int finalI = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(finalI +"");
                    }
                });
//                ((TextView)view).setText(i+"");
                Log.e("TAG","i===="+i+"getThreadid====="+Thread.currentThread().getId());
            }


        }
    }
}
