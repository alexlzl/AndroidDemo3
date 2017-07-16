package fingertip.creditease.com.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;
//import android.widget.TextView;

/**
 * Describe：我们自定义控件的时候，最常见的需要重写构造函数是 public View(Context context) {} 和 public View(Context context, @Nullable AttributeSet attrs) {}, 因为第一个是在 Java 代码中创建 View 时会调用，第二是在 XML 布局文件中引用 View 时，会被调用。这两个构造函数都是系统会调用的方法。但是，还剩两个构造函数` public View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {} 和 public View(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {}, 是需要我们自行调用的，系统并不会调用，当我们需要为自定义 View 设置默认的属性的时候，就需要用到了。
 * <p>
 * <p>
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/3/14 下午5:43
 */

public class AttrTestTextView extends TextView {
    public AttrTestTextView(Context context) {
        super(context);
    }

    public AttrTestTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.defaultStyleAttr);
    }

    public AttrTestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.DefaultStyleRes);
    }

    /**
     * Describe: 可以看出来，属性的获取主要由 context.obtainStyledAttributes 决定，而最后两个参数defStyleAttr和defStyleRes决定了最终默认的属性样式是怎么的。
     * <p>
     * Author: lzl
     * <p>
     * Time: 2017/3/14 下午6:05
     */
    public AttrTestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        /**
         * 　4个参数的意思分别是：

         　　　　set：属性值的集合

         　　　　attrs：我们要获取的属性的资源ID的一个数组，如同ContextProvider中请求数据库时的Projection数组，就是从一堆属性中我们希望查询什么属性的值

         　　　　defStyleAttr：这个是当前Theme中的一个attribute，是指向style的一个引用，当在layout xml中和style中都没有为View指定属性时，会从Theme中这个attribute指向的Style中查找相应的属性值，这就是defStyle的意思，如果没有指定属性值，就用这个值，所以是默认值，但这个attribute要在Theme中指定，且是指向一个Style的引用，如果这个参数传入0表示不向Theme中搜索默认值

         　　　　defStyleRes：这个也是指向一个Style的资源ID，但是仅在defStyleAttr为0或defStyleAttr不为0但Theme中没有为defStyleAttr属性赋值时起作用
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttrTestTextView,
                defStyleAttr, defStyleRes);
        StringBuilder sb = new StringBuilder();
        sb.append(typedArray.getString(R.styleable.AttrTestTextView_firstAttr)).append("\n");
        sb.append(typedArray.getString(R.styleable.AttrTestTextView_secondAttr)).append("\n");
        sb.append(typedArray.getString(R.styleable.AttrTestTextView_thirdAttr)).append("\n");
        sb.append(typedArray.getString(R.styleable.AttrTestTextView_fourthAttr)).append("\n");
        sb.append(typedArray.getString(R.styleable.AttrTestTextView_fifthAttr));

        setText(sb);


        typedArray.recycle();
    }
}
