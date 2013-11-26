package com.example.smarthouse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ScreenSaver extends Activity {

	TextClock _textClock;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_textClock = new TextClock(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void test(){}

}
