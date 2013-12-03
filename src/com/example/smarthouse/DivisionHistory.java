package com.example.smarthouse;

public class DivisionHistory extends History{

	private Division _division;
	
	DivisionHistory(Division division){
		setDivision(division);
	}

	//Getters
	public Division getDivision() { return _division; }

	//Setters
	public void setDivision(Division division) { _division = division; }
	
}
