package com.example.smarthouse.divisions;

import com.example.smarthouse.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class DivisionActivity extends Activity {

	private String division;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_division);
		// Show the Up button in the action bar.
		Intent i = getIntent();
		division = (i.getExtras()).getString("Division");
		Button button;
		if(division.equals("Cozinha")) {
			
	        button = (Button) findViewById(R.id.button1);
	        button.setText("Entradas/saidas");
	        button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
				   Intent i = new Intent(getApplicationContext(), KitchenActivity.class);
				   startActivity(i);
	            }
	        });
	        button = (Button) findViewById(R.id.button2);
	        button.setText("Iluminação");
	        button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
				   Intent i = new Intent(getApplicationContext(), KitchenActivity.class);
				   startActivity(i);
	            }
	        });
	        button = (Button) findViewById(R.id.button3);
	        button.setText("Electrodomesticos");
	        button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
				   Intent i = new Intent(getApplicationContext(), KitchenActivity.class);
				   startActivity(i);
	            }
	        });
		} else if (division.equals("Sala")) {
	        button = (Button) findViewById(R.id.button1);
	        button.setText("Filmes");
	        button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
				   Intent i = new Intent(getApplicationContext(), SalaActivity.class);
				   startActivity(i);
	            }
	        });
	        button = (Button) findViewById(R.id.button2);
	        button.setText("Albuns");
	        button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
				   Intent i = new Intent(getApplicationContext(), SalaActivity.class);
				   startActivity(i);
	            }
	        });
	        button = (Button) findViewById(R.id.button3);
	        button.setText("Iluminação");
	        button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
				   Intent i = new Intent(getApplicationContext(), SalaActivity.class);
				   startActivity(i);
	            }
	        });
		}
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.division, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}