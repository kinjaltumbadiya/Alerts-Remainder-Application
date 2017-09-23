package com.example.alerts;


import java.util.Calendar;




import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Custom extends Activity implements OnClickListener{

	Button b1,b2,b3;
	AlarmManager am;
	NotificationManager nm;
	
	
	
	LinearLayout layoutOfPopup;
	PopupWindow popupMessage;
	Button popupButton, insidePopupButton;
	TextView popupText;
	EditText et1,et2;

	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom);
		
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(this);
	
		et1=(EditText)findViewById(R.id.editText1);
		et2=(EditText)findViewById(R.id.editText2);
		et2.setOnClickListener(this);
		
		setTitle("Fill Info");
	//	setTitleColor(R.style.abc);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#007FFF")));
        bar.setDisplayHomeAsUpEnabled(true);
        
       
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    // Handle item selection
	    switch (item.getItemId()) 
	    {
	        case android.R.id.home:
	            Intent intent = new Intent(this, MainActivity.class);
	            NavUtils.navigateUpTo(this, intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}


	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		NotificationManager mNotificationManager;
		int mYear;
		int mMonth;
		int mDay;
		switch (v.getId()) {
		case R.id.button1:
			
			
			
			
			hotornot info=new hotornot(Custom.this);
			info.open();
			String my1=et1.getText().toString();
			String my2=et2.getText().toString();
		
			info.insertcustom(my1,my2);
			
			System.out.println("Custom na data");
			Cursor entries=info.getallcustommsg();
		
			info.close();
			
		
			
			break;

		
		
		case R.id.editText2:
			final Calendar c = Calendar.getInstance();
			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);
			 
			DatePickerDialog dpd = new DatePickerDialog(this,
			        new DatePickerDialog.OnDateSetListener() {
			 
			            @Override
			            public void onDateSet(DatePicker view, int year,
			                    int monthOfYear, int dayOfMonth) {
			                et2.setText(dayOfMonth + "-"
			                        + (monthOfYear + 1) + "-" + year);
			 
			            }
			        }, mYear, mMonth, mDay);
			dpd.show();
		
		default:
			
			break;
		}
		
	}
	
	private PopupWindow pwindo;
	
	private void initiatePopupWindow() {
		try {
		// We need to get the instance of the LayoutInflater
		LayoutInflater inflater = (LayoutInflater) Custom.this
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.popup,
		(ViewGroup) findViewById(R.id.text));
		pwindo = new PopupWindow(layout, 400, 470, true);
		pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
		

	//	btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
	//	btnClosePopup.setOnClickListener(cancel_button_click_listener);

		} catch (Exception e) {
		e.printStackTrace();
		}
		}

	

	
}
