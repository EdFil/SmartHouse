package com.example.smarthouse.screensaver;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.example.smarthouse.DataVariables;
import com.example.smarthouse.InfoArea;
import com.example.smarthouse.InstantConsumption;
import com.example.smarthouse.NotificationArea;
import com.example.smarthouse.R;
import com.example.smarthouse.mainactivity.MainScreenActivity;
import com.example.smarthouse.mainactivity.TextClock;

public class ScreenSaverActivity extends FragmentActivity {

	private ArrayList<InfoArea> _infoAreas;
	private FrameLayout _infoArea;
	private DataVariables _dataVariables;
	private Handler _handler = new Handler();
	private Runnable _updateTimeTask = new Runnable() {
		int index = 0;
		public void run() {
			index++;
			if(index >= _infoAreas.size())
				index = 0;
			_infoAreas.get(index).setFrameLayout(_infoArea);
			_handler.postDelayed(this, 10000);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_screensaver);
		_dataVariables = (DataVariables)getApplication();
		if(!_dataVariables.isWindowInited())
			_dataVariables.initWindowSize(this);
		_dataVariables._currentAct = this;
		_handler.removeCallbacks(_updateTimeTask);
		_handler.postDelayed(_updateTimeTask, 0); 
		initSeekBar();
		_infoArea = (FrameLayout)findViewById(R.id.InfoArea);
		new TextClock(this);
		_infoAreas = new ArrayList<InfoArea>();
		_infoAreas.add(new InstantConsumption(this));
		_infoAreas.add(new NotificationArea(this));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.s
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void initSeekBar() {
		
	   SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar); 
	  // final TextView seekBarValue = (TextView)findViewById(R.id.UnlockProgress); 
	   //seekBarValue.setText(0+"%");
	   seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 
		
		   @Override 
		   public void onProgressChanged(SeekBar seekBar, int progress, 
		     boolean fromUser) { 
			   if(progress == 100) {
				   Intent i = new Intent(getApplicationContext(), MainScreenActivity.class);
				   startActivity(i);
				   Log.i("Progress",0+"");
				   
			   }
		    //seekBarValue.setText(String.valueOf(progress)+"%"); 
		   } 
		
		   @Override 
		   public void onStartTrackingTouch(SeekBar seekBar) { 
		    // TODO Auto-generated method stub 
		   } 
		
		   @Override 
		   public void onStopTrackingTouch(SeekBar seekBar) { 
			   seekBar.setProgress(0);
		   } 
	   });
	}
}
