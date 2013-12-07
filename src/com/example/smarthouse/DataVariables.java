package com.example.smarthouse;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DataVariables extends Application{
	
	public int WIDTH = 0;
	public int HEIGHT = 0;

	public History _history;
	public ArrayList<Division> _divisions;	
	
	public boolean isWindowInited(){
		if(WIDTH == 0)
			return false;
		if(HEIGHT == 0)
			return false;
		return true;
	}
	
	public void initWindowSize(Activity activity) {
		WindowManager w = activity.getWindowManager();
		Display d = w.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		d.getMetrics(metrics);
		// since SDK_INT = 1;
		WIDTH = metrics.widthPixels;
		HEIGHT = metrics.heightPixels;
		// includes window decorations (statusbar bar/menu bar)
		if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
			try {
				WIDTH = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
			    HEIGHT = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
			} catch (Exception ignored) { }
		// includes window decorations  (statusbar bar/menu bar)
		else if (Build.VERSION.SDK_INT >= 17)
			try {
			    Point realSize = new Point();
			    Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
			    WIDTH = realSize.x;
			    HEIGHT = realSize.y;
			} catch (Exception ignored) {  }
	}
}