package com.example.smarthouse.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import com.example.smarthouse.Breadcumbs;
import com.example.smarthouse.DataVariables;
import com.example.smarthouse.Device;
import com.example.smarthouse.Division;
import com.example.smarthouse.Notification;
import com.example.smarthouse.R;
import com.example.smarthouse.divisions.DivisionActivity;
import com.example.smarthouse.history.HistoryActivity;
import com.example.smarthouse.popupsalerts.notfDialog;

public class MainScreenActivity extends FragmentActivity{

	private final int NUM_ELEMS_ROW = 3;
	private DataVariables _dataVariables;
	private TableLayout _tableLayout;
	private HorizontalScrollView _hscrollview;
	private int buttonCount = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen); 
		
		_tableLayout = (TableLayout) findViewById(R.id.ButtonTable);
		_hscrollview = (HorizontalScrollView) findViewById(R.id.NotificationView);
		_dataVariables = (DataVariables)getApplication();
		if(!_dataVariables.isWindowInited())
			_dataVariables.initWindowSize(this);
		_dataVariables._currentAct = this;
		((Button)findViewById(R.id.historyButton)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
            }
        } );
		
		new Breadcumbs(this);
		new TextClock(this);
		
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
			//just a test
			button.setCompoundDrawablesWithIntrinsicBounds(0, _dataVariables._divisions.get(buttonCount).getImg(), 0, 0);
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					_dataVariables._currentDivision = _dataVariables._divisions.get(v.getId());
					startActivity(new Intent(getApplicationContext(), DivisionActivity.class));
	            }
	        } );
			row.addView(button,((int)(_dataVariables.WIDTH*0.3)),((int)(_dataVariables.HEIGHT*0.3)));
			buttonCount++; i++;
		}
		
		buttonCount = 0;
		LinearLayout llayout = (LinearLayout) findViewById(R.id.llayout);
		
		for(int temp = 0; temp < _dataVariables._divisions.size(); temp++){
			Division d = _dataVariables._divisions.get(temp);
			for(int j = 0; j < d.getDevices().size(); j++){
				Device device = d.getDevices().get(j);
				for(Notification notification : device.getNotifications()){
					Button button = new Button(this);
					button.setText(notification.getDescription());
					button.setId(buttonCount);
//					button.setOnClickListener(new View.OnClickListener() {
//			            public void onClick(Vi1ew v) {
//						   Intent intent = new Intent(getApplicationContext(), DivisionActivity.class);
//						   intent.putExtra("Division", v.getId());
//						   startActivity(intent);
//			            }
//			        } );
					llayout.addView(button,((int)(_dataVariables.WIDTH*0.25)),LayoutParams.MATCH_PARENT);
					buttonCount++;
				}
			}
		}
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
