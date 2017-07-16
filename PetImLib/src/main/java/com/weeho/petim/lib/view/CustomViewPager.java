package com.weeho.petim.lib.view;

import java.util.HashMap;
import java.util.LinkedHashMap;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * 
 * Describe:自定义支持动效的VIEWPAGER
 * 
 * Date:2015-9-24
 * 
 * Author:wangkui
 */
public class CustomViewPager extends ViewPager {
	private float mTrans;
	private float mScale;
	/**
	 * 最大的缩小比例
	 */
	private static final float SCALE_MAX = 0.5f;
	/**
	 * 保存position与对于的View
	 */
	private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<Integer, View>();
	/**
	 * 滑动时左边的元素
	 */
	private View mLeft;
	/**
	 * 滑动时右边的元素
	 */
	private View mRight;
	private static final boolean API_11;
	private float mRot;
	//控制是否可以滑动
	private boolean scrollble = true;
	
	public boolean isScrollble() {
		return scrollble;
	}

	public void setScrollble(boolean scrollble) {
		this.scrollble = scrollble;
	}
	 @SuppressLint("ClickableViewAccessibility") @Override  
	 public boolean onTouchEvent(MotionEvent ev) {  
	        if (!scrollble) {  
	            return false;  
	        }  
	        return super.onTouchEvent(ev);  
	    }  
	 
	 @Override
		public boolean onInterceptTouchEvent(MotionEvent arg0) {
			// TODO Auto-generated method stub
		 if (!scrollble) {  
	            return false;  
	        } 
			return super.onInterceptTouchEvent(arg0);
		}
	static {
		API_11 = Build.VERSION.SDK_INT >= 11;
	}

	public CustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

		// 滑动特别小的距离时，
		float effectOffset = isSmall(positionOffset) ? 0 : positionOffset;

		// 获取左边的View
		mLeft = findViewFromObject(position);
		// 获取右边的View
		mRight = findViewFromObject(position + 1);

		// 添加切换动画效果
//		 animateStack(mLeft, mRight, effectOffset, positionOffsetPixels);
//		 animateStackStack(mLeft, mRight, effectOffset, positionOffsetPixels);
//		animateAccordion(mLeft, mRight, effectOffset);
		super.onPageScrolled(position, positionOffset, positionOffsetPixels);
	}

	public void setObjectForPosition(View view, int position) {
		mChildrenViews.put(position, view);
	}

	
	
	/**
	 * 通过过位置获得对应的View
	 * 
	 * @param position
	 * @return
	 */
	public View findViewFromObject(int position) {
		return mChildrenViews.get(position);
	}

	public boolean isSmall(float positionOffset) {
		return Math.abs(positionOffset) < 0.0001;
	}

	public  void animateStack(View left, View right, float effectOffset,
			int positionOffsetPixels) {
		if (right != null) {
			/**
			 * 缩小比例 如果手指从右到左的滑动（切换到后一个）：0.0~1.0，即从一半到最大
			 * 如果手指从左到右的滑动（切换到前一个）：1.0~0，即从最大到一半
			 */
			mScale = (1 - SCALE_MAX) * effectOffset + SCALE_MAX;
			/**
			 * x偏移量： 如果手指从右到左的滑动（切换到后一个）：0-720 如果手指从左到右的滑动（切换到前一个）：720-0
			 */
			mTrans = -getWidth() - getPageMargin() + positionOffsetPixels;
			ViewHelper.setScaleX(right, mScale);
			ViewHelper.setScaleY(right, mScale);
			ViewHelper.setTranslationX(right, mTrans);
		}
		if (left != null) {
			left.bringToFront();
		}
	}

	public  void animateStackStack(View left, View right,
			float positionOffset, int positionOffsetPixels) {
		if (right != null) {
			manageLayer(right, true);
			mScale = (1 - SCALE_MAX) * positionOffset + SCALE_MAX;
			mTrans = -getWidth() - getPageMargin() + positionOffsetPixels;
			ViewHelper.setScaleX(right, mScale);
			ViewHelper.setScaleY(right, mScale);
			ViewHelper.setTranslationX(right, mTrans);
		}
		if (left != null) {
			left.bringToFront();
		}
	}

	private void manageLayer(final View v, boolean enableHardware) {
		if (!API_11)
			return;
		int layerType = enableHardware ? View.LAYER_TYPE_HARDWARE
				: View.LAYER_TYPE_NONE;
		if (layerType != v.getLayerType())
			v.setLayerType(layerType, null);
		 ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotationY", 0); 
		  animator.addListener(new AnimatorListenerAdapter() { 
		      @Override 
		      public void onAnimationEnd(Animator animation) { 
		         v.setLayerType(View.LAYER_TYPE_NONE, null); 
		     } 
		  }); 
		  animator.start();
	}

	public  void animateAccordion(View left, View right, float positionOffset) {
		if (left != null) {
			manageLayer(left, true);
			ViewHelper.setPivotX(left, left.getMeasuredWidth());
			ViewHelper.setPivotY(left, 0);
			ViewHelper.setScaleX(left, 1 - positionOffset);
		}
		if (right != null) {
			manageLayer(right, true);
			ViewHelper.setPivotX(right, 0);
			ViewHelper.setPivotY(right, 0);
			ViewHelper.setScaleX(right, positionOffset);
		}
	}

}
