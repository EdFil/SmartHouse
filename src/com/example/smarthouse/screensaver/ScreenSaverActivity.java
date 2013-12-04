package com.example.smarthouse.screensaver;


import com.example.smarthouse.DataVariables;
import com.example.smarthouse.R;
import com.example.smarthouse.mainactivity.MainScreenActivity;
import com.example.smarthouse.mainactivity.TextClock;
import com.example.smarthouse.popupsalerts.notfDialog;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class ScreenSaverActivity extends FragmentActivity {

	private TextClock _textClock;
	private DataVariables _instance;
	private Handler _handler = new Handler();
	private Runnable _updateTimeTask = new Runnable() {
		public void run() {
			updateConsumo();
			_handler.postDelayed(this, 1000);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_screensaver);
		_instance = (DataVariables)getApplication();
		//tance.setConsumo(20);
		updateConsumo();
		_handler.removeCallbacks(_updateTimeTask);
		_handler.postDelayed(_updateTimeTask, 1000); 
		initSeekBar();
		_textClock = new TextClock(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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

	private void updateConsumo() {
		TextView textViewToChange = (TextView) findViewById(R.id.Consumo);
		//textViewToChange.setText(""+_instance.getConsumo()+"Kw/h");
	}
	
	public void onNotfClick(View v) {
        notfDialog dialog = new notfDialog();
        dialog.show(getSupportFragmentManager(), "notfDialog");
	}
}
