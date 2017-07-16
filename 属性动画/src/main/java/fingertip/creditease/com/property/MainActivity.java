package fingertip.creditease.com.property;

import android.animation.IntEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static String TAG=MainActivity.class.getSimpleName();
    private Button button;
    private Button buttono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        buttono= (Button) findViewById(R.id.buttono);
    }

    public void valueAnimator(View view) {
        //1.设置属性的初始值和结束值
//        ValueAnimator mAnimator = ValueAnimator.ofInt(0, 100);也可以实现
        ValueAnimator mAnimator = new ValueAnimator();
        mAnimator.setIntValues(0,100);
        //2.为目标对象的属性变化设置监听器
        //(每个时间片调用一次   调用次数=setDuration的时常/时间片（系统每隔多长时间执行一次）)
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatorValue = (int) animation.getAnimatedValue();
                Log.e(TAG,"ValueAnimator值=="+animatorValue);
                float fraction = animatorValue / 100f;
                IntEvaluator mEvaluator = new IntEvaluator();
                //3.使用IntEvaluator计算属性值并赋值给View的高
                button.getLayoutParams().height = mEvaluator.evaluate(fraction, 0, 500);
                button.requestLayout();
            }
        });
        //4.为ValueAnimator设置LinearInterpolator
        mAnimator.setInterpolator(new LinearInterpolator());
        //5.设置动画的持续时间
        mAnimator.setDuration(500);
        //6.为ValueAnimator设置目标对象并开始执行动画
//        mAnimator.setTarget(button);
        mAnimator.start();
    }

    public void objcetAnimator(View view) {
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.2f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(buttono, pvhRotation);
        rotationAnim.setDuration(5000);
        rotationAnim.start();
    }
    public void objcetAnimator2(View view) {
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(view,"rotation",0f,360f,0f);
        objectAnimator.setDuration(5000);
        objectAnimator.start();
    }
}
