package com.example.smarthouse;

import java.util.Random;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

public class InstantConsumption implements InfoArea {

	private Handler _handler = new Handler();
	private Runnable _updateTimeTask = new Runnable() {
		public void run() {
			_consumptionValue = 0;
			for(Division division : _dataVariables._divisions){
				for (Device device : division.getDevices()) {
					_consumptionValue += device.getCurrentConsumption();
				}
			}
			_consumptionView.setText(String.valueOf(_consumptionValue + (new Random()).nextFloat()*30/1000) + " kWh");
			_handler.postDelayed(this, 1000);
		}
	};
	private static final String TITLE = "Consumo instantaneo";
	private DataVariables _dataVariables;
	private TextView _titleView,
					 _consumptionView;
	private float _consumptionValue;
	
	public InstantConsumption(Activity activity){
		_dataVariables = (DataVariables)activity.getApplication();
		_titleView = new TextView(activity);
		_consumptionView = new TextView(activity);
		_handler.removeCallbacks(_updateTimeTask);
		_handler.postDelayed(_updateTimeTask, 1000);
		
		_consumptionValue = 0;
		_titleView.setText(TITLE);
		_titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, _dataVariables.HEIGHT*0.1f);
		_titleView.setGravity(Gravity.CENTER_HORIZONTAL);
		_titleView.setTypeface(null, Typeface.BOLD);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int) (_dataVariables.HEIGHT*0.8));
		params.gravity = Gravity.TOP;
		_titleView.setLayoutParams(params);
		
		_consumptionView.setTextSize(TypedValue.COMPLEX_UNIT_PX, _dataVariables.HEIGHT*0.07f);
		_consumptionView.setTypeface(null, Typeface.ITALIC);
		_consumptionView.setGravity(Gravity.CENTER);
		_consumptionView.setLayoutParams(params);
	}
	
	@Override
	public void setFrameLayout(FrameLayout frameLayout) {
		frameLayout.removeAllViews();
		frameLayout.addView(_titleView);
		frameLayout.addView(_consumptionView);
	}

}
