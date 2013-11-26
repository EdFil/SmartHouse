package com.example.smarthouse.divisions;

import com.example.smarthouse.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class KitchenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kitchen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kitchen, menu);
		return true;
	}

}
