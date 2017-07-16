package fingertip.creditease.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Describe: 1.从执行顺序分析
 * Constructor->onFinishInflate->onMeasure..->onSizeChanged->onLayout->addOnGlobalLayoutListener->onWindowFocusChanged->onMeasure->onLayout
 * 由上可知,onMeasure和onLayout会被多次调用.
 * <p>
 * 2.从api上分析
 * 1)Constructor:构造方法,View初始化的时候调用,在这里是无法获取其子控件的引用的.更加无法获取宽高了.
 * <p>
 * 2)onFinishInflate:当布局初始化完毕后回调,在这里可以获取所有直接子View的引用,但是无法获取宽高.
 * <p>
 * 3)onMeasure:当测量控件宽高时回调,当调用了requestLayout()也会回调onMeasure.在这里一定可以通过getMeasuredHeight()和getMeasuredWidth()来获取控件的高和宽,但不一定可以通过getHeight()和getWidth()来获取控件宽高,因为getHeight()和getWidth()必须要等onLayout方法回调之后才能确定.
 * <p>
 * 4)onSizeChanged:当控件的宽高发生变化时回调,和onMeasure一样,一定可以通过getMeasuredHeight()和getMeasuredWidth()来获取控件的高和宽,因为它是在onMeasure方法执行之后和onLayout方法之前回调的.
 * <p>
 * 5)onLayout:当确定控件的位置时回调,当调用了requestLayout()也会回调onLayout.在这里一定可以通过getHeight()和getWidth()获取控件的宽高,同时由于onMeasure方法比onLayout方法先执行,所以在这里也可以通过getMeasuredHeight()和getMeasuredWidth()来获取控件的高和宽.
 * <p>
 * 6)addOnGlobalLayoutListener:当View的位置确定完后会回调改监听方法,它是紧接着onLayout方法执行而执行的,只要onLayout方法调用了,那么addOnGlobalLayoutListener的监听器就会监听到.在这里getMeasuredHeight()和getMeasuredWidth()和getHeight()和getWidth()都可以获取到宽高.
 * <p>
 * 7)onWindowFocusChanged:当View的焦点发送改变时回调,在这里getMeasuredHeight()和getMeasuredWidth()和getHeight()和getWidth()都可以获取到宽高.Activity也可以通过重写该方法来判断当前的焦点是否发送改变了;需要注意的是这里View获取焦点和失去焦点都会回调.
 * <p>
 * 那么,上面分析了那么多方法来获取控件的宽高,那到底用哪一种呢?
 * 具体要用哪一种,是需要根据View的宽高是否会发生变化来决定:
 * 1.如果自定义的View在使用的过程中宽高信息是不会改变的,那么上面方式3~方式7都可以使用.
 * <p>
 * 2.如果自定义的View在使用过程中宽高信息都会发生改变的,而且又需要获取一开始时的宽高信息,那么建议使用View.getViewTreeObserver().addOnGlobalLayoutListener(OnGlobalLayoutListener listener)的方式,因为这种方式有getViewTreeObserver().removeOnGlobalLayoutListener(this);来避免回调函数因宽高信息的变化而多次调用,如果使用其他方式的话,就要借助额外的变量来保证获取到的宽高是View的初始高度.
 * <p>
 * Author: lzl
 * <p>
 * Time: 2017/3/28 下午6:22
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TestLayout";
    private  TestLayout  testLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testLayout=new TestLayout(this);
//        setContentView(testLayout);
        Log.e(TAG,"======MainActivity==执行onCreate=======");
//        testLayout= (TestLayout) findViewById(R.id.testlayout);
//        testLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(testLayout.getVisibility()==View.VISIBLE){
//                    testLayout.setVisibility(View.GONE);
//                }else{
//                    testLayout.setVisibility(View.VISIBLE);
//                }
//
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"======MainActivity==执行onStart=======");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"======MainActivity==执行onResume=======");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.e(TAG,"======MainActivity==执行onPostResume=======");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"======MainActivity==执行onPause=======");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"======MainActivity==执行onStop=======");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"======MainActivity==执行onDestroy=======");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e(TAG,"======MainActivity==执行onWindowFocusChanged=======");
    }
}
