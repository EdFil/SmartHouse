package com.example.smarthouse.history;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smarthouse.DataVariables;
import com.example.smarthouse.History;
import com.example.smarthouse.R;

public class HistoryActivity extends FragmentActivity {

	private static final String TITLE = "Historico";
	private static final String ROW_1 = "Consumo Mensal: ";
	
	private final String[] MONTH_DAY = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" };
	
	private FrameLayout.LayoutParams _params;
	private TextView _defaultText;
	
	private DataVariables _dataVariables;
	private History _history;
	private LinearLayout _leftData, _rightData, _midData;
	
	private Time _leftTime, _rightTime;
	
	private TextView _leftMonth, _rightMonth;
	private Button _leftGoLeft, _leftGoRight,
				   _rightGoLeft, _rightGoRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		_dataVariables = (DataVariables)getApplication();
		
		_params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		_params.gravity = Gravity.CENTER;
		_params.setMargins(5, 10, 0, 0);
		_defaultText = new TextView(this);
		_defaultText.setGravity(Gravity.CENTER);
		_leftData = (LinearLayout)findViewById(R.id.LeftData);
		_rightData = (LinearLayout)findViewById(R.id.RightData);
		_midData = (LinearLayout)findViewById(R.id.MidData);
		
		_leftTime = new Time();
		_rightTime = new Time();
		_leftTime.setToNow();
		_rightTime.setToNow();
		prevMonth(_leftTime, _rightTime);
		
		setUpViews();
		((TextView)findViewById(R.id.menuName)).setText(TITLE);
		((TextView)findViewById(R.id.menuName)).setTextSize(((int)(_dataVariables.WIDTH*0.05)));
		
		
		
	
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
		_leftData.addView(clone(_defaultText), _params);
		_rightData.addView(clone(_defaultText), _params);
		_midData.addView(clone(_defaultText), _params);
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
		float consumption = _dataVariables._history.getAllConsumptionFromMonth(_leftTime.month);
		((TextView)_leftData.getChildAt(1)).setText(String.valueOf(consumption));
		_leftMonth.setText(MONTH_DAY[_leftTime.month] + "\n" + _leftTime.year);
	}
	
	private void refreshRightSide(){
		float consumption = _dataVariables._history.getAllConsumptionFromMonth(_rightTime.month);
		((TextView)_rightData.getChildAt(1)).setText(String.valueOf(consumption));
		_rightMonth.setText(MONTH_DAY[_rightTime.month] + "\n" + _rightTime.year);
	}
	
	private void refreshMidSide(){
		float leftConsumptionValue = Float.parseFloat((String) ((TextView)_leftData.getChildAt(1)).getText());
		float rightConsumptionValue = Float.parseFloat((String) ((TextView)_rightData.getChildAt(1)).getText());
		((TextView)_midData.getChildAt(1)).setText(String.valueOf(leftConsumptionValue - rightConsumptionValue));
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
	
	private TextView clone(TextView textView){
		TextView clone = new TextView(this);
		clone.setGravity(textView.getGravity());
		clone.setLayoutParams(_params);
		return clone;
	}
	
}

