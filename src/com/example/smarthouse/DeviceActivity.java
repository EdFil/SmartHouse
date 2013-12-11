package com.example.smarthouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.example.smarthouse.divisions.DivisionActivity;
import com.example.smarthouse.mainactivity.ExpandableListAdapter;
import com.example.smarthouse.mainactivity.TextClock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

public class DeviceActivity extends FragmentActivity {

	private TextClock _textClock;
	private DataVariables _dataVariables;
	private Division _division;
	private boolean _selected;
	
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device);
		_selected = false;
		_dataVariables = (DataVariables)getApplication();
		if(!_dataVariables.isWindowInited())
			_dataVariables.initWindowSize(this);
		_division = _dataVariables._currentDivision;
		TextView _deviceName = (TextView)findViewById(R.id.deviceName);
		_deviceName.setText(_division.getName());
		_deviceName.setTextSize(((int)(_dataVariables.WIDTH*0.05)));
		
		_textClock = new TextClock(this);
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
        
        Button button0 = new Button(this);
        button0.setVisibility(View.GONE);
        button0.setId(3);
        button0.setText("Multimedia");
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(getApplicationContext(), MediaActivity.class);
            	startActivity(i);
            }
        });
        View frag = getSupportFragmentManager().findFragmentById(R.id.details).getView();
        LinearLayout l =  (LinearLayout)frag.findViewById(R.id.buttonOptions);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        l.addView(button0, lp);
        
        
        Button button1 = (Button) findViewById(R.id.ON);
        button1.setVisibility(View.GONE);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	View frag = getSupportFragmentManager().findFragmentById(R.id.details).getView();
            	_dataVariables._currentDevice.turnOn(_dataVariables._currentUser);
            	((TextView)frag.findViewById(R.id.Cons)).setText("Consumo Instantaneo: " +_dataVariables._currentDevice.getCurrentConsumption()+" ");
            }
        });
        
        Button button2 = (Button) findViewById(R.id.OFF);
        button2.setVisibility(View.GONE);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	View frag = getSupportFragmentManager().findFragmentById(R.id.details).getView();
            	_dataVariables._currentDevice.turnOff(_dataVariables._currentUser);
            	((TextView)frag.findViewById(R.id.Cons)).setText("Consumo Instantaneo: "+"0");
            }
        });
        
        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
 
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {
            	expListView.expandGroup(0);
                return true;
            }
        });
 
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
            		View frag = getSupportFragmentManager().findFragmentById(R.id.details).getView();
            		_selected = true;
                    for(Device d : _dataVariables._currentDivision.getDevices()) {
            	        if(d.getName().equals( listDataChild.get( listDataHeader.get(groupPosition)).get(childPosition)  ) )
            	        	_dataVariables._currentDevice =  d;
                    }
            		
            		((TextView)frag.findViewById(R.id.AlertDescription)).setText("");
            		((TextView)frag.findViewById(R.id.Product)).setText( listDataHeader.get(groupPosition) +""+ listDataChild.get( listDataHeader.get(groupPosition)).get(childPosition));
            		((TextView)frag.findViewById(R.id.Cons)).setText("Consumo Instantaneo: " +_dataVariables._currentDevice.getCurrentConsumption()+" ");
            		Button b;
                    if(( listDataChild.get( listDataHeader.get(groupPosition)).get(childPosition) ).equals("Televisao"))
                    {
                        b = (Button) findViewById(R.id.ON);
                        b.setVisibility(View.VISIBLE);
                        b = (Button) findViewById(R.id.OFF);
                        b.setVisibility(View.VISIBLE);
                        b = (Button) frag.findViewById(3);
                        b.setVisibility(View.VISIBLE);
                    } else {
                        b = (Button) findViewById(R.id.ON);
                        b.setVisibility(View.VISIBLE);
                        b = (Button) findViewById(R.id.OFF);
                        b.setVisibility(View.VISIBLE);
                        b = (Button) frag.findViewById(3);
                        b.setVisibility(View.GONE);	
                    }
            		
                return false;
            }

        });	
        expListView.expandGroup(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.device, menu);
		return true;
	}
	
    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
 
        // Adding child data
        	
        listDataHeader.add("Aparelhos");
        
        List<String> ElectrodoMesticos = new ArrayList<String>();
        for(Device d : _dataVariables._currentDivision.getDevices()) {
	        ElectrodoMesticos.add(d.getName());
        }
        
        listDataChild.put(listDataHeader.get(0), ElectrodoMesticos); // Header, Child data

    }

}
