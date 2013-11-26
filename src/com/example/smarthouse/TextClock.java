package com.example.smarthouse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class TextClock implements Runnable {

	private Thread runner;
    private final Runnable updater = new Runnable(){
        public void run(){
            updateClockValues();
        };
     };
    final Handler mHandler = new Handler();
    
	private Date _currentTime;
	private TextView _timeTextView;
	private TextView _dateTextView;
	
	SimpleDateFormat _timeDateFormat;
	SimpleDateFormat _dateDateFormat;
	
	TextClock(Activity activity){
		_timeDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
		_dateDateFormat = new SimpleDateFormat("EEEE, F 'of' MMMM yyyy", Locale.getDefault());
		
		_currentTime = Calendar.getInstance().getTime();
		_timeTextView = (TextView) activity.findViewById(R.id.time);
		_dateTextView = (TextView) activity.findViewById(R.id.date);
		
		if (runner == null){ 
           runner = new Thread(this);
           runner.start();
        }
	}
	
	private void updateClockValues(){
		_currentTime = Calendar.getInstance().getTime();
		_timeTextView.setText(_timeDateFormat.format(_currentTime.getTime()));
		_dateTextView.setText(_dateDateFormat.format(_currentTime.getTime()));
	}
	
	@Override
	public void run() {
		while (runner != null) {
		      try {
		    	  Thread.sleep(1000);
		    	  Log.i("Tick", "Tock");
		      } catch (InterruptedException e) { };
		      mHandler.post(updater);
		 }
	}

}