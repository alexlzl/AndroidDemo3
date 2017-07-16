package fingertip.creditease.com.testintentsend;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testIntent(View view) {
        try {
            Toast.makeText(this, "to activity", Toast.LENGTH_LONG).show();
            ComponentName componentName = new ComponentName("fingertip.creditease.com.testintent", "fingertip.creditease.com.testintent.Main2Activity");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "no activity", Toast.LENGTH_LONG).show();
        }


    }

    /**
     * Describe: 启动第三方页面
     * <p>
     * Author: lzl
     * <p>
     * Time: 2016/12/27 下午4:18
     */
    public void testIntent1(View view) {
        try {
            Toast.makeText(this, "to", Toast.LENGTH_LONG).show();
            Intent intent = new Intent("com.intent.test");
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "no", Toast.LENGTH_LONG).show();
        }


    }

    /**
     * Describe: 启动第三方页面
     * <p>
     * Author: lzl
     * <p>
     * Time: 2016/12/27 下午4:18
     */
    public void testIntent2(View view) {
        Intent intent = new Intent();
        intent.setClassName("fingertip.creditease.com.testintent", "fingertip.creditease.com.testintent.Main3Activity");
        startActivity(intent);

    }

    public void testIntent3(View view){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, 12)
                .putExtra(AlarmClock.EXTRA_MINUTES, 12);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void testIntent4(View view){
        Uri uri = Uri.parse("smsto://10086");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", "测试短信");
        startActivity(Intent.createChooser(intent, "发送短信"));
    }
}
