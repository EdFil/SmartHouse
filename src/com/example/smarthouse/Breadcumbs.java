package com.example.smarthouse;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smarthouse.divisions.DivisionActivity;
import com.example.smarthouse.mainactivity.MainScreenActivity;

public class Breadcumbs{

	private LinearLayout _breadcrumbsLayout;
	private DataVariables _dataVariables;
	
	public Breadcumbs(Activity activity) {
		_breadcrumbsLayout = (LinearLayout)activity.findViewById(R.id.Breadcumbs);
		_dataVariables = (DataVariables)activity.getApplication();
		if(activity instanceof MainScreenActivity){
			addHomeButton(activity, true);
		} 
		else if (activity instanceof DivisionActivity){
			addHomeButton(activity, false);
			addSeparator(activity);
			addDivisionButton(activity, true);
		}
		else if (activity instanceof DeviceActivity){
			addHomeButton(activity, false);
			addSeparator(activity);
			addDivisionButton(activity, false);
			addSeparator(activity);
			addDeviceButton(activity, true);
		}
		else if (activity instanceof LightActivity){
			addHomeButton(activity, false);
			addSeparator(activity);
			addDivisionButton(activity, false);
			addSeparator(activity);
			addLightButton(activity, true);
		}
	}
	
	private void addHomeButton(final Activity activity, boolean end){
		Button mainMenu = returnNewButton(activity, end);
		mainMenu.setText("Home");
		mainMenu.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				activity.startActivity(new Intent(activity.getApplicationContext(), MainScreenActivity.class));
            }
        });
		_breadcrumbsLayout.addView(mainMenu);
	}
	
	private void addDivisionButton(final Activity activity, boolean end){
		Button division = returnNewButton(activity, end);
		division.setText(_dataVariables._currentDivision.getName());
		division.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				activity.startActivity(new Intent(activity.getApplicationContext(), DivisionActivity.class));
            }
        });
		_breadcrumbsLayout.addView(division);
	}
	
	private void addDeviceButton(final Activity activity, boolean end){
		Button device = returnNewButton(activity, end);
		device.setText("Aparelhos");
		device.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				activity.startActivity(new Intent(activity.getApplicationContext(), DeviceActivity.class));
            }
        });
		_breadcrumbsLayout.addView(device);
	}
	
	private void addLightButton(final Activity activity, boolean end){
		Button device = returnNewButton(activity, end);
		device.setText("Iluminacao");
		device.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				activity.startActivity(new Intent(activity.getApplicationContext(), DeviceActivity.class));
            }
        });
		_breadcrumbsLayout.addView(device);
	}
	
	private void addSeparator(final Activity activity){
		TextView separator = new TextView(activity);
		separator.setText(">");
		separator.setTextSize(TypedValue.COMPLEX_UNIT_PX, _dataVariables.HEIGHT*0.05f);
		separator.setPadding(0, 0, (int)(_dataVariables.HEIGHT*0.01), 0);
		_breadcrumbsLayout.addView(separator);
	}
	
	@SuppressLint("NewApi")
	private Button returnNewButton(Activity activity, boolean end){
		Button button = new Button(activity);
		button.setHeight((int)(_dataVariables.HEIGHT*0.2));
		button.setBackgroundColor(Color.BLACK);
		button.setTextSize(TypedValue.COMPLEX_UNIT_PX, _dataVariables.HEIGHT*0.05f);
		button.setTextColor(Color.WHITE);
		if(!end)
			button.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		if(end){
			button.setScaleX(1.1f);
			button.setScaleY(1.1f);
		}
		return button;
	}
}
