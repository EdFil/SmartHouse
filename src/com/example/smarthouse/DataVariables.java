package com.example.smarthouse;

import java.util.ArrayList;
import java.util.Random;

import com.example.smarthouse.popupsalerts.moviePopup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ToggleButton;

import com.example.smarthouse.popupsalerts.notfDialog;

public class DataVariables extends Application {
	
	public int WIDTH = 0;
	public int HEIGHT = 0;

	public FragmentActivity _currentAct;
	public User _currentUser;
	public Division _currentDivision;
	public Device _currentDevice;
	
	public ArrayList<User> _users;
	public History _history;
	public ArrayList<Division> _divisions;	

	public boolean _onHistoryFromMainMenu = false;
	public boolean _onHistoryFromDivision = false;
	public boolean _onHistoryFromDevice = false;
	
	private Handler _handler = new Handler();
	private Runnable _generateAlert = new Runnable() {
		public void run() {
            if(_currentAct != null) {
            	notfDialog dialog = new notfDialog();
            	dialog.show(_currentAct.getSupportFragmentManager(), "notfDialog");
				//_consumptionView.setText(String.valueOf(_consumptionValue + (new Random()).nextFloat()*30/1000) + " kWh");
				_handler.postDelayed(this, 1000000);
            }
		}
	};
	
	public DataVariables(){
		initUsers();
		initDivisions();
		_handler.removeCallbacks(_generateAlert);
		_handler.postDelayed(_generateAlert, randBetween(1000, 2000));
	}
	
	public void historyFromMainMenu(){
		_onHistoryFromMainMenu = true;
		_onHistoryFromDivision = _onHistoryFromDevice = false;
	}
	
	public void historyFromDevice(){
		_onHistoryFromDevice = true;
		_onHistoryFromDivision = _onHistoryFromMainMenu = false;
	}
	
	public void historyFromDivision(){
		_onHistoryFromDivision = true;
		_onHistoryFromMainMenu = _onHistoryFromDevice = false;
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
		Division cozinha = new Division("Cozinha", R.drawable.cozinha);
		cozinha.addDevice(new TimedDevice("Torradeira"));
		cozinha.addDevice(new TimedDevice("Frigorifico"));
		cozinha.addDevice(new TimedDevice("Fogao"));
		cozinha.addDevice(new TimedDevice("Fritadeira"));
		cozinha.addDevice(new TimedDevice("Esquentador"));
		cozinha.addDevice(new TimedDevice("Televisao"));
		cozinha.addDevice(new TimedDevice("Luzes"));
		cozinha.addLight(new TimedDevice("Exaustor"));
		cozinha.addLight(new TimedDevice("Expositor"));
		cozinha.addLight(new TimedDevice("Tecto"));
		cozinha.addLight(new TimedDevice("Bancada"));
		cozinha.getDevice("Torradeira").addNotification(new Notification("Torradeira a arder!"));
		cozinha.getDevice("Fogao").addNotification(new Notification("Fogao a arder!"));
		cozinha.getDevice("Fogao").addNotification(new Notification("Fogao cheio"));
		cozinha.getDevice("Fogao").addNotification(new Notification("Fogao demasiado quente!"));
		cozinha.getDevice("Esquentador").addNotification(new Notification("Esquentador desligado do gas!"));
		cozinha.getDevice("Esquentador").addNotification(new Notification("Esquentador demasiado quente!"));
		cozinha.getDevice("Televisao").addNotification(new Notification("Televisao da cozinha a arder!"));
		cozinha.getDevice("Televisao").addNotification(new Notification("Televisao da cozinha demasiado quente!"));
		Division sala = new Division("Sala", R.drawable.sala);
		sala.addDevice(new TimedDevice("Televisao"));
		sala.addDevice(new TimedDevice("PS4"));
		sala.addDevice(new TimedDevice("Lareira Eletrica"));
		sala.addDevice(new TimedDevice("Luzes"));
		sala.addLight(new TimedDevice("Tecto"));
		sala.addLight(new TimedDevice("Bar"));
		sala.addLight(new TimedDevice("Candeiro"));
		sala.getDevice("Televisao").addNotification(new Notification("Televisao da sala a arder!"));
		sala.getDevice("Televisao").addNotification(new Notification("Curto circuito na televisao da sala!"));
		sala.getDevice("Televisao").addNotification(new Notification("Televisao da sala demasiado quente!"));
		sala.getDevice("PS4").addNotification(new Notification("PS4 demasiado quente!"));
		sala.getDevice("PS4").addNotification(new Notification("PS4 ligada ha muito tempo!"));
		_divisions.add(cozinha);
        _divisions.add(sala);
        Division quartoEdgar = new Division("Quarto Edgar", R.drawable.quarto);
        quartoEdgar.addDevice(new TimedDevice("Carro Telecomandado"));
        quartoEdgar.addLight(new TimedDevice("Candeiro"));
        quartoEdgar.addLight(new TimedDevice("Tecto"));
        _divisions.add(quartoEdgar);
        Division dispensa = new Division("Dispensa", R.drawable.dispensa);
        dispensa.addDevice(new TimedDevice("Desumidificador"));
        dispensa.addLight(new TimedDevice("Tecto"));
        _divisions.add(dispensa);
        Division quartojoao = new Division("Quarto Joao", R.drawable.quarto);
        quartojoao.addDevice(new TimedDevice("Portatil"));
        quartojoao.addLight(new TimedDevice("Candeiro"));
        quartojoao.addLight(new TimedDevice("Tecto"));
        _divisions.add(quartojoao);
        Division quartoAndre = new Division("Quarto Andre", R.drawable.quarto);
        quartoAndre.addDevice(new TimedDevice("Impresora3D"));
        quartoAndre.addLight(new TimedDevice("Candeiro"));
        quartoAndre.addLight(new TimedDevice("Tecto"));
        _divisions.add(quartoAndre);
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
	 
	 public float randBetween(float start, float end) {
	        return start + Math.round(Math.random() * (end - start));
	    }
}
