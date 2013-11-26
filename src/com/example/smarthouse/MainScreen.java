package com.example.smarthouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainScreen extends FragmentActivity {

	private TextClock _textClock;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
        _textClock = new TextClock(this);
		Button button;
        button = (Button) findViewById(R.id.backTest);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	finish();
            }
        });
        button = (Button) findViewById(R.id.historico);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
			   //Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
			   //startActivity(i);
            }
        });
        button = (Button) findViewById(R.id.Cozinha);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
			   //Intent i = new Intent(getApplicationContext(), CozinhaActivity.class);
			   //startActivity(i);
            }
        });
        button = (Button) findViewById(R.id.Sala);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
			   Intent i = new Intent(getApplicationContext(), SalaActivity.class);
			   startActivity(i);
            }
        });

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onNotfClick(View v) {
        notfDialog dialog = new notfDialog();
        dialog.show(getSupportFragmentManager(), "notfDialog");
	}
}
