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
	private TextView _dateTextView;
	
	SimpleDateFormat _dateDateFormat;
	
	TextClock(Activity activity){
		_dateDateFormat = new SimpleDateFormat("EEEE, d 'of' MMMM yyyy", Locale.getDefault());
		
		_currentTime = Calendar.getInstance().getTime();
		_dateTextView = (TextView) activity.findViewById(R.id.date);
		
		if (runner == null){ 
           runner = new Thread(this);
           runner.start();
        }
	}
	
	private void updateClockValues(){
		_currentTime = Calendar.getInstance().getTime();
		_dateTextView.setText(_dateDateFormat.format(_currentTime.getTime()));
	}
	
	@Override
	public void run() {
		while (runner != null) {
		      mHandler.post(updater);
		      try {
		    	  Thread.sleep(1000);
		    	  Log.i("Tick", "Tock");
		      } catch (InterruptedException e) { };
		 }
	}

}
