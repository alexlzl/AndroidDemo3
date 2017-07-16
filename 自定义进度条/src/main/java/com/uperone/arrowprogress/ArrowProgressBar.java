package com.uperone.arrowprogress;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uperone.progressbar.R;

public class ArrowProgressBar extends RelativeLayout {
	public ArrowProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initArrowProgressBar( context );
	}

	public ArrowProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initArrowProgressBar( context );
	}

	public ArrowProgressBar(Context context) {
		super(context);
		initArrowProgressBar( context );
	}
	
	private void initArrowProgressBar( Context context ){
		LayoutInflater layoutInflater = LayoutInflater.from( context );
		View view = layoutInflater.inflate(R.layout.arrow_progress_bar_layout, null);
		
		mProgressBar = ( ProgressBar )view.findViewById( R.id.downloadProgressId );
		mProgressTxt = ( TextView )view.findViewById( R.id.progressTxtId );
		mArrowImg = ( ImageView )view.findViewById( R.id.arrowImgId );
		mArrowImg.setVisibility( ImageView.GONE );
		
		addView( view );
	}
	
	public void setProgress( int progress ){
		if( progress < PROGRESS_MAX ){
			LayoutParams arrowParams = ( LayoutParams )mArrowImg.getLayoutParams( );
			float leftPosition = ( ( mProgressBar.getWidth( )/PROGRESS_MAX ) * ( progress - 2 ) ) + mProgressBar.getLeft();
			arrowParams.leftMargin = ( int )Math.ceil( leftPosition );
			
			mArrowImg.setLayoutParams( arrowParams );
		}else{
			mArrowImg.setVisibility( ImageView.GONE );
		}
		
		mProgressBar.setProgress( progress );
		mProgressTxt.setText( progress + "%" );
	}
	
	private ProgressBar mProgressBar = null;
	private TextView mProgressTxt = null;
	private ImageView mArrowImg = null;
	private static final float PROGRESS_MAX = 100.0f;
}
