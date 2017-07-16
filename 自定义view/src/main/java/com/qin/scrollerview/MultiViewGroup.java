package com.qin.scrollerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

//�Զ���ViewGroup �� ����������LinearLayout�ؼ�������ڲ�ͬ�Ĳ���λ�ã�ͨ��scrollBy����scrollTo�����л�
public class MultiViewGroup extends ViewGroup {

	private Context mContext;
	
	private static String TAG = "MultiViewGroup";
    private int curScreen = 0 ;  //��ǰ��
	
    private Scroller mScroller = null ;
    
    
	public MultiViewGroup(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public MultiViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}
   //startScroll ����
	public void startMove(){
		curScreen ++ ;
		Log.i(TAG, "----startMove---- curScreen " + curScreen);
		
		Log.i(TAG, "----width  " + getWidth());
		//����Scroller����ƻ�������
		mScroller.startScroll((curScreen-1) *getWidth(), 0, 
				getWidth(), 0,3000);
		//������ֱ�ӵ�Ŀ���
		//scrollTo(curScreen * getWidth(), 0);
		//��ʵ�ڵ����ť��ʱ�򣬾ͻش���View�������̣����������ǿ�ƻ�����View
		invalidate();
	}
	//ֹͣ����
	public void stopMove(){
		
		Log.v(TAG, "----stopMove ----");
		
		if(mScroller != null){
			//���������û���������ǾͰ����˽����İ�ť�������Ǿͽ����ö����������ϻ���ָ��λ��
			if(!mScroller.isFinished()){
				
				int scrollCurX= mScroller.getCurrX() ;
              	//�ж��Ƿ�ﵽ��һ�����м�λ�ã�����ﵽ�͵ִ���һ�������򱣳���ԭ��Ļ
				//int moveX = scrollCurX - mScroller.getStartX()   ;
                // Log.i(TAG, "----mScroller.is not finished ---- shouldNext" + shouldNext);
				//boolean shouldNext = moveX >= getWidth() / 2 ;
				int descScreen = ( scrollCurX +  getWidth() / 2) /  getWidth() ;
				
				Log.i(TAG, "----mScroller.is not finished ---- shouldNext" + descScreen);
				
				Log.i(TAG, "----mScroller.is not finished ---- scrollCurX " + scrollCurX);
				mScroller.abortAnimation();

			    //ֹͣ�˶������������ϻ���Ŀ��λ��
				scrollTo(descScreen * getWidth() , 0);
				mScroller.forceFinished(true);
				
				curScreen = descScreen ;
			}
	    }
			else
				Log.i(TAG, "----OK mScroller.is  finished ---- ");
	}
	// ֻ�е�ǰLAYOUT�е�ĳ��CHILD����SCROLL�����������Ż���ʹ�Լ���COMPUTESCROLL������
	@Override
	public void computeScroll() {	
		// TODO Auto-generated method stub
		Log.e(TAG, "computeScroll");
		// �������true����ʾ������û�н���
		// ��Ϊǰ��startScroll������ֻ����startScroll���ʱ �Ż�Ϊfalse
		if (mScroller.computeScrollOffset()) {
			Log.e(TAG, mScroller.getCurrX() + "======" + mScroller.getCurrY());
			// �����˶���Ч�� ÿ�ι���һ��
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			
			Log.e(TAG, "### getleft is " + getLeft() + " ### getRight is " + getRight());
			
		    //ˢ��View ����Ч�����������
			postInvalidate();
		}
		else
			Log.i(TAG, "have done the scoller -----");
	}
	/////���Ͽ�����ʾScroller���ʹ��
	//// --------------------------------
	/////--------------------------------
	
	private static final int TOUCH_STATE_REST = 0;
	private static final int TOUCH_STATE_SCROLLING = 1;
	private int mTouchState = TOUCH_STATE_REST;
	//-------------------------- 
	//�������¼� ~
	public static int  SNAP_VELOCITY = 600 ;
	private int mTouchSlop = 0 ;
	private float mLastionMotionX = 0 ;
	private float mLastMotionY = 0 ;
	//������������
	private VelocityTracker mVelocityTracker = null ;
	
	// ����о�ûʲô���� ����true����false ���ǻ�ִ��onTouchEvent�� ��Ϊ��view����onTouchEvent����false��
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		Log.e(TAG, "onInterceptTouchEvent-slop:" + mTouchSlop);

		final int action = ev.getAction();
		//��ʾ�Ѿ���ʼ�����ˣ�����Ҫ�߸�Action_MOVE������(��һ��ʱ���ܵ���)��
		if ((action == MotionEvent.ACTION_MOVE) && (mTouchState != TOUCH_STATE_REST)) {
			return true;
		}

		final float x = ev.getX();
		final float y = ev.getY();

		switch (action) {
		case MotionEvent.ACTION_MOVE:
			Log.e(TAG, "onInterceptTouchEvent move");
			final int xDiff = (int) Math.abs(mLastionMotionX - x);
			//��������С��������
			if (xDiff > mTouchSlop) {
				mTouchState = TOUCH_STATE_SCROLLING;
			}
			break;

		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "onInterceptTouchEvent down");
			mLastionMotionX = x;
			mLastMotionY = y;
			Log.e(TAG, mScroller.isFinished() + "");
			mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST : TOUCH_STATE_SCROLLING;

			break;

		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "onInterceptTouchEvent up or cancel");
			mTouchState = TOUCH_STATE_REST;
			break;
		}
		Log.e(TAG, mTouchState + "====" + TOUCH_STATE_REST);
		return mTouchState != TOUCH_STATE_REST;
	}
	public boolean onTouchEvent(MotionEvent event){
		
		Log.i(TAG, "--- onTouchEvent--> " );

		// TODO Auto-generated method stub
		Log.e(TAG, "onTouchEvent start");
		if (mVelocityTracker == null) {
			
			Log.e(TAG, "onTouchEvent start-------** VelocityTracker.obtain");
			
			mVelocityTracker = VelocityTracker.obtain();
		}
		
		mVelocityTracker.addMovement(event);
		
		super.onTouchEvent(event);
		
		//��ָλ�õص�
		float x = event.getX();
		float y = event.getY();
		
		
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			//�����Ļ�Ķ�����û��������Ͱ����ˣ����Ǿͽ����ö���
			if(mScroller != null){
				if(!mScroller.isFinished()){
					mScroller.abortAnimation();
				}
			}
			
			mLastionMotionX = x ;
			break ;
		case MotionEvent.ACTION_MOVE:
			int detaX = (int)(mLastionMotionX - x );
			scrollBy(detaX, 0);
			
			Log.e(TAG, "--- MotionEvent.ACTION_MOVE--> detaX is " + detaX );
			mLastionMotionX = x ;
			
			break ;
		case MotionEvent.ACTION_UP:
			
			final VelocityTracker velocityTracker = mVelocityTracker  ;
			velocityTracker.computeCurrentVelocity(1000);
			
			int velocityX = (int) velocityTracker.getXVelocity() ;
			
			Log.e(TAG , "---velocityX---" + velocityX);
			
			//�������ʴﵽ��һ����׼(�������һ�����������һ����Ļ) ���Ͻ�����������
			if (velocityX > SNAP_VELOCITY && curScreen > 0) {
				// Fling enough to move left
				Log.e(TAG, "snap left");
				snapToScreen(curScreen - 1);
			}
			//������������������һ����Ļ)
			else if(velocityX < -SNAP_VELOCITY && curScreen < (getChildCount()-1)){
				Log.e(TAG, "snap right");
				snapToScreen(curScreen + 1);
			}
			//����Ϊ�����ƶ��� ��ǿ���л���Ļ
			else{
				//�����ǻ����ƶ��ģ�������ж��Ǳ����ڱ���Ļ���ǵ���һ��Ļ
				snapToDestination();
			}
			
			if (mVelocityTracker != null) {
				mVelocityTracker.recycle();
				mVelocityTracker = null;
			}
			
			mTouchState = TOUCH_STATE_REST ;
			
		    break;
		case MotionEvent.ACTION_CANCEL:
			mTouchState = TOUCH_STATE_REST ;
			break;
		}
		
		return true ;
	}
	////�����ǻ����ƶ���
	private void snapToDestination(){
		//��ǰ��ƫ��λ��
		int scrollX = getScrollX() ;
		int scrollY = getScrollY() ;
		
		Log.e(TAG, "### onTouchEvent snapToDestination ### scrollX is " + scrollX);

		//�ж��Ƿ񳬹���һ�����м�λ�ã�����ﵽ�͵ִ���һ�������򱣳���ԭ��Ļ	
		//ֱ��ʹ�������ʽ�ж�����һ����Ļ ǰ������Լ�
		//�ж��Ƿ񳬹���һ�����м�λ�ã�����ﵽ�͵ִ���һ�������򱣳���ԭ��Ļ
		// ������һ���򵥹�ʽ��˼�ǣ����赱ǰ����ƫ��ֵ�� scrollCurX ����ÿ����Ļһ��Ŀ�ȣ�����ÿ����Ļ�Ŀ�Ⱦ���
		//  ����Ŀ��������λ���ˡ� ����ÿ����Ļ���Ϊ320dip, ���ǻ�����500dip��������Ȼ����Ӧ�õ���ڶ���	
		int destScreen = (getScrollX() + getWidth() / 2 ) / getWidth() ;
		
		 
	    Log.e(TAG, "### onTouchEvent  ACTION_UP### dx destScreen " + destScreen);
		
		snapToScreen(destScreen);
	}
    private void snapToScreen(int whichScreen){	
	    //�򵥵��Ƶ�Ŀ����Ļ�������ǵ�ǰ��������һ��Ļ
	    //ֱ����ת��ȥ����̫�Ѻ�
	    //scrollTo(mLastScreen * getWidth(), 0);
	    //Ϊ���Ѻ��ԣ�����������һ������Ч��
	    //��Ҫ�ٴλ����ľ��� ��������һ��Ļ�ļ�����������

	    curScreen = whichScreen ;
	    
	    if(curScreen > getChildCount() - 1)
	    	curScreen = getChildCount() - 1 ;
	    
	    int dx = curScreen*getWidth() - getScrollX() ;
	    
	    Log.e(TAG, "### onTouchEvent  ACTION_UP### dx is " + dx);
	    
	    mScroller.startScroll(getScrollX(), 0, dx, 0,Math.abs(dx) * 2);
	    
	    //��ʱ��Ҫ�ֶ�ˢ��View ����ûЧ��
	    invalidate();
	    
    }
		
    private void init() {
		
		mScroller = new Scroller(mContext);
		
		// ��ʼ��3�� LinearLayout�ؼ�
		LinearLayout oneLL = new LinearLayout(mContext);
		oneLL.setBackgroundColor(Color.RED);
        addView(oneLL);
		
		LinearLayout twoLL = new LinearLayout(mContext);
		twoLL.setBackgroundColor(Color.YELLOW);
		addView(twoLL);
		
		LinearLayout threeLL = new LinearLayout(mContext);
		threeLL.setBackgroundColor(Color.BLUE);
		addView(threeLL);
		
		//��ʼ��һ����С��������
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
	}

	// measure����
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec,heightMeasureSpec);
		Log.i(TAG, "--- start onMeasure --");

		// ���ø�ViewGroup�Ĵ�С
//		int width = MeasureSpec.getSize(widthMeasureSpec);
//		int height = MeasureSpec.getSize(heightMeasureSpec);
//		setMeasuredDimension(width, height);

//		int childCount = getChildCount();
//		Log.i(TAG, "--- onMeasure childCount is -->" + childCount);
//		for (int i = 0; i < childCount; i++) {
//			View child = getChildAt(i);
//			// ����ÿ������ͼ�Ĵ�С �� ��ȫ��
//			child.measure(getWidth(), MultiScreenActivity.scrrenHeight);
//		}
	}

	private int curPage = 0 ;
	
	// layout����
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		Log.i(TAG, "--- start onLayout --");
		int startLeft = 0; // ÿ������ͼ����ʼ��������
		int startTop = 10; // �������Ϊ10px �൱�� android��marginTop= "10px"
		int childCount = getChildCount();
		Log.i(TAG, "--- onLayout childCount is -->" + childCount );

		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			
			//��ʹ�ɼ��ģ��Ż�����Ļ��
			if(child.getVisibility() != View.GONE)
			    child.layout(startLeft, startTop, 
					   startLeft + getWidth(), 
					   startTop + MultiScreenActivity.scrrenHeight );
			    
			    startLeft = startLeft + getWidth() ; //У׼ÿ����View����ʼ����λ��
			    //��������ͼ������Ļ�еķֲ����� [0 , 320] / [320,640] / [640,960]
		}
	}
}
