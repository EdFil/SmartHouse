package com.example.smarthouse.history;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.smarthouse.DataVariables;
import com.example.smarthouse.Division;
import com.example.smarthouse.History;
import com.example.smarthouse.R;

public class HistoryActivity extends FragmentActivity {

	private static final String TITLE = "Historico";
	private static final String ROW_1 = "Consumo Mensal: ";
	
	private final String[] MONTH_DAY = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" };
	
	private DataVariables _dataVariables;
	private TableLayout _tableLayout;
	private History _history;
	
	private Time _leftTime, _rightTime;
	
	private TextView _leftMonth, _rightMonth;
	private Button _leftGoLeft, _leftGoRight,
				   _rightGoLeft, _rightGoRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		_dataVariables = (DataVariables)getApplication();
		
		_leftTime = _rightTime = new Time();
		prevMonth(_rightTime, _leftTime);
		
		((TextView)findViewById(R.id.menuName)).setText(TITLE);
		
		
		
	
	}

	private void setUpViews() {
		_leftGoLeft = (Button)findViewById(R.id.LeftGoLeft);
		_leftGoRight = (Button)findViewById(R.id.LeftGoRight);
		_rightGoLeft = (Button)findViewById(R.id.RightGoLeft);
		_rightGoRight = (Button)findViewById(R.id.RightGoRight);
		_leftMonth = (TextView)findViewById(R.id.LeftMonth);
		_rightMonth = (TextView)findViewById(R.id.RightMonth);
		_leftGoLeft.setText("<");
		_rightGoLeft.setText("<");
		_leftGoRight.setText(">");
		_rightGoRight.setText(">");
		_leftMonth.setText(MONTH_DAY[_leftTime.month]);
		_rightMonth.setText(MONTH_DAY[_rightTime.month]);
		_leftGoLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d("XXX", "LeftGoLeft");
            }
        });
		_leftGoRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d("XXX", "LeftGoRight");
            }
        });
		_rightGoLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d("XXX", "RightGoLeft");
            }
        });
		_rightGoRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d("XXX", "RightGoRight");
            }
        });
	}

	private void setLeftSide(){
		float consumption = _dataVariables._history.getAllConsumptionFromMonth(_leftTime.month);
		((TextView)((TableRow)_tableLayout.getChildAt(1)).getChildAt(0)).setText(String.valueOf(consumption));
	}
	
	private void setRightSide(){

	}
	
	private void refreshMidSide(){

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

	private void prevMonth(Time time, Time other){
		if(time.month <= 0){
			time.month = 11;
			time.year--;
		}
		else{
			time.month--;
		}
	}
	
}

