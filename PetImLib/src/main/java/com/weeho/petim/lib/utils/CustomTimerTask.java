package com.weeho.petim.lib.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * 
 * 
 * @describe:倒计时功能
 * 
 * @author:wangkui
 * 
 * @time:2014-11-4上午10:17:11
 * 
 */
public class CustomTimerTask {
	private TextView tvDay,tvHour;
//	private TextView tvDay,tvHour, tvMinute, tvSeconds;
	private long days,hours, minutes, seconds;
	// 计时器变化的间隔时间，毫秒
	private int intervalTime;
	// 需要倒计时的总时间，毫秒
	private long totalTaskTime;
	private CountDownTimer timer;

	private TimeEvent event;

	public interface TimeEvent {
		public void complete();
	}

	public CustomTimerTask(TextView day,TextView hour, TextView minute, TextView seconds,
			long totalTime, int interval) {
		super();
		// TODO Auto-generated constructor stub
		tvDay = day;
		tvHour = hour;
//		tvMinute = minute;
//		tvSeconds = seconds;
		intervalTime = interval;
		totalTaskTime = totalTime;
	}

	public void setTotalTime(long totalTime) {
		totalTaskTime = totalTime;

	}

	public void setEvent(TimeEvent event) {
		this.event = event;
	}

	public void startTimer() {
		timer = new CountDownTimer(totalTaskTime, intervalTime) {
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				tvDay.setText("00");
				tvHour.setText("00");
//				tvMinute.setText("00");
//				tvSeconds.setText("00");
				if (event != null) {
					event.complete();
				}
			}

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				days = millisUntilFinished / (60 * 60 * 1000 * 24);
				// 获取小时值
				hours = (millisUntilFinished-days*(60*60*1000*24)) / (60 * 60 * 1000);
				// 获取分值
				minutes = (millisUntilFinished - days*(60*60*1000*24)-hours * (60 * 60 * 1000))
						/ (60 * 1000);
				// 获取秒值
				seconds = (millisUntilFinished - days*(60*60*1000*24)-hours * (60 * 60 * 1000) - minutes
						* (60 * 1000)) / 1000;
				if (days < 10) {
					tvDay.setText("0" + days);
				} else
					tvDay.setText(days + "");
				if (hours < 10) {
					tvHour.setText("0" + hours);
				} else
					tvHour.setText(hours + "");
//				if (minutes < 10) {
//					tvMinute.setText("0" + minutes);
//				} else
//					tvMinute.setText(minutes + "");
//				if (seconds < 10) {
//					tvSeconds.setText("0" + seconds);
//				} else
//					tvSeconds.setText(seconds + "");
			}
		};
		timer.start();
	}

	public void stopTimer() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}
}
