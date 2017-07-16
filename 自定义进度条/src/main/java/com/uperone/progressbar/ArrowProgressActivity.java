package com.uperone.progressbar;

import com.uperone.arrowprogress.ArrowProgressBar;


public class ArrowProgressActivity extends BaseActivity {
	@Override
	public void setContentView() {
		setContentView( R.layout.activity_arrow_progress_layout );
	}

	@Override
	public void findViews() {
		mArrowProgressBar = ( ArrowProgressBar )findViewById( R.id.arrowProgressBarId );
	}

	@Override
	public void getData() {
		
	}

	@Override
	public void showConent() {
		new Thread( new Runnable( ) {
			@Override
			public void run() {
				while( mProgress < 100 ){
					try {
						mProgress++;
						updateProgress( mProgress );
						Thread.sleep( 100 );
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start( );
	}
	
	private void updateProgress( final int progress ){
		ArrowProgressActivity.this.runOnUiThread( new Runnable( ) {
			@Override
			public void run() {
				mArrowProgressBar.setProgress( progress );
			}
		});
	}
	
	private int mProgress = 0;
	private ArrowProgressBar mArrowProgressBar = null;
}
