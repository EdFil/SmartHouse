package com.example.smarthouse;

import java.util.HashMap;
import java.util.Map;

import android.text.format.Time;

public class History {

	private HashMap<Time, Float> _consumptionHistory;
	private HashMap<Time, Usage> _usageHistory;
	
	History(){
		setConsumptionHistory(new HashMap<Time, Float>());
		setUsageHistory(new HashMap<Time, Usage>());
	}

	public void addConsumption(float consumption) {
		_consumptionHistory.put(new Time(), consumption);
	}
	
	public void addUsage(Usage usage){
		_usageHistory.put(new Time(), usage);
	}
	
	//Getters
	public HashMap<Time, Float> getConsumptionHistory() { return _consumptionHistory; }
	public HashMap<Time, Usage> getUsageHistory() { return _usageHistory; }

	//Setters 
	private void setConsumptionHistory(HashMap<Time, Float> consumptionHistory){ _consumptionHistory = consumptionHistory; }
	private void setUsageHistory(HashMap<Time, Usage> usageHistory){ _usageHistory = usageHistory; }

	public float getAllConsumptionFromMonth(int month) {
		float res = 0;
		for (Map.Entry<Time, Float> entry : _consumptionHistory.entrySet()) {
			if(entry.getKey().month == month)
				res += entry.getValue();
		}
		return res;
	}
}
