package com.example.smarthouse;

import android.os.SystemClock;

public class TimedUsage extends Usage {

	private boolean _running;
	private long _time;

	public TimedUsage(User user, Device device) {
		super(user, device);
		setRunning(true);
		setTime(SystemClock.elapsedRealtime());
		// TODO Auto-generated constructor stub
	}
	
	//Getters
	public long getTime() { 
		if(_running)
			return SystemClock.elapsedRealtime() - _time;
		return _time;
	}
	public boolean isRunning() { return _running; }

	//Setters
	private void setTime(long time) { _time = time; }
	private void setRunning(boolean running) { _running = running; }
	
	public void finish(){
		if(_running){
			long elapsedRunningTime = SystemClock.elapsedRealtime() - _time;
			setTime(elapsedRunningTime);
			setRunning(false);
		}
	}
	
	@Override
	public String toString(){
		if(_running)
			return super.getUser().getName() + " has been using the " + super.getDevice().getName() + " for " + getTime() + " seconds.";
		return super.getUser().getName() + " used the " + super.getDevice().getName() + " for " + getTime() + " seconds.";		
	}
}
