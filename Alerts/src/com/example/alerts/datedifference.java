package com.example.alerts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.net.ParseException;
import android.os.Bundle;

public class datedifference extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm);
		datedifference obj = new datedifference();
	      SimpleDateFormat simpleDateFormat = 
	                new SimpleDateFormat("dd/M/yyyy");
	      String date = simpleDateFormat.format(Calendar.getInstance().getTime());
	      System.out.print("current date =="+date);
	      try {

	        Date date1 = simpleDateFormat.parse(date);
	        Date date2 = simpleDateFormat.parse("15/9/2014");

	        obj.printDifference(date1, date2);

	      } catch (ParseException e) {
	        e.printStackTrace();
	      } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400
    public void printDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
            "%d days, %d hours, %d minutes, %d seconds%n", 
            elapsedDays,
            elapsedHours, elapsedMinutes, elapsedSeconds);

    }
    
}
