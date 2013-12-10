package com.example.smarthouse;

import java.util.ArrayList;

public class Device {

	private String _name;
	private DeviceHistory _history;
	private ArrayList<Notification> _notifications;
	
	public Device(String name){
		_name = name;
		_history = new DeviceHistory();
		_notifications = new ArrayList<Notification>();
	}
	
	//Getter
	public String getName() { return _name; }
	public DeviceHistory getHistory() { return _history; }
	public ArrayList<Notification> getNotifications() { return _notifications;}
	
	//Setter
	public void addNotification(Notification not) { _notifications.add(not);}
	public void setName(String name) { _name = name; }
	public void setHistory(DeviceHistory history) { _history = history; }
	
}
