package com.qin.scrollerview;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MultiScreenActivity extends Activity implements OnClickListener {

	private Button bt_scrollLeft;
	private Button bt_scrollRight;
	private MultiViewGroup mulTiViewGroup  ;
	
	public static int screenWidth  ;  // ��Ļ���
	public static int scrrenHeight ;  //��Ļ�߶�
	
	private int curscreen = 0;   // ��ǰλ�ڵڼ���Ļ  ����3��"��Ļ"�� 3��LinearLayout
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //�����Ļ�ֱ��ʴ�С
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		screenWidth = metric.widthPixels ;
		scrrenHeight = metric.heightPixels;		
		System.out.println("screenWidth * scrrenHeight --->" + screenWidth + " * " +scrrenHeight);
		
		setContentView(R.layout.multiview);
 
        //��ȡ�Զ�����ͼ�Ŀռ�����
		mulTiViewGroup = (MultiViewGroup)findViewById(R.id.mymultiViewGroup);
		
		bt_scrollLeft = (Button) findViewById(R.id.bt_scrollLeft);
		bt_scrollRight = (Button) findViewById(R.id.bt_scrollRight);

		bt_scrollLeft.setOnClickListener(this);
		bt_scrollRight.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bt_scrollLeft:
			mulTiViewGroup.startMove() ; //��һ��
			break;
		case R.id.bt_scrollRight:
			mulTiViewGroup.stopMove() ; //ֹͣ����
			break;
		}
	}

}
