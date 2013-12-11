package com.example.smarthouse;

import java.util.ArrayList;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.graphics.Point;
import android.os.Build;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DataVariables extends Application{
	
	public int WIDTH = 0;
	public int HEIGHT = 0;

	public User _currentUser;
	public Division _currentDivision;
	public Device _currentDevice;
	
	public ArrayList<User> _users;
	public History _history;
	public ArrayList<Division> _divisions;	
	
	public DataVariables(){
		initUsers();
		initDivisions();
	}
	
	public boolean isWindowInited(){
		if(WIDTH == 0 || HEIGHT == 0)
			return false;
		return true;
	}
	
	@SuppressLint("NewApi")
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
	
	private void initUsers(){
		_users = new ArrayList<User>();
		_users.add(new User("Edgar"));
		_users.add(new User("Andre"));
		_users.add(new User("Joao"));
		_currentUser = _users.get(0);
	}
	
	private void initDivisions() {
		_divisions = new ArrayList<Division>();
		Division cozinha = new Division("Cozinhas");
		cozinha.addDevice(new TimedDevice("Torradeira"));
		cozinha.addDevice(new TimedDevice("Frigorifico"));
		cozinha.addDevice(new TimedDevice("Fogao"));
		cozinha.addDevice(new TimedDevice("Fritadeira"));
		cozinha.addDevice(new TimedDevice("Esquentador"));
		cozinha.addDevice(new TimedDevice("Televisao"));
		cozinha.addDevice(new TimedDevice("Luzes"));
		cozinha.getDevice("Torradeira").addNotification(new Notification("Torradeira a arder!"));
		cozinha.getDevice("Fogao").addNotification(new Notification("Fog�o a arder!"));
		cozinha.getDevice("Fogao").addNotification(new Notification("Fog�o cheio"));
		cozinha.getDevice("Fogao").addNotification(new Notification("Fog�o demasiado quente!"));
		cozinha.getDevice("Esquentador").addNotification(new Notification("Esquentador desligado do g�s!"));
		cozinha.getDevice("Esquentador").addNotification(new Notification("Esquentador demasiado quente!"));
		cozinha.getDevice("Televisao").addNotification(new Notification("Televis�o da cozinha a arder!"));
		cozinha.getDevice("Televisao").addNotification(new Notification("Televis�o da cozinha demasiado quente!"));
		Division sala = new Division("Sala");
		sala.addDevice(new TimedDevice("Televisao"));
		sala.addDevice(new TimedDevice("PS4"));
		sala.addDevice(new TimedDevice("Lareira Eletrica"));
		sala.addDevice(new TimedDevice("Luzes"));
		sala.getDevice("Televisao").addNotification(new Notification("Televis�o da sala a arder!"));
		sala.getDevice("Televisao").addNotification(new Notification("Curto circuito na televis�o da sala!"));
		sala.getDevice("Televisao").addNotification(new Notification("Televis�o da sala demasiado quente!"));
		sala.getDevice("PS4").addNotification(new Notification("PS4 demasiado quente!"));
		sala.getDevice("PS4").addNotification(new Notification("PS4 ligada � muito tempo!"));
		_divisions.add(cozinha);
        _divisions.add(sala);
        _divisions.add(new Division("Quarto Edgar"));
        _divisions.add(new Division("Dispensa"));
        _divisions.add(new Division("Quarto Joao"));
        _divisions.add(new Division("Quarto Andre"));
        _history = new History();
        for(Division division : _divisions){
        	for(Device device : division.getDevices()){
        		for(int month = 0; month < 12; month++){
        			for(int day = 0; day < 31; day++){
	        			float newValue = ((new Random()).nextFloat() * 500) / ((new Random()).nextFloat() * 250);
	        			Time time = new Time();
	        			time.set(day, month, 2013);
	        			_history.addConsumption(time, newValue);
        			}
        		}
        	}
        }		
	}
	
	 public static int randBetween(int start, int end) {
	        return start + (int)Math.round(Math.random() * (end - start));
	    }
}
