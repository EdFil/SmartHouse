package com.example.smarthouse.mainactivity;

import java.util.ArrayList;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.smarthouse.DataVariables;
import com.example.smarthouse.Division;
import com.example.smarthouse.R;
import com.example.smarthouse.divisions.DivisionActivity;
import com.example.smarthouse.popupsalerts.notfDialog;

public class MainScreenActivity extends FragmentActivity{

	private final int NUM_ELEMS_ROW = 3;
	private DataVariables _dataVariables;
	private TableLayout _tableLayout;
	private int buttonCount = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		
		_tableLayout = (TableLayout) findViewById(R.id.ButtonTable);
		
		_dataVariables = (DataVariables)getApplication();
		
        _dataVariables._divisions = new ArrayList<Division>();
        _dataVariables._divisions.add(new Division("Cozinha"));
        _dataVariables._divisions.add(new Division("Sala"));
        _dataVariables._divisions.add(new Division("Quarto Edgar"));
        _dataVariables._divisions.add(new Division("Dispensa"));
        _dataVariables._divisions.add(new Division("Quarto Joao"));
        _dataVariables._divisions.add(new Division("Quarto Andre"));
		
		TableRow row = null;
		int i = NUM_ELEMS_ROW;
		
		do{
			if(i >=  NUM_ELEMS_ROW){
				row = new TableRow(this);
				_tableLayout.addView(row);
			}
			Button button = new Button(this);
			button.setText(_dataVariables._divisions.get(buttonCount).getName());
			button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
				   Intent i = new Intent(getApplicationContext(), DivisionActivity.class);
				   i.putExtra("Division", buttonCount);
				   startActivity(i);
	            }
	        } );
			row.addView(button,100,50);
			buttonCount++;
		}while(buttonCount < _dataVariables._divisions.size());
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
