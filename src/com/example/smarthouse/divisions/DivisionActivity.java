package com.example.smarthouse.divisions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.smarthouse.Breadcumbs;
import com.example.smarthouse.DataVariables;
import com.example.smarthouse.DeviceActivity;
import com.example.smarthouse.LightActivity;
import com.example.smarthouse.R;
import com.example.smarthouse.history.HistoryActivity;
import com.example.smarthouse.mainactivity.TextClock;

public class DivisionActivity extends Activity {

	private DataVariables _dataVariables;
	private TableLayout _tableLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		_dataVariables = (DataVariables)getApplication();
		if(!_dataVariables.isWindowInited())
			_dataVariables.initWindowSize(this);
		new Breadcumbs(this);
		new TextClock(this);
		_tableLayout = (TableLayout) findViewById(R.id.ButtonTable);
		
		_dataVariables = (DataVariables)getApplication();
		
		TableRow row = null;
		row = new TableRow(this);
		_tableLayout.addView(row);
		((Button)findViewById(R.id.historyButton)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				_dataVariables.historyFromDivision();
				startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
            }
        } );;
		Button button;
		
		button = new Button(this);
		button.setId(0);
        button.setText("Iluminacao");
		button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.lightbulb, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), LightActivity.class);
				startActivity(i);
            }
        });
        row.addView(button,((int)(_dataVariables.WIDTH*0.40)),((int)(_dataVariables.HEIGHT*0.7*0.8)));
        
        button = new Button(this);
        button.setId(1);
        button.setText("Aparelhos");
		button.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.devices, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), DeviceActivity.class);
				startActivity(i);
            }
        });
        row.addView(button,((int)(_dataVariables.WIDTH*0.40)),((int)(_dataVariables.HEIGHT*0.7*0.8)));
        
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.division, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
