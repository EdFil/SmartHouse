package com.example.smarthouse;

public class Device {

	private String _name;
	private DeviceHistory _history;
	
	Device(String name){
		_name = name;
		_history = new DeviceHistory();
	}
	
	//Getter
	public String getName() { return _name; }
	public DeviceHistory getHistory() { return _history; }
	
	//Setter
	public void setName(String name) { _name = name; }
	public void setHistory(DeviceHistory history) { _history = history; }
	
}
