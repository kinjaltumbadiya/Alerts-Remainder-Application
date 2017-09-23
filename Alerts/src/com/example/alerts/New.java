package com.example.alerts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class New extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SharedPreferences settings = getSharedPreferences("MyPrefs", 0);

		if (settings.getBoolean("is_first_time", true)) {
		    //the app is being launched for first time, do something        
		    Log.d("TAG", "First time");
		    
	     	Intent obj=new Intent(New.this,SettingsActivity.class);
        	startActivity(obj);
		    
		    // first time task

		    // record the fact that the app has been started at least once
		    settings.edit().putBoolean("is_first_time", false).commit(); 
		}
		 else
		{
		    //second time launch..
			 Log.d("TAG", "Second time");
			 Intent obj=new Intent(New.this,MainActivity.class);
	        	startActivity(obj);
		} 
	}
	
}
