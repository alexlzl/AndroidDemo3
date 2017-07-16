package com.weeho.petim.lib.view;

import com.weeho.petim.lib.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.widget.Adapter;
import android.widget.Gallery;
import android.widget.SpinnerAdapter;

@SuppressWarnings("deprecation")
public class CustomCoverFlow extends Gallery {

	public static final int ACTION_DISTANCE_AUTO = Integer.MAX_VALUE;

	public static final float SCALEDOWN_GRAVITY_TOP = 0.0f;

	public static final float SCALEDOWN_GRAVITY_CENTER = 0.5f;

	public static final float SCALEDOWN_GRAVITY_BOTTOM = 1.0f;

	private float reflectionRatio = 0.4f;

	private int reflectionGap = 20;

	private boolean reflectionEnabled = false;

	private float unselectedAlpha;

	private Camera transformationCamera;

	private int maxRotation = 75;

	private float unselectedScale;

	private float scaleDownGravity = SCALEDOWN_GRAVITY_CENTER;

	private int actionDistance;

	private float unselectedSaturation;

	public CustomCoverFlow(Context context) {
		super(context);
		this.initialize();
	}

	public CustomCoverFlow(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initialize();
		this.applyXmlAttributes(attrs);
	}

	public CustomCoverFlow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.initialize();
		this.applyXmlAttributes(attrs);
	}

	private void initialize() {
		this.transformationCamera = new Camera();
		this.setSpacing(0);
	}

	int totalCount = 1;

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	private void applyXmlAttributes(AttributeSet attrs) {
		TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.CustomCoverFlow);

		this.actionDistance = a.getInteger(
				R.styleable.CustomCoverFlow_actionDistance,
				ACTION_DISTANCE_AUTO);
		this.scaleDownGravity = a.getFloat(
				R.styleable.CustomCoverFlow_scaleDownGravity, 1.0f);
		this.maxRotation = a.getInteger(
				R.styleable.CustomCoverFlow_maxRotation, 45);
		this.unselectedAlpha = a.getFloat(
				R.styleable.CustomCoverFlow_unselectedAlpha, 0.3f);
		this.unselectedSaturation = a.getFloat(
				R.styleable.CustomCoverFlow_unselectedSaturation, 0.0f);
		this.unselectedScale = a.getFloat(
				R.styleable.CustomCoverFlow_unselectedScale, 0.75f);
	}

	public float getReflectionRatio() {
		return reflectionRatio;
	}

	public void setReflectionRatio(float reflectionRatio) {
		if (reflectionRatio <= 0 || reflectionRatio > 0.5f) {
			throw new IllegalArgumentException(
					"reflectionRatio may only be in the interval (0, 0.5]");
		}

		this.reflectionRatio = reflectionRatio;

		if (this.getAdapter() != null) {
		}
	}

	public int getReflectionGap() {
		return reflectionGap;
	}

	public void setReflectionGap(int reflectionGap) {
		this.reflectionGap = reflectionGap;

		if (this.getAdapter() != null) {
		}
	}

	public boolean isReflectionEnabled() {
		return reflectionEnabled;
	}

	public void setReflectionEnabled(boolean reflectionEnabled) {
		this.reflectionEnabled = reflectionEnabled;

		if (this.getAdapter() != null) {
		}
	}

	@Override
	public void setAdapter(SpinnerAdapter adapter) {
		if (!(adapter instanceof Adapter)) {
			throw new ClassCastException("setAdapter error");
		}
		super.setAdapter(adapter);
	}

	public int getMaxRotation() {
		return maxRotation;
	}

	public void setMaxRotation(int maxRotation) {
		this.maxRotation = maxRotation;
	}

	public float getUnselectedAlpha() {
		return this.unselectedAlpha;
	}

	public float getUnselectedScale() {
		return unselectedScale;
	}

	public void setUnselectedScale(float unselectedScale) {
		this.unselectedScale = unselectedScale;
	}

	public float getScaleDownGravity() {
		return scaleDownGravity;
	}

	public void setScaleDownGravity(float scaleDownGravity) {
		this.scaleDownGravity = scaleDownGravity;
	}

	public int getActionDistance() {
		return actionDistance;
	}

	public void setActionDistance(int actionDistance) {
		this.actionDistance = actionDistance;
	}

	@Override
	public void setUnselectedAlpha(float unselectedAlpha) {
		super.setUnselectedAlpha(unselectedAlpha);
		this.unselectedAlpha = unselectedAlpha;
	}

	public float getUnselectedSaturation() {
		return unselectedSaturation;
	}

	public void setUnselectedSaturation(float unselectedSaturation) {
		this.unselectedSaturation = unselectedSaturation;
	}

	@Override
	protected boolean getChildStaticTransformation(View child, Transformation t) {

		View item = child;

		if (android.os.Build.VERSION.SDK_INT >= 16) {
			item.invalidate();
		}

		final int coverFlowWidth = this.getWidth();
		final int coverFlowCenter = coverFlowWidth / 2;
		final int childWidth = item.getWidth();
		final int childHeight = item.getHeight();
		final int childCenter = item.getLeft() + childWidth / 2;

		final int actionDistance = (this.actionDistance == ACTION_DISTANCE_AUTO) ? (int) ((coverFlowWidth + childWidth) / 2.0f)
				: this.actionDistance;

		final float effectsAmount = Math.min(
				1.0f,
				Math.max(-1.0f, (1.0f / actionDistance)
						* (childCenter - coverFlowCenter)));

		t.clear();
		t.setTransformationType(Transformation.TYPE_BOTH);

		if (this.unselectedAlpha != 1) {
			final float alphaAmount = (this.unselectedAlpha - 1)
					* Math.abs(effectsAmount) + 1;
			t.setAlpha(alphaAmount);
		}

		if (this.unselectedSaturation != 1) {
			final float saturationAmount = (this.unselectedSaturation - 1)
					* Math.abs(effectsAmount) + 1;
		}

		final Matrix imageMatrix = t.getMatrix();

		if (this.maxRotation != 0) {
			final int rotationAngle = (int) (-effectsAmount * this.maxRotation);
			this.transformationCamera.save();
			this.transformationCamera.rotateY(rotationAngle);
			this.transformationCamera.getMatrix(imageMatrix);
			this.transformationCamera.restore();
		}

		if (this.unselectedScale != 1) {
			final float zoomAmount = (this.unselectedScale - 1)
					* Math.abs(effectsAmount) + 1;

			final float translateX = childWidth / 2.0f;
			final float translateY = childHeight * this.scaleDownGravity;
			imageMatrix.preTranslate(-translateX, -translateY);
			imageMatrix.postScale(zoomAmount, zoomAmount);
			imageMatrix.postTranslate(translateX, translateY);
		}

		return true;
	}

//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	public static class LayoutParams extends Gallery.LayoutParams {
		public LayoutParams(Context c, AttributeSet attrs) {
			super(c, attrs);
		}

		public LayoutParams(int w, int h) {
			super(w, h);
		}

		public LayoutParams(ViewGroup.LayoutParams source) {
			super(source);
		}
	}
//	  @Override
//	    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//	    	if (e1.getX() - e2.getX() < 0.0F){
//	    		onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT,null);
//	    	}else{
//	    		onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT,null);
//	    	}
//	        return true;
//	    }
}
