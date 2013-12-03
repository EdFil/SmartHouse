package com.example.smarthouse;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Division implements Parcelable{

	private String _name;
	private ArrayList<Device> _devices;
	private DivisionHistory _history;
	
	public Division(String name){
		setName(name);
		setHistory(new DivisionHistory(this));
		setDevices(new ArrayList<Device>());
	}
	
	public Division(Parcel in){
		
	}
	
	/** Adds a new Device to the devision
	 * 
	 * @param device the device to be added
	 */
	public void addDevice(Device device){
		_devices.add(device);
	}
	
	/** Removes a new Device to the devision
	 * 
	 * @param device the device to be removed
	 */
	public void removeDevice(Device device){
		_devices.remove(device);
	}
	
	//Getters 
	public String getName() { return _name;	}
	public DivisionHistory getHistory() { return _history; }
	public ArrayList<Device> getDevices() { return _devices; }

	
	//Setters
	private void setName(String _name) { this._name = _name; }
	private void setHistory(DivisionHistory history) { _history = history; }
	private void setDevices(ArrayList<Device> devices) { _devices = devices; }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
	
}
