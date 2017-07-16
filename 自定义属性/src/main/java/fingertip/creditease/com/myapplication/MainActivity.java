package fingertip.creditease.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Describe: http://www.cnblogs.com/angeldevil/p/3479431.html
 * <p>
 * <p>
 * <p>
 * 　从前面的说明可以得到以下结论：
 * <p>
 * 要为自定义View自定义属性，可以通过declare-styleable声明需要的属性
 * 为了在Theme设置View的默认样式，可以同时定义一个format为reference的属性att_a，在定义Theme时为这个attribute指定一个Style，并且在View的第二个构造函数中将R.attr.attr_a 作为第三个参数调用第三个构造函数
 * 可以定义一个Style作为Theme中没有定义attr_a时View属性的默认值。
 * 可以在Theme中直接为属性赋值，但优先级最低
 * 当defStyleAttr（即View的构造函数的第三个参数）不为0且在Theme中有为这个attr赋值时，defStyleRes（通过obtainStyledAttributes的第四个参数指定）不起作用
 * 属性值定义的优先级：xml>style>Theme中的默认Sytle>默认Style（通过obtainStyledAttributes的第四个参数指定）>在Theme中直接指定属性值
 * <p>
 * Author: lzl
 * <p>
 * Time: 2017/3/15 下午2:16
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
