package com.example.smarthouse;

import java.util.ArrayList;

public abstract class Device {

	protected enum Status{
		ON, OFF, STAND_BY
	}
	
	private float _offConsumption;
	private float _onConsumption;
	private float _standByConsumption;
	
	private float _currentConsumption;
	private String _name;
	private DeviceHistory _history;
	private ArrayList<Notification> _notifications;
	private Status _status;
	private Usage _currentUsage;
	
	public Device(String name, float onConsumption, float offConsumption, float standByConsumption){
		_name = name;
		_status = Status.OFF;
		_onConsumption = onConsumption;
		_offConsumption = offConsumption;
		_standByConsumption = standByConsumption;
		_history = new DeviceHistory();
		_notifications = new ArrayList<Notification>();
	}
	
	public Device(String name, float onConsumption, float offConsumption){
		this(name, onConsumption, offConsumption, 0.0f);
	}

	//Getter
	public String getName() { return _name; }
	public Usage getCurrentUsage() { return _currentUsage; }
	public DeviceHistory getHistory() { return _history; }
	public ArrayList<Notification> getNotifications() { return _notifications;}
	public boolean isOn() { return _status == Status.ON;}
	public boolean isOff() { return _status == Status.OFF;}
	public boolean isStandBy() { return _status == Status.STAND_BY;}
	public float getCurrentConsumption(){ return _currentConsumption; }
	
	//Setter
	public void addNotification(Notification not) { _notifications.add(not);}
	public void setName(String name) { _name = name; }
	public void setHistory(DeviceHistory history) { _history = history; }
	public void turnOn(User user){ 
		_status = Status.ON; 
		setConsumption(_onConsumption);
		newUsage(user);
		_history.addUsage(_currentUsage);
	}
	public void turnOff(User user){
		_status = Status.OFF;
		setConsumption(_offConsumption);
		endUsage(user);
	}
	public void turnStandBy(){
		_status = Status.STAND_BY;
		setConsumption(_standByConsumption);
	}
	protected void setConsumption(float value){ _currentConsumption = value; }
	protected void newUsage(User user){
		_currentUsage = new Usage(user, this);
	}
	protected void endUsage(User user){
		_currentUsage = null;
	}
	
}

