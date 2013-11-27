package com.example.smarthouse.history;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smarthouse.R;

public class HistoryActivity extends FragmentActivity {

	private final String TEXT_1 = "Consumo Total: ";
	private final String TEXT_2 = "Média Consumo Diário: ";
	private final String TEXT_3 = "Divisão mais gastadora: ";
	private final String TEXT_4 = "Média luzes ligadas mês: ";
	
	private final Random _random = new Random();
	
	private HistoryData _leftMonth, 
					    _rightMonth;
	
	private float _leftF1, _leftF2, _leftF4,
				  _rightF1, _rightF2, _rightF4;
	
	private String _leftF3, _rightF3;
	
	private TextView _calendarLeftMonth, 
    				 _calendarRightMonth,
    				 _left1, _left2, _left3, _left4,
    				 _right1, _right2, _right3, _right4,
    				 _mid1, _mid2, _mid3, _mid4;
	
	private Button _leftGoLeft,
				   _leftGoRight,
				   _rightGoLeft,
				   _rightGoRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);

		_leftMonth = new HistoryData(1);
		_rightMonth = new HistoryData(2);

		_calendarLeftMonth = (TextView) findViewById(R.id.CalendarLeftData);
		_calendarLeftMonth.setText(_leftMonth.getMonth());
		_calendarRightMonth = (TextView) findViewById(R.id.CalendarRightData);
		_calendarRightMonth.setText(_rightMonth.getMonth());
		
		_left1 = (TextView) findViewById(R.id.Left1);
		_left2 = (TextView) findViewById(R.id.Left2);
		_left3 = (TextView) findViewById(R.id.Left3);
		_left4 = (TextView) findViewById(R.id.Left4);
		_right1 = (TextView) findViewById(R.id.Right1);
		_right2 = (TextView) findViewById(R.id.Right2);
		_right3 = (TextView) findViewById(R.id.Right3);
		_right4 = (TextView) findViewById(R.id.Right4);
		_mid1 = (TextView) findViewById(R.id.Mid1);
		_mid2 = (TextView) findViewById(R.id.Mid2);
		_mid3 = (TextView) findViewById(R.id.Mid3);
		_mid4 = (TextView) findViewById(R.id.Mid4);
		
		
		_leftGoLeft = (Button) findViewById(R.id.LeftGoLeft);
		_leftGoLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	_calendarLeftMonth.setText(_leftMonth.prevMonth());
            	setLeftSide();
            	refreshMidSide();
            }
        });
		_leftGoRight = (Button) findViewById(R.id.LeftGoRight);
		_leftGoRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	_calendarLeftMonth.setText(_leftMonth.nextMonth());
            	setLeftSide();
            	refreshMidSide();
            }
        });
		_rightGoLeft = (Button) findViewById(R.id.RightGoLeft);
		_rightGoLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	_calendarRightMonth.setText(_rightMonth.prevMonth());
            	setRightSide();
            	refreshMidSide();
            }
        });
		_rightGoRight = (Button) findViewById(R.id.RightGoRight);
		_rightGoRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	_calendarRightMonth.setText(_rightMonth.nextMonth());
            	setRightSide();
            	refreshMidSide();
            }
        });
		
		setLeftSide();
		setRightSide();
		refreshMidSide();
	}

	private void setLeftSide(){
		_leftF1 = _random.nextFloat() * 150;
		_leftF2 = _leftF1 / (_random.nextFloat() * 30);
		float value = _random.nextFloat();
		if(value < 0.3)
			_leftF3 = "Quarto 1";
		else 
			_leftF3 = "Sala de Estar";
		_leftF4 = _random.nextFloat() * 25;
		_left1.setText(TEXT_1 + _leftF1);
		_left2.setText(TEXT_2 + _leftF2);
		_left3.setText(TEXT_3 + _leftF3);
		_left4.setText(TEXT_4 + _leftF4);
	}
	
	private void setRightSide(){
		_rightF1 = _random.nextFloat() * 150;
		_rightF2 = _rightF1 / (_random.nextFloat() * 30);
		float value = _random.nextFloat();
		if(value < 0.3)
			_rightF3 = "Quarto 1";
		else 
			_rightF3 = "Sala de Estar";
		_rightF4 = _random.nextFloat() * 25;
		_right1.setText(TEXT_1 + _rightF1);
		_right2.setText(TEXT_2 + _rightF2);
		_right3.setText(TEXT_3 + _rightF3);
		_right4.setText(TEXT_4 + _rightF4);
	}
	
	private void refreshMidSide(){
		_mid1.setText("" + (_leftF1 - _rightF1));
		_mid2.setText("" + (_leftF2 - _rightF2));
		if(_leftF3.equals(_rightF3))
			_mid3.setText("=");
		else
			_mid3.setText("!=");
		_mid4.setText("" + (_leftF4 - _rightF4));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

}

