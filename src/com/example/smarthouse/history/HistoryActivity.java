package com.example.smarthouse.history;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.smarthouse.Breadcumbs;
import com.example.smarthouse.DataVariables;
import com.example.smarthouse.R;
import com.example.smarthouse.mainactivity.TextClock;

public class HistoryActivity extends FragmentActivity {
	
	private static final String ROW_TITLE_1 = "Consumo Mensal:";
	private static final String ROW_TITLE_2 = "Title 2:";
	private static final String ROW_TITLE_3 = "Title 3:";
	private static final String ROW_TITLE_4 = "Title 4:";
	private static final String ROW_TITLE_5 = "Title 5:";
	private static final String ROW_TITLE_6 = "Title 6:";
	
	private final String[] MONTH_DAY = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" };
	
	private FrameLayout.LayoutParams _params;
	
	private DataVariables _dataVariables;
	
	private Time _leftTime, _rightTime;
	
	private TextView _leftMonth, _rightMonth;
	private Button _leftGoLeft, _leftGoRight,
				   _rightGoLeft, _rightGoRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		_dataVariables = (DataVariables)getApplication();
		
		new Breadcumbs(this);
		new TextClock(this);
		
		//Create params for the textViews
		_params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		_params.gravity = Gravity.CENTER;
		_params.setMargins(5, 10, 0, 0);
		
		//Setup title rows
		setTextOnId(0, 1, ROW_TITLE_1);
		setTextOnId(0, 2, ROW_TITLE_2);
		setTextOnId(0, 3, ROW_TITLE_3);
		setTextOnId(0, 4, ROW_TITLE_4);
		setTextOnId(0, 5, ROW_TITLE_5);
		setTextOnId(0, 6, ROW_TITLE_6);
		
		//Setup the calendar between the buttons
		_leftTime = new Time();
		_rightTime = new Time();
		_leftTime.setToNow();
		_rightTime.setToNow();
		prevMonth(_leftTime, _rightTime);
		setUpViews();
	}

	private void setUpViews() {
		_leftGoLeft = (Button)findViewById(R.id.LeftGoLeft);
		_leftGoRight = (Button)findViewById(R.id.LeftGoRight);
		_rightGoLeft = (Button)findViewById(R.id.RightGoLeft);
		_rightGoRight = (Button)findViewById(R.id.RightGoRight);
		_leftMonth = (TextView)findViewById(R.id.LeftMonth);
		_rightMonth = (TextView)findViewById(R.id.RightMonth);
		_leftMonth.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int)(_dataVariables.HEIGHT*0.035));
		_rightMonth.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int)(_dataVariables.HEIGHT*0.035));
		_leftGoLeft.setText("<");
		_rightGoLeft.setText("<");
		_leftGoRight.setText(">");
		_rightGoRight.setText(">");
		refreshLeftSide();
		refreshRightSide();
		refreshMidSide();
		_leftGoLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	prevMonth(_leftTime, _rightTime);
            	refreshLeftSide();
            	refreshMidSide();
            }
        });
		_leftGoRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	nextMonth(_leftTime, _rightTime);
            	refreshLeftSide();
            	refreshMidSide();
            }
        });
		_rightGoLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	prevMonth(_rightTime, _leftTime);
            	refreshRightSide();
            	refreshMidSide();
            }
        });
		_rightGoRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	nextMonth(_rightTime, _leftTime);
            	refreshRightSide();
            	refreshMidSide();
            }
        });
	}

	private void refreshLeftSide(){
		Float consumption;
		if(_dataVariables._onHistoryFromMainMenu)
			consumption = _dataVariables._history.getAllConsumptionFromMonth(_leftTime.month);
		else
			consumption = _dataVariables.randBetween(500f, 1000f);
		setTextOnId(1, 1, consumption.toString());
		_leftMonth.setText(MONTH_DAY[_leftTime.month] + "\n" + _leftTime.year);
	}
	
	private void refreshRightSide(){
		Float consumption;
		if(_dataVariables._onHistoryFromMainMenu)
			consumption = _dataVariables._history.getAllConsumptionFromMonth(_rightTime.month);
		else
			consumption = _dataVariables.randBetween(500f, 1000f);
		setTextOnId(3, 1, consumption.toString());
		_rightMonth.setText(MONTH_DAY[_rightTime.month] + "\n" + _rightTime.year);
	}
	
	private void refreshMidSide(){
		float leftConsumptionValue = Float.valueOf((String) getTextOnId(1, 1).getText());
		float rightConsumptionValue = Float.valueOf((String) getTextOnId(3, 1).getText());
		setTextOnId(2, 1, String.valueOf(leftConsumptionValue - rightConsumptionValue));
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
		if(time.month == other.month && time.year == other.year)
			prevMonth(time, other);
	}
	
	private void nextMonth(Time time, Time other){
		if(time.month >= 11){
			time.month = 0;
			time.year++;
		}
		else{
			time.month++;
		}
		if(time.month == other.month && time.year == other.year)
			nextMonth(time, other);
	}
	
	private void setTextOnId(int x, int y, String text){
		TableLayout table = (TableLayout)findViewById(R.id.HistoryTable);
		TableRow row = (TableRow)table.getChildAt(y);
		TextView textView = (TextView)row.getChildAt(x);
		textView.setText(text);
	}
	
	private TextView getTextOnId(int x, int y){
		TableLayout table = (TableLayout)findViewById(R.id.HistoryTable);
		TableRow row = (TableRow)table.getChildAt(y);
		return (TextView)row.getChildAt(x);
		
	}
}


	