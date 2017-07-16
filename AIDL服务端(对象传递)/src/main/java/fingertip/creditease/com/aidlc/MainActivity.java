package fingertip.creditease.com.aidlc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 开发流程一般为：
 1、定义AIDL文件（先在服务端定义，客户端也要定义，内容路服务端一样）
 2、服务端实现接口
 3、客户端调用接口
 三、AIDL语法规则
 语法规则如下：
 1、语法跟Java的接口很相似
 2、AIDL只支持方法，不能定义静态成员
 3、AIDL运行方法有任何类型的参数（除short外）和返回值
 4、除默认类型，均需要导包
 四、AIDL支持的数据类型
 1、除short外的基本数据类型
 2、String, CharSequence
 3、List（作为参数时，要指明是输入in 还是输出out）, Map
 4、Parcelable （自定义类型要实现 Parcelable 接口）
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
