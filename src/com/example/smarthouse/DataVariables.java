package com.example.smarthouse;

import android.app.Application;

public class DataVariables extends Application{
	
	private History _history;
	
	//Getters
	public History getHistory() { return _history; }

	//Setters
	public void setHistory(History history) { _history = history; }
   
}