package com.example.smarthouse.divisions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.smarthouse.DataVariables;
import com.example.smarthouse.Device;
import com.example.smarthouse.DeviceActivity;
import com.example.smarthouse.Division;
import com.example.smarthouse.DivisionHistory;
import com.example.smarthouse.LightActivity;
import com.example.smarthouse.R;
import com.example.smarthouse.mainactivity.MainScreenActivity;
import com.example.smarthouse.mainactivity.TextClock;

public class DivisionActivity extends Activity {

	private static final int NUM_ELEMS_ROW = 3;
	
	private TextClock _textClock;
	private DataVariables _dataVariables;
	private Division _division;
	private TableLayout _tableLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		_dataVariables = (DataVariables)getApplication();
		if(!_dataVariables.isWindowInited())
			_dataVariables.initWindowSize(this);
		_division = _dataVariables._currentDivision;
		TextView _menuName = (TextView)findViewById(R.id.menuName);
		_menuName.setText(_division.getName());
		_menuName.setTextSize(((int)(_dataVariables.WIDTH*0.05)));
		
		_textClock = new TextClock(this);
		_tableLayout = (TableLayout) findViewById(R.id.ButtonTable);
		
		_dataVariables = (DataVariables)getApplication();
		
		TableRow row = null;
		int i = NUM_ELEMS_ROW;
		
		row = new TableRow(this);
		_tableLayout.addView(row);
		i = 0;
		
		Button button;
		
		button = new Button(this);
		button.setId(0);
        button.setText("Iluminacao");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	_dataVariables._currentDevice = _division.getDevices().get(v.getId());
				Intent i = new Intent(getApplicationContext(), LightActivity.class);
				startActivity(i);
            }
        });
        row.addView(button,((int)(_dataVariables.WIDTH*0.35)),((int)(_dataVariables.HEIGHT*0.7*0.8)));
        
        button = new Button(this);
        button.setId(1);
        button.setText("Aparelhos");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	_dataVariables._currentDevice = _division.getDevices().get(v.getId());
				Intent i = new Intent(getApplicationContext(), DeviceActivity.class);
				startActivity(i);
            }
        });
        row.addView(button,((int)(_dataVariables.WIDTH*0.35)),((int)(_dataVariables.HEIGHT*0.7*0.8)));
        
        button = new Button(this);
        button.setId(2);
        button.setText("Consumo");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	_dataVariables._currentDevice = _division.getDevices().get(v.getId());
				Intent i = new Intent(getApplicationContext(), DivisionHistory.class);
				startActivity(i);
            }
        });
        row.addView(button,((int)(_dataVariables.WIDTH*0.35)),((int)(_dataVariables.HEIGHT*0.7*0.8)));
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