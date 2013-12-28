package com.example.smarthouse;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

@SuppressLint("NewApi")
public class NotificationArea implements InfoArea {

	private Handler _handler = new Handler();
	private Runnable _updateTimeTask = new Runnable() {
		public void run() {
			_handler.postDelayed(this, 1000);
		}
	};
	
	private DataVariables _dataVariables;
	private TableLayout _notificationTable;
	private ScrollView _scrollView;
	
	@SuppressWarnings("deprecation")
	public NotificationArea(Activity activity){
		_dataVariables = (DataVariables)activity.getApplication();
		_notificationTable = new TableLayout(activity);
		 _scrollView = new ScrollView(activity);
		_handler.removeCallbacks(_updateTimeTask);
		_handler.postDelayed(_updateTimeTask, 1000);
		
		GradientDrawable drawable = new GradientDrawable();
	    drawable.setShape(GradientDrawable.RECTANGLE);
	    drawable.setStroke(5, Color.WHITE);
	    drawable.setColor(Color.TRANSPARENT);
	    drawable.setCornerRadius(5f);
	    drawable.setSize((int)(_dataVariables.WIDTH * 0.8), (int)(_dataVariables.HEIGHT * 0.1));
		
		for (Division division : _dataVariables._divisions) {
			for (Device device : division.getDevices()) {
				for(Notification notification : device.getNotifications()){
					TableRow row = new TableRow(activity);
					Button notificationButton = new Button(activity);
					notificationButton.setText(notification.getDescription());
					notificationButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, _dataVariables.HEIGHT*0.03f);
					if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
						notificationButton.setBackgroundDrawable(drawable);
					else
						notificationButton.setBackground(drawable);
					notificationButton.setTextColor(Color.WHITE);
					row.addView(notificationButton);
					_notificationTable.addView(row);
				}
			}
		}
		
		_scrollView.addView(_notificationTable);
	}
	
	@Override
	public void setFrameLayout(FrameLayout frameLayout) {
		frameLayout.removeAllViews();
		frameLayout.addView(_scrollView);
	}

}
