package com.example.smarthouse;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.smarthouse.mainactivity.TextClock;

public class LightActivity extends Activity {

	private TextClock _textClock;
	private ToggleButton _bancada;
	private ToggleButton _exaustor;
	private ToggleButton _expositor;
	private ToggleButton _tecto;
	private TextView _light;
	private Division _division;
	private DataVariables _dataVariables;
	private TableRow _tablerow;

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
		
		_expositor = new ToggleButton(this);
		_expositor.setId(0);
		_expositor.setText("Expositor");
		_expositor.setTextOn("Expositor");
		_expositor.setTextOff("Expositor");
		_expositor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		_light = (TextView) findViewById(R.id.expositor);
            	if(!_expositor.isChecked())
            		_light.setBackgroundResource(R.drawable.lightoff);
            	
            	else
            		_light.setBackgroundResource(R.drawable.lighton);
            }
        });
		_tablerow.addView(_expositor,((int)(_dataVariables.WIDTH*0.25)), LayoutParams.MATCH_PARENT);
		
        _exaustor = new ToggleButton(this);
        _exaustor.setId(0);
        _exaustor.setText("Exaustor");
        _exaustor.setTextOn("Exaustor");
        _exaustor.setTextOff("Exaustor");
        _exaustor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		_light = (TextView) findViewById(R.id.exaustor);
            	if(!_exaustor.isChecked())
            		_light.setBackgroundResource(R.drawable.lightoff);
            	
            	else
            		_light.setBackgroundResource(R.drawable.lighton);
            }
        });
        _tablerow.addView(_exaustor,((int)(_dataVariables.WIDTH*0.25)), LayoutParams.MATCH_PARENT);
        
		_tablerow = (TableRow ) findViewById(R.id.tableRow2);

        _bancada = new ToggleButton(this);
        _bancada.setId(0);
        _bancada.setText("Bancada");
        _bancada.setTextOn("Bancada");
        _bancada.setTextOff("Bancada");
        _bancada.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		_light = (TextView) findViewById(R.id.bancada);
            	if(!_bancada.isChecked())
            		_light.setBackgroundResource(R.drawable.lightoff);
            	
            	else
            		_light.setBackgroundResource(R.drawable.lighton);
            }
        });
        _tablerow.addView(_bancada,((int)(_dataVariables.WIDTH*0.25)), LayoutParams.MATCH_PARENT);
        
        _tecto = new ToggleButton(this);
        _tecto.setId(0);
        _tecto.setText("Tecto");
        _tecto.setTextOn("Tecto");
        _tecto.setTextOff("Tecto");
        _tecto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		_light = (TextView) findViewById(R.id.tecto);
            	if(!_tecto.isChecked())
            		_light.setBackgroundResource(R.drawable.lightoff);
            	
            	else
            		_light.setBackgroundResource(R.drawable.lighton);
            }
        });
		_tablerow.addView(_tecto,((int)(_dataVariables.WIDTH*0.25)), LayoutParams.MATCH_PARENT);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.light, menu);
		return true;
	}

}
