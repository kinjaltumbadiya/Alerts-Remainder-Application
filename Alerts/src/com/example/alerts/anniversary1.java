package com.example.alerts;


import java.util.Calendar;




import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.SweepGradient;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class anniversary1 extends Activity implements OnClickListener{

	Button b1;
	EditText e4;
	String path;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anniversary1);
		
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(this);
		
		e4=(EditText)findViewById(R.id.editText4);
		e4.setOnClickListener(this);
		
		path = getIntent().getExtras().getString("path");
		e4.setText(path);
		
		setTitle("Fill Anniversary Info");
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
	            Intent intent = new Intent(this, anniversary.class);
	            NavUtils.navigateUpTo(this, intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		int mYear;
		int mMonth;
		int mDay;
		switch (v.getId()) {
		case R.id.button1:
			System.out.println("hellooo");
			
			EditText et1=(EditText)findViewById(R.id.editText1);
			EditText et2=(EditText)findViewById(R.id.editText2);
			EditText et3=(EditText)findViewById(R.id.editText3);
			EditText et4=(EditText)findViewById(R.id.editText4);
			
			
			hotornot info=new hotornot(anniversary1.this);
			info.open();
			String my1=et1.getText().toString();
			String my2=et2.getText().toString();
			String my3=et3.getText().toString();
			String my4=et4.getText().toString();
			info.insertanniversary(my1,my2,my3,my4,path);
			//String entries=info.getallmsg();
			//System.out.println("ITsss:"+entries);
		
			info.close();
			
			System.out.println("hi");
			
			//Intent a= new Intent(anniversary1.this,ReadFragment.class);
			//startActivity(a);
		
			break;
		
		case R.id.editText4:
			
			final Calendar c = Calendar.getInstance();
			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);
			 
			DatePickerDialog dpd = new DatePickerDialog(this,
			        new DatePickerDialog.OnDateSetListener() {
			 
			            @Override
			            public void onDateSet(DatePicker view, int year,
			                    int monthOfYear, int dayOfMonth) {
			                e4.setText(dayOfMonth + "-"
			                        + (monthOfYear + 1) + "-" + year);
			 
			            }
			        }, mYear, mMonth, mDay);
			dpd.show();
			
			break;
			
		default:
			break;
		}
		
	}

	
	
}
