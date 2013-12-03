package com.example.smarthouse;

public class SimpleUsage extends Usage {

	SimpleUsage(User user, Device device) {
		super(user, device);
	}
	
	@Override
	public String toString(){
		return super.getUser().getName() + " used the " + super.getDevice().getName() + ".";		
	}
	
}
