package com.example.smarthouse.mainactivity;

import java.util.ArrayList;

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
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

import com.example.smarthouse.DataVariables;
import com.example.smarthouse.Device;
import com.example.smarthouse.Division;
import com.example.smarthouse.Notification;
import com.example.smarthouse.R;
import com.example.smarthouse.TimedDevice;
import com.example.smarthouse.User;
import com.example.smarthouse.divisions.DivisionActivity;
import com.example.smarthouse.popupsalerts.notfDialog;

public class MainScreenActivity extends FragmentActivity{

	private TextClock _textClock;
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
		initDivisions();
		initUsers();
		TextView _menuName = (TextView)findViewById(R.id.menuName);
		_menuName.setText("Home");
		_menuName.setTextSize(((int)(_dataVariables.WIDTH*0.05)));
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
					_dataVariables._currentDivision = _dataVariables._divisions.get(v.getId());
					startActivity(new Intent(getApplicationContext(), DivisionActivity.class));
	            }
	        } );
			row.addView(button,((int)(_dataVariables.WIDTH*0.3)),((int)(_dataVariables.HEIGHT*0.2)));
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

	private void initUsers(){
		_dataVariables._users = new ArrayList<User>();
		_dataVariables._users.add(new User("Edgar"));
		_dataVariables._users.add(new User("Andre"));
		_dataVariables._users.add(new User("Joao"));
		_dataVariables._currentUser = _dataVariables._users.get(0);
	}
	
	private void initDivisions() {
		_dataVariables._divisions = new ArrayList<Division>();
		Division cozinha = new Division("Cozinha");
		cozinha.addDevice(new TimedDevice("Torradeira"));
		cozinha.addDevice(new TimedDevice("Frigorifico"));
		cozinha.addDevice(new TimedDevice("Fogao"));
		cozinha.addDevice(new TimedDevice("Fritadeira"));
		cozinha.addDevice(new TimedDevice("Esquentador"));
		cozinha.addDevice(new TimedDevice("Televisao"));
		cozinha.addDevice(new TimedDevice("Luzes"));
		cozinha.getDevice("Torradeira").addNotification(new Notification("Torradeira a arder!"));
		cozinha.getDevice("Fogao").addNotification(new Notification("Fogão a arder!"));
		cozinha.getDevice("Fogao").addNotification(new Notification("Fogão cheio"));
		cozinha.getDevice("Fogao").addNotification(new Notification("Fogão demasiado quente!"));
		cozinha.getDevice("Esquentador").addNotification(new Notification("Esquentador desligado do gás!"));
		cozinha.getDevice("Esquentador").addNotification(new Notification("Esquentador demasiado quente!"));
		cozinha.getDevice("Televisao").addNotification(new Notification("Televisão da cozinha a arder!"));
		cozinha.getDevice("Televisao").addNotification(new Notification("Televisão da cozinha demasiado quente!"));
		Division sala = new Division("Sala");
		sala.addDevice(new TimedDevice("Televisao"));
		sala.addDevice(new TimedDevice("PS4"));
		sala.addDevice(new TimedDevice("Lareira Eletrica"));
		sala.addDevice(new TimedDevice("Luzes"));
		sala.getDevice("Televisao").addNotification(new Notification("Televisão da sala a arder!"));
		sala.getDevice("Televisao").addNotification(new Notification("Curto circuito na televisão da sala!"));
		sala.getDevice("Televisao").addNotification(new Notification("Televisão da sala demasiado quente!"));
		sala.getDevice("PS4").addNotification(new Notification("PS4 demasiado quente!"));
		sala.getDevice("PS4").addNotification(new Notification("PS4 ligada à muito tempo!"));
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
