package com.example.smarthouse;

public class Usage {

	private User _user;
	private Device _device;

	Usage(User user, Device device){
		setUser(user);
	}
	
	//Getters
	public User getUser() { return _user; }
	public Device getDevice() { return _device; }

	//Setters
	private void setUser(User user) { _user = user; }
	public void setDevice(Device device) { this._device = device; }
	
	
}
