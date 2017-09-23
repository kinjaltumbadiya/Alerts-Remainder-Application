package com.example.alerts;


import java.util.Date;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.widget.ViewFlipper;

public class splash extends Activity {
	ViewFlipper flipper;
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.splash);
		
	//	setalarm obj=new setalarm();
		//Date d=new Date("2015-01-29");
		//obj.settt(d);
		
	    flipper= (ViewFlipper) findViewById(R.id.viewFlipper1);
	    flipper.setFlipInterval(200);
	    flipper.startFlipping();
		
		
		
		Thread timer = new Thread()
		{
			public void run() {
				try
				{
					sleep(3000);
					
					
				}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				finally{
					Intent openstartingpoint = new Intent(splash.this,MainActivity.class);
					startActivity(openstartingpoint);
				}
				
				
			}
			
		};
		timer.start();

		}
@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		finish();
	}


}

