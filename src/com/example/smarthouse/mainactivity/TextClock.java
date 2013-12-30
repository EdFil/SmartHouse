package com.example.smarthouse.mainactivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.TypedValue;
import android.widget.TextView;

import com.example.smarthouse.DataVariables;
import com.example.smarthouse.R;

public class TextClock implements Runnable {

	private DataVariables _dataVariables;
	private Thread runner;
    private final Runnable updater = new Runnable(){
    	boolean bool = false;
        public void run(){
            updateClockValues(bool);
            bool = !bool;
        };
     };
    final Handler mHandler = new Handler();
    
	private Date _currentTime;
	private TextView _dateTextView1;
	private TextView _dateTextView2;
	private TextView _dateTextView3;
	
	SimpleDateFormat _dateDateFormat1;
	SimpleDateFormat _dateDateFormat11;
	SimpleDateFormat _dateDateFormat2;
	SimpleDateFormat _dateDateFormat3;
	
	public TextClock(Activity activity){
		_dataVariables = (DataVariables)activity.getApplication();
		_dateDateFormat1 = new SimpleDateFormat("HH:mm", Locale.getDefault());
		_dateDateFormat11 = new SimpleDateFormat("HH mm", Locale.getDefault());
		_dateDateFormat2 = new SimpleDateFormat("d 'of' MMMM yyyy", Locale.getDefault());
		_dateDateFormat3 = new SimpleDateFormat("EEEE", Locale.getDefault());
		
		_currentTime = Calendar.getInstance().getTime();
		_dateTextView1 = (TextView) activity.findViewById(R.id.hour);
		_dateTextView2 = (TextView) activity.findViewById(R.id.date1);
		_dateTextView3 = (TextView) activity.findViewById(R.id.date2);
		
		if(_dateTextView1 != null)
			_dateTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, _dataVariables.HEIGHT*0.15f);
		if(_dateTextView2 != null)
			_dateTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, _dataVariables.HEIGHT*0.07f);
		if(_dateTextView3 != null){
			_dateTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, _dataVariables.HEIGHT*0.07f);
			_dateTextView3.setTypeface(null, Typeface.BOLD);
		}
		
		if (runner == null){ 
           runner = new Thread(this);
           runner.start();
        }
	}
	
	private void updateClockValues(boolean bool){
		_currentTime = Calendar.getInstance().getTime();
		if(_dateTextView1 != null){
			if(bool)
				_dateTextView1.setText(_dateDateFormat1.format(_currentTime.getTime()));
			else
				_dateTextView1.setText(_dateDateFormat11.format(_currentTime.getTime()));
		}
		if(_dateTextView2 != null)
			_dateTextView2.setText(_dateDateFormat2.format(_currentTime.getTime()));
		if(_dateTextView3 != null)
			_dateTextView3.setText(_dateDateFormat3.format(_currentTime.getTime()));
	}
	
	@Override
	public void run() {
		while (runner != null) {
		      mHandler.post(updater);
		      try {
		    	  Thread.sleep(1000);
		      } catch (InterruptedException e) { };
		 }
	}

}
