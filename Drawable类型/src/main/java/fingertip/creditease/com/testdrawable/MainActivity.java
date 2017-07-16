package fingertip.creditease.com.testdrawable;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageView imageViewScale;
    private ImageView imageViewLevelList;
    private ImageView imageViewTransition;
    private ImageView  imageViewRotate;
    private ImageView imageViewAnimationList;
    private ImageView imageViewRotated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        imageViewRotated= (ImageView) findViewById(R.id.testAnimatedRoated);
        imageViewAnimationList= (ImageView) findViewById(R.id.animation_list);
        imageViewRotate= (ImageView) findViewById(R.id.rotate);
        imageViewTransition= (ImageView) findViewById(R.id.tansition);
//        ((TransitionDrawable)imageViewTransition.getDrawable()).startTransition(500);
        imageViewLevelList= (ImageView) findViewById(R.id.level_list);
        imageViewLevelList.getDrawable().setLevel(10);

        imageViewScale= (ImageView) findViewById(R.id.scale);
        imageViewScale.getDrawable().setLevel(5000); //level范围值0~10000
        /**
         * 使用clip标签可以对drawable进行裁剪，在做进度条时很有用。通过设置level值控制裁剪多少，level取值范围为0~10000，默认为0，表示完全裁剪，图片将不可见；10000则完全不裁剪，可见完整图片。看看clip标签可以设置的属性：
         */
        imageView = (ImageView) findViewById(R.id.test_clip);
        imageView.getDrawable().setLevel(5000);
    }

    public void onTest(View view) {
        Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
    }

    public void testLevelList(View view){
        Toast.makeText(this,"testLevelList",Toast.LENGTH_LONG).show();
       int level= imageViewLevelList.getDrawable().getLevel();
        if(level>=100){
            imageViewLevelList.getDrawable().setLevel(100);
        }else{
            imageViewLevelList.getDrawable().setLevel(level+30);
        }

    }

    public void testTransition(View view){
        Toast.makeText(this,"testTransition",Toast.LENGTH_LONG).show();
        ((TransitionDrawable)imageViewTransition.getDrawable()).startTransition(500);
    }

    public void testRotate(View view){
        Toast.makeText(this,"testRotate",Toast.LENGTH_LONG).show();
        ((RotateDrawable)imageViewRotate.getDrawable()).setLevel(5000);
    }

    public void testAnimationList(View view){
        Toast.makeText(this,"testAnimationList",Toast.LENGTH_LONG).show();
        ((AnimationDrawable)imageViewAnimationList.getDrawable()).start();
    }

    public void testAnimatedRoated(View view){
        Toast.makeText(this,"testAnimatedRoated",Toast.LENGTH_LONG).show();
    }

    public void testhtml(View view){
        Resources res = getResources();
        String text = String.format(res.getString(R.string.welcome_messages), "alex", 1);
        CharSequence styledText = Html.fromHtml(text);
        ((TextView)view).setText(styledText);
    }
}
