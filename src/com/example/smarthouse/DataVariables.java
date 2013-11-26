package com.example.smarthouse;

import android.app.Application;

public class DataVariables extends Application{
   private int consumo;
   
   public void setConsumo(int d){
     this.consumo=d;
   }
   public int getConsumo(){
     return this.consumo;
   }
}