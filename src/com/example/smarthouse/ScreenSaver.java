package com.example.smarthouse;

import com.example.smarthouse.notfDialog;
import com.example.smarthouse.DataVariables;
import com.example.smarthouse.MainScreen;
import com.example.smarthouse.R;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class ScreenSaver extends FragmentActivity {

	private Handler handler = new Handler();
	private Runnable updateTimeTask = new Runnable() {
		public void run() {
			updateConsumo();
			handler.postDelayed(this, 1000);
		}
	};
	private DataVariables instance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_screensaver);
		instance = (DataVariables)getApplication();
		instance.setConsumo(20);
		updateConsumo();
		handler.removeCallbacks(updateTimeTask);
		handler.postDelayed(updateTimeTask, 1000); 
		initSeekBar();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void initSeekBar() {
		
	   SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar); 
	   final TextView seekBarValue = (TextView)findViewById(R.id.UnlockProgress); 
	   seekBarValue.setText(0+"%");
	   seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 
		
		   @Override 
		   public void onProgressChanged(SeekBar seekBar, int progress, 
		     boolean fromUser) { 
			   if(progress == 100) {
				   Intent i = new Intent(getApplicationContext(), MainScreen.class);
				   startActivity(i);
			   }
			
		    seekBarValue.setText(String.valueOf(progress)+"%"); 
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
		textViewToChange.setText(""+instance.getConsumo()+"Kw/h");
	}
	
	public void onNotfClick(View v) {
        notfDialog dialog = new notfDialog();
        dialog.show(getSupportFragmentManager(), "notfDialog");
	}
}
