package com.example.smarthouse.history;

import java.util.Map;
import java.util.TreeMap;

public class HistoryData {

	private Map<Integer, String> _monthMap;
	private int _currentMonthSelected;
	
	HistoryData(int month){
		_currentMonthSelected = month;
		_monthMap = new TreeMap<Integer, String>();
		_monthMap.put(1, "Janeiro");
		_monthMap.put(2, "Fevereiro");
		_monthMap.put(3, "Março");
		_monthMap.put(4, "Abril");
		_monthMap.put(5, "Maio");
		_monthMap.put(6, "Junho");
		_monthMap.put(7, "Julho");
		_monthMap.put(8, "Agosto");
		_monthMap.put(9, "Setembro");
		_monthMap.put(10, "Outubro");
		_monthMap.put(11, "Novembro");
		_monthMap.put(12, "Dezembro");
	}
	
	public String getMonth(){
		return _monthMap.get(_currentMonthSelected);
	}
	
	public String prevMonth(){
		_currentMonthSelected--;
		if(_currentMonthSelected < 1)
			_currentMonthSelected = 12;
		return _monthMap.get(_currentMonthSelected);
	}
	
	public String nextMonth(){
		_currentMonthSelected++;
		if(_currentMonthSelected > 12)
			_currentMonthSelected = 1;
		return _monthMap.get(_currentMonthSelected);
	}
	
}

