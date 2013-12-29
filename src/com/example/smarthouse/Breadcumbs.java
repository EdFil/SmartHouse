package com.example.smarthouse;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.smarthouse.divisions.DivisionActivity;
import com.example.smarthouse.history.HistoryActivity;
import com.example.smarthouse.mainactivity.MainScreenActivity;

@SuppressLint("NewApi")
public class Breadcumbs{

	private LinearLayout _breadcrumbsLayout;
	private DataVariables _dataVariables;
	
	public Breadcumbs(Activity activity) {
		_breadcrumbsLayout = (LinearLayout)activity.findViewById(R.id.Breadcumbs);
		_dataVariables = (DataVariables)activity.getApplication();
		if(activity instanceof MainScreenActivity){
			addHomeButton(activity);
		} 
		else if (activity instanceof DivisionActivity){
			addHomeButton(activity);
			addDivisionButton(activity);
		}
		else if (activity instanceof DeviceActivity){
			addHomeButton(activity);
			addDivisionButton(activity);
			addDeviceButton(activity);
		}
	}
	
	@SuppressWarnings("deprecation")
	private void addHomeButton(final Activity activity){
		GradientDrawable drawable = new GradientDrawable();
	    drawable.setShape(GradientDrawable.RECTANGLE);
	    drawable.setStroke(5, Color.WHITE);
	    drawable.setColor(Color.TRANSPARENT);
	    drawable.setCornerRadius(5f);
	    drawable.setSize((int)(_dataVariables.WIDTH * 0.8), (int)(_dataVariables.HEIGHT * 0.1));
		Button mainMenu = new Button(activity);
		mainMenu.setText("Home");
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
			mainMenu.setBackgroundDrawable(drawable);
		else
			mainMenu.setBackground(drawable);
		mainMenu.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				activity.startActivity(new Intent(activity.getApplicationContext(), MainScreenActivity.class));
            }
        });
		_breadcrumbsLayout.addView(mainMenu);
	}
	
	private void addDivisionButton(final Activity activity){
		Button division = new Button(activity);
		division.setText(_dataVariables._currentDivision.getName());
		division.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				activity.startActivity(new Intent(activity.getApplicationContext(), DivisionActivity.class));
            }
        });
		_breadcrumbsLayout.addView(division);
	}
	
	private void addDeviceButton(final Activity activity){
		Button device = new Button(activity);
		device.setText(_dataVariables._currentDevice.getName());
		device.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				activity.startActivity(new Intent(activity.getApplicationContext(), DeviceActivity.class));
            }
        });
		_breadcrumbsLayout.addView(device);
	}
}
