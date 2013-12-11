package com.example.smarthouse;

public class TimedDevice extends Device {

	private static final String NAME = "Fridge";
	private static final float ON_CONSUMPTION = 725;
	private static final float OFF_CONSUMPTION = 0;
	
	
	public TimedDevice() {
		this(NAME);
	}
	
	public TimedDevice(String name) {
		super(name, ON_CONSUMPTION, OFF_CONSUMPTION);
	}
	
	@Override
	protected void endUsage(User user){
		//TimedUsage usage = (TimedUsage)super.getHistory().getUsageHistory().get(getCurrentUsage());
		//((TimedUsage)getCurrentUsage()).finish();
		super.endUsage(user);
	}
	
	@Override
	public void turnStandBy(){ /*Empty*/ }
}


