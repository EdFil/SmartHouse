package com.example.smarthouse;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.smarthouse.mainactivity.TextClock;

public class LightActivity extends Activity {

	private TextClock _textClock;
	private ToggleButton _toggleButton;
	private TextView _light;
	private Division _division;
	private DataVariables _dataVariables;
	private TableRow _tablerow;
	private int lid = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light);
		_dataVariables = (DataVariables)getApplication();
		if(!_dataVariables.isWindowInited())
			_dataVariables.initWindowSize(this);
		_division = _dataVariables._currentDivision;
		TextView _menuName = (TextView)findViewById(R.id.menuName);
		_menuName.setText(_division.getName());
		_menuName.setTextSize(((int)(_dataVariables.WIDTH*0.05)));
		_textClock = new TextClock(this);

		_tablerow = (TableRow ) findViewById(R.id.tableRow1);
		
		for (int i = 0 ; i < _dataVariables._currentDivision.getLights().size(); i++) {
			_toggleButton = new ToggleButton(this);
			_toggleButton.setChecked(false);
			_toggleButton.setId(i);
			_toggleButton.setText(_dataVariables._currentDivision.getLights().get(i).getName());
			_toggleButton.setTextOn(_dataVariables._currentDivision.getLights().get(i).getName());
			_toggleButton.setTextOff(_dataVariables._currentDivision.getLights().get(i).getName());
			
			if(i == 0) {
				lid = R.id.l1;
				_light = (TextView) findViewById(R.id.l1);
				_light.setOnClickListener(new View.OnClickListener() {
					ToggleButton b = _toggleButton;
					int d = lid;
		            public void onClick(View v) {
		        		_light = (TextView) findViewById(d);
		            	if(!b.isChecked()) 
		            	{
		            		_light.setBackgroundResource(R.drawable.lighton);
		            		b.setChecked(true);
		            	}
		            	else
		            	{
		            		_light.setBackgroundResource(R.drawable.lightoff);
		            		b.setChecked(false);
		            	}
		            }
		        });
				_light.setVisibility(View.VISIBLE);
			}
			else if(i == 1) {
				lid = R.id.l2;
				_light = (TextView) findViewById(R.id.l2);
				_light.setOnClickListener(new View.OnClickListener() {
					ToggleButton b = _toggleButton;
					int d = lid;
		            public void onClick(View v) {
		        		_light = (TextView) findViewById(d);
		            	if(!b.isChecked())
		            	{
		            		_light.setBackgroundResource(R.drawable.lighton);
		            		b.setChecked(true);
		            	}
		            	else
		            	{
		            		_light.setBackgroundResource(R.drawable.lightoff);
		            		b.setChecked(false);
		            	}
		            }
		        });
				_light.setVisibility(View.VISIBLE);
			}
			else if (i == 2) {
				lid = R.id.l3;
				_light = (TextView) findViewById(R.id.l3);
				_light.setOnClickListener(new View.OnClickListener() {
					ToggleButton b = _toggleButton;
					int d = lid;
		            public void onClick(View v) {
		        		_light = (TextView) findViewById(d);
		            	if(!b.isChecked())
		            	{
		            		_light.setBackgroundResource(R.drawable.lighton);
		            		b.setChecked(true);
		            	}
		            	else
		            	{
		            		_light.setBackgroundResource(R.drawable.lightoff);
		            		b.setChecked(false);
		            	}
		            }
		        });
				_light.setVisibility(View.VISIBLE);
			}
			else if (i == 3) {
				lid = R.id.l4;
				_light = (TextView) findViewById(R.id.l4);
				_light.setOnClickListener(new View.OnClickListener() {
					ToggleButton b = _toggleButton;
					int d = lid;
		            public void onClick(View v) {
		        		_light = (TextView) findViewById(d);
		            	if(!b.isChecked())
		            	{
		            		_light.setBackgroundResource(R.drawable.lighton);
		            		b.setChecked(true);
		            	}
		            	else
		            	{
		            		_light.setBackgroundResource(R.drawable.lightoff);
		            		b.setChecked(false);
		            	}
		            }
		        });
				_light.setVisibility(View.VISIBLE);
			}
			_toggleButton.setOnClickListener(new View.OnClickListener() {
				ToggleButton b = _toggleButton;
				int d = lid;
	            public void onClick(View v) {
	        		_light = (TextView) findViewById(d);
	            	if(!b.isChecked())
	            		_light.setBackgroundResource(R.drawable.lightoff);
	            	
	            	else
	            		_light.setBackgroundResource(R.drawable.lighton);
	            }
	        });
			_tablerow.addView(_toggleButton,((int)(_dataVariables.WIDTH*0.25)), LayoutParams.MATCH_PARENT);
			if(i == 1)
				_tablerow = (TableRow ) findViewById(R.id.tableRow2);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.light, menu);
		return true;
	}
}
