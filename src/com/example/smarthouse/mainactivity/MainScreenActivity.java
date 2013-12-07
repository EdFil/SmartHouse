package com.example.smarthouse.mainactivity;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.smarthouse.DataVariables;
import com.example.smarthouse.Device;
import com.example.smarthouse.Division;
import com.example.smarthouse.R;
import com.example.smarthouse.divisions.DivisionActivity;
import com.example.smarthouse.popupsalerts.notfDialog;

public class MainScreenActivity extends FragmentActivity{

	private TextClock _textClock;
	private final int NUM_ELEMS_ROW = 3;
	private DataVariables _dataVariables;
	private TableLayout _tableLayout;
	private int buttonCount = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen); 
		
		_tableLayout = (TableLayout) findViewById(R.id.ButtonTable);
		_dataVariables = (DataVariables)getApplication();
		if(!_dataVariables.isWindowInited())
			_dataVariables.initWindowSize(this);
		initDivisions();
		_textClock = new TextClock(this);
		
		TableRow row = null;
		int i = NUM_ELEMS_ROW;
		
		while(buttonCount < _dataVariables._divisions.size()){
			if(i >=  NUM_ELEMS_ROW){
				row = new TableRow(this);
				_tableLayout.addView(row);
				i = 0;
			}
			Button button = new Button(this);
			button.setText(_dataVariables._divisions.get(buttonCount).getName());
			button.setId(buttonCount);
			button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
				   Intent intent = new Intent(getApplicationContext(), DivisionActivity.class);
				   intent.putExtra("Division", v.getId());
				   startActivity(intent);
	            }
	        } );
			row.addView(button,((int)(_dataVariables.WIDTH*0.2)),((int)(_dataVariables.HEIGHT*0.1)));
			buttonCount++; i++;
		}
	}
	
	private void initDivisions() {
		_dataVariables._divisions = new ArrayList<Division>();
		Division cozinha = new Division("Cozinha");
		cozinha.addDevice(new Device("Torradeira"));
		cozinha.addDevice(new Device("Frigorifico"));
		cozinha.addDevice(new Device("Fogão"));
		cozinha.addDevice(new Device("Fritadeira"));
		cozinha.addDevice(new Device("Esquentador"));
		cozinha.addDevice(new Device("Televisão"));
		cozinha.addDevice(new Device("Luzes"));
		Division sala = new Division("Sala");
		sala.addDevice(new Device("Televisão"));
		sala.addDevice(new Device("PS4"));
		sala.addDevice(new Device("Lareira Eletrica"));
		sala.addDevice(new Device("Luzes"));
		_dataVariables._divisions.add(cozinha);
        _dataVariables._divisions.add(sala);
        _dataVariables._divisions.add(new Division("Quarto Edgar"));
        _dataVariables._divisions.add(new Division("Dispensa"));
        _dataVariables._divisions.add(new Division("Quarto Joao"));
        _dataVariables._divisions.add(new Division("Quarto Andre"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onNotfClick(View v) {
        notfDialog dialog = new notfDialog();
        dialog.show(getSupportFragmentManager(), "notfDialog");
	}
}
