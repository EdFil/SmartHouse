package com.example.smarthouse.mainactivity;

import java.util.ArrayList;

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

import com.example.smarthouse.Division;
import com.example.smarthouse.R;
import com.example.smarthouse.divisions.DivisionActivity;
import com.example.smarthouse.popupsalerts.notfDialog;

public class MainScreenActivity extends FragmentActivity implements Parcelable{

	private final int NUM_ELEMS_ROW = 3;
	
	private ArrayList<Division> _divisions;
	
	private TableLayout _tableLayout;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		
		_tableLayout = (TableLayout) findViewById(R.id.ButtonTable);
		
		
        _divisions = new ArrayList<Division>();
		_divisions.add(new Division("Cozinha"));
		_divisions.add(new Division("Sala"));
		_divisions.add(new Division("Quarto Edgar"));
		_divisions.add(new Division("Dispensa"));
		_divisions.add(new Division("Quarto Joao"));
		_divisions.add(new Division("Quarto Andre"));
		
		TableRow row = null;
		int buttonCount = 0, i = NUM_ELEMS_ROW;
		
		do{
			if(i >=  NUM_ELEMS_ROW){
				row = new TableRow(this);
				_tableLayout.addView(row);
			}
			Button button = new Button(this);
			button.setText(_divisions.get(buttonCount).getName());
			button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
				   Intent i = new Intent(getApplicationContext(), DivisionActivity.class);
				   i.putExtra("Division",_divisions.get(buttonCount));
				   startActivity(i);
	            }
	        });
			row.addView(button,100,50);
		}while(buttonCount < _divisions.size());
		
		for (Division division : _divisions) {
			Button button = new Button(this);
			button.setText(division.getName());
			_tableLayout.
		}
		
        button = (Button) findViewById(R.id.Sala);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
			   Intent i = new Intent(getApplicationContext(), DivisionActivity.class);
			   i.putExtra("Division","Sala");
			   startActivity(i);
            }
        });

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

	@Override
	public int describeContents() { return 0; }

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
}
