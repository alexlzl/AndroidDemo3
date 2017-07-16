package fingertip.creditease.com.testglide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private SimpleDraweeView setImageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.test_iv);
//        setImageURI= (SimpleDraweeView) findViewById(R.id.user_avator);
//        Glide.with(this)
//                .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
//                .into(imageView);
    }

    public void ontest(View view){
//        Glide.with(MainActivity.this)
//                .load("http://bpic.588ku.com/back_pic/03/86/14/5557ce5e35973aa.jpg!/fw/650/quality/90/unsharp/true/compress/true")
//                .into(imageView);
        Log.e("TAG","start");
//        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
//        Glide.with(this)
//                .load("http://bpic.588ku.com/back_pic/00/02/38/985617612c20a1e.jpg")
//                .into(imageView);
                  new Runnable() {
              @Override
              public void run() {
                  Log.e("TAG","run=====");
                          Glide.with(MainActivity.this)
                .load("http://bpic.588ku.com/back_pic/03/86/14/5557ce5e35973aa.jpg!/fw/650/quality/90/unsharp/true/compress/true")
                .into(imageView);
                  Glide.with(MainActivity.this)
                          .load("http://bpic.588ku.com/back_pic/03/86/14/5557ce5e35973aa.jpg!/fw/650/quality/90/unsharp/true/compress/true")
                          .into(imageView);
                  Glide.with(MainActivity.this)
                          .load("http://bpic.588ku.com/back_pic/03/86/14/5557ce5e35973aa.jpg!/fw/650/quality/90/unsharp/true/compress/true")
                          .into(imageView);
                  Glide.with(MainActivity.this)
                          .load("http://bpic.588ku.com/back_pic/03/86/14/5557ce5e35973aa.jpg!/fw/650/quality/90/unsharp/true/compress/true")
                          .into(imageView);
                  Glide.with(MainActivity.this)
                          .load("http://bpic.588ku.com/back_pic/03/86/14/5557ce5e35973aa.jpg!/fw/650/quality/90/unsharp/true/compress/true")
                          .into(imageView);
                  Glide.with(MainActivity.this)
                          .load("http://bpic.588ku.com/back_pic/03/86/14/5557ce5e35973aa.jpg!/fw/650/quality/90/unsharp/true/compress/true")
                          .into(imageView);
                  Glide.with(MainActivity.this)
                          .load("http://bpic.588ku.com/back_pic/03/86/14/5557ce5e35973aa.jpg!/fw/650/quality/90/unsharp/true/compress/true")
                          .into(imageView);
                  for(int i=0;i<100000;){
                      Log.e("TAG","i====="+i);
                      i++;
                  }
                  Log.e("TAG","run====end");
//                  while (true){
//                      Log.e("TAG","chthread");
//                  }
//                  try {
//                      Thread.sleep(3000);
//                  } catch (InterruptedException e) {
//                      e.printStackTrace();
//                  }
              }
          }.run();
        Log.e("TAG","Main2Activity");
        Intent intent=new Intent();
        intent.setClass(this,Main2Activity.class);
        startActivity(intent);
//        Uri uri = Uri.parse("http://file.yixin.com/baoxiao/mobile/imagejpg_14822175199891");
//        setImageURI.setImageURI(uri);
    }

}
