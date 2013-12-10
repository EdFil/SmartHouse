package com.example.smarthouse.screensaver;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.example.smarthouse.DataVariables;
import com.example.smarthouse.Division;
import com.example.smarthouse.InfoArea;
import com.example.smarthouse.InstantConsumption;
import com.example.smarthouse.R;
import com.example.smarthouse.TimedDevice;
import com.example.smarthouse.User;
import com.example.smarthouse.mainactivity.MainScreenActivity;
import com.example.smarthouse.mainactivity.TextClock;

public class ScreenSaverActivity extends FragmentActivity {

	private ArrayList<InfoArea> _infoAreas;
	private InfoArea _currentInfo;
	private FrameLayout _infoArea;
	private DataVariables _dataVariables;
	private Handler _handler = new Handler();
	private Runnable _updateTimeTask = new Runnable() {
		public void run() {
			_handler.postDelayed(this, 1000);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_screensaver);
		_dataVariables = (DataVariables)getApplication();
		if(!_dataVariables.isWindowInited())
			_dataVariables.initWindowSize(this);
		_handler.removeCallbacks(_updateTimeTask);
		_handler.postDelayed(_updateTimeTask, 1000); 
		initSeekBar();
		_infoArea = (FrameLayout)findViewById(R.id.InfoArea);
		new TextClock(this);
		initUsers();
		initDivisions();
		_currentInfo = new InstantConsumption(this);
		_currentInfo.setFrameLayout(_infoArea);
	}
	
	private void initUsers(){
		_dataVariables._users = new ArrayList<User>();
		_dataVariables._users.add(new User("Edgar"));
		_dataVariables._users.add(new User("Andre"));
		_dataVariables._users.add(new User("Joao"));
		_dataVariables._currentUser = _dataVariables._users.get(0);
	}
	
	private void initDivisions() {
		_dataVariables._divisions = new ArrayList<Division>();
		Division cozinha = new Division("Cozinha");
		cozinha.addDevice(new TimedDevice("Torradeira"));
		cozinha.addDevice(new TimedDevice("Frigorifico"));
		cozinha.addDevice(new TimedDevice("Fogão"));
		cozinha.addDevice(new TimedDevice("Fritadeira"));
		cozinha.addDevice(new TimedDevice("Esquentador"));
		cozinha.addDevice(new TimedDevice("Televisão"));
		cozinha.addDevice(new TimedDevice("Luzes"));
		Division sala = new Division("Sala");
		sala.addDevice(new TimedDevice("Televisão"));
		sala.addDevice(new TimedDevice("PS4"));
		sala.addDevice(new TimedDevice("Televisão"));
		sala.addDevice(new TimedDevice("PS4"));
		sala.addDevice(new TimedDevice("Lareira Eletrica"));
		sala.addDevice(new TimedDevice("Luzes"));
		_dataVariables._divisions.add(cozinha);
        _dataVariables._divisions.add(sala);
        _dataVariables._divisions.add(new Division("Quarto Edgar"));
        _dataVariables._divisions.add(new Division("Dispensa"));
        _dataVariables._divisions.add(new Division("Quarto Joao"));
        _dataVariables._divisions.add(new Division("Quarto Andre"));
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
