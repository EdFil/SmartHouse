package com.example.smarthouse;

import java.util.ArrayList;

public class Division{

	private String _name;
	private ArrayList<Device> _devices;
	private ArrayList<Device> _lights;
	private DivisionHistory _history;
	private int _img;
	
	public Division(String name, int img){
		setImg(img);
		setName(name);
		setHistory(new DivisionHistory());
		setDevices(new ArrayList<Device>());
		setLights(new ArrayList<Device>());
	}
	
	/** Adds a new Device to the devision
	 * 
	 * @param device the device to be added
	 */
	public void addDevice(Device device){
		_devices.add(device);
	}
	
	/** Removes a new Device to the division
	 * 
	 * @param device the device to be removed
	 */
	public void removeDevice(Device device){
		_devices.remove(device);
	}
	/** Adds a new Light to the devision
	 * 
	 * @param device the device to be added
	 */
	public void addLight(Device device){
		_lights.add(device);
	}
	
	/** Removes a new Light to the division
	 * 
	 * @param device the device to be removed
	 */
	public void removeLight(Device device){
		_lights.remove(device);
	}	

	//Getters 
	public String getName() { return _name;	}
	public int getImg() { return _img; }
	public DivisionHistory getHistory() { return _history; }
	public ArrayList<Device> getDevices() { return _devices; }
	public Device getDevice(String name){
		for(Device d : _devices){
			if(d.getName().equals(name)){
				return d;
			}
		}
		return null;
	}
	public ArrayList<Device> getLights() { return _lights; }
	public Device getLight(String name){
		for(Device d : _lights){
			if(d.getName().equals(name)){
				return d;
			}
		}
		return null;
	}

	//Setters
	private void setImg(int _img) { this._img = _img; }
	private void setName(String _name) { this._name = _name; }
	private void setHistory(DivisionHistory history) { _history = history; }
	private void setDevices(ArrayList<Device> devices) { _devices = devices; }
	private void setLights(ArrayList<Device> lights) { _lights = lights; }

}
