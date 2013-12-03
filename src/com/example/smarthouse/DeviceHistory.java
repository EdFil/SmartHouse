package com.example.smarthouse;

public class DeviceHistory extends History{

	private Device _device;
	
	public DeviceHistory(Device device) {
		super();
		_device = device;
	}

	//Getters
	public Device getDevice() { return _device;	}

	//Setters
	public void setDevice(Device device) { _device = device; }
	
}
