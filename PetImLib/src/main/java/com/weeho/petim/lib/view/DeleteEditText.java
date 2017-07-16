package com.weeho.petim.lib.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.weeho.petim.lib.R;


public class DeleteEditText extends EditText implements OnFocusChangeListener,
        TextWatcher {
	// 删除按钮的引用
	private Drawable mClearDrawable;
	//是否显示×图片
	private boolean isChangeVisbile = true;
	public DeleteEditText(Context context) {
		this(context, null);
		init();
	}

	public DeleteEditText(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.editTextStyle);
		init();
	}

	public DeleteEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		// 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
		mClearDrawable = getCompoundDrawables()[2];
		if (mClearDrawable == null) {
			mClearDrawable = getResources().getDrawable(
					R.drawable.search_clear);
		}
		mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth()+10,
				mClearDrawable.getIntrinsicHeight()+10);
		setClearIconVisible(false);
		setOnFocusChangeListener(this);
		addTextChangedListener(this);
	}

	
	public void isVisibleDrawble(boolean visible){
		this.isChangeVisbile = visible;
	}
	/**
	 * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 -
	 * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标 ，竖直方向没有考虑
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (getCompoundDrawables()[2] != null) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				boolean touchable = event.getX() > (getWidth()
						- getPaddingRight() - mClearDrawable
							.getIntrinsicWidth())
						&& (event.getX() < ((getWidth() - getPaddingRight())));
				if (touchable) {
					this.setText("");
					// PositionActivity.tvCancle.setText("取消");
					// PositionSearchFragment.tagIsSearch = false;
					// LogUtil.d(TAG, "==" +
					// PositionSearchFragment.tagIsSearch);
					// if (PositionActivity.fragmentPosition == 1) {
					// Message message = Message.obtain();
					// message.what = 1;
					// Bundle data = new Bundle();
					// data.putString("DATA", "");
					// message.setData(data);
					// PositionSearchFragment.searchHandler
					// .sendMessage(message);
					// }

				}
			}
		}

		return super.onTouchEvent(event);
	}

	/**
	 * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
	 */
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus && isChangeVisbile) {
			setClearIconVisible(getText().length() > 0);
		} else {
			setClearIconVisible(false);
		}
	}

	/**
	 * 
	 * Describe:设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:liuzhouliang
	 */
	public void setClearIconVisible(boolean visible) {
		Drawable right = visible ? mClearDrawable : null;
		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
	}

	/**
	 * 当输入框里面内容发生变化的时候回调的方法
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int count, int after) {
		if(isChangeVisbile)
			setClearIconVisible(s.length() > 0);
		else
			setClearIconVisible(false);
		// if (PositionActivity.fragmentPosition == 1) {
		//
		// if (!s.toString().equals("")) {
		// Message message = Message.obtain();
		// message.what = 0;
		// Bundle data = new Bundle();
		// data.putString("DATA", s.toString());
		// message.setData(data);
		// PositionSearchFragment.searchHandler.sendMessage(message);
		// } else {
		// Message message = Message.obtain();
		// message.what = 0;
		// Bundle data = new Bundle();
		// data.putString("DATA", "");
		// message.setData(data);
		// PositionSearchFragment.searchHandler.sendMessage(message);
		// }
		//
		// }

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	/**
	 * 
	 * Describe:设置晃动动画
	 * 
	 * Date:2015-9-22
	 * 
	 * Author:liuzhouliang
	 */
	public void setShakeAnimation() {
		this.setAnimation(shakeAnimation(5));
	}

	public static Animation shakeAnimation(int counts) {
		Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
		translateAnimation.setInterpolator(new CycleInterpolator(counts));
		translateAnimation.setDuration(1000);
		return translateAnimation;
	}

}
