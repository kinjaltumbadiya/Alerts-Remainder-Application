package com.example.alerts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
 


public class setalarm extends Activity {

	 final static int RQS_1 = 1;
   private PendingIntent pendingIntent;
	public void settt()
	{
		
		
		System.out.println("okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		Calendar cal = Calendar.getInstance();
	    cal.set(2015, 
	    02, 
	    14,20,28,00);
	    setAlarm(cal);
	    
	}
	
	private void setAlarm(Calendar targetCal)
	{

		
  	 // info.setText("\n\n***\n"
  	  //  + "Alarm is set@ " + targetCal.getTime() + "\n"
  	   // + "***\n");9
  	  
		Intent myIntent = new Intent(setalarm.this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(setalarm.this, 0, myIntent,0);
       
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, targetCal.getTimeInMillis(), pendingIntent);
  	 }

}
