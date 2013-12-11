package com.example.smarthouse.divisions;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.VideoView;

import com.example.smarthouse.R;

public class MovieActivity extends Activity {

	VideoView videoView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie);
	    videoView = (VideoView)findViewById(R.id.videoView1);        
	    videoView.setVideoPath("/sdcard/big_buck_bunny.mp4");
	    videoView.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movie, menu);
		return true;
	}

}
