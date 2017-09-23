package com.example.alerts;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class birthday extends Activity implements OnClickListener{
	
	private static final int PICK_FROM_FILE = 0;
	String imageNameForSDCard="";
	ImageView iv1,iv2;
	EditText e1,e2,e3,e4;
	Button b1;
	int h = 0;
	int m = 0;
	
	final static int RQS_1 = 1;
	
	protected static final int PICK_FROM_GALLERY = 0;

	protected static final int PICK_FROM_CAMERA = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.birthday);
		setTitle("Fill Birthday Info");
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#007FFF")));
		bar.setDisplayHomeAsUpEnabled(true);
		
		iv1=(ImageView)findViewById(R.id.imageView1);
		iv2=(ImageView)findViewById(R.id.imageView2);
		iv2.setOnClickListener(this);
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e2.setOnClickListener(this);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		e4.setOnClickListener(this);
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(this);
		
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
		int mYear = 0;
		int mMonth = 0;
		int mDay = 0;
		
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imageView2:

			CharSequence colors[] = new CharSequence[] {"Take picture", "Choose from gallery"};
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Choose a type");
			builder.setItems(colors, new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        // the user clicked on colors[which]
			    		
			    	if(which == 0){
			    		 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			    		 Toast.makeText(getApplicationContext(), "f"+MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString(), Toast.LENGTH_LONG).show();
			    		 intent.putExtra(MediaStore.EXTRA_OUTPUT,
			    		 MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
			    		 Toast.makeText(getApplicationContext(), "fdzsg"+MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString(), Toast.LENGTH_LONG).show();
			    		 // ******** code for crop image
			    		 if("//media/external/images/media"==MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()){
			    			 
			    		 }
			    		 else
			    		 {
			    		 intent.putExtra("crop", "true");
			    		 intent.putExtra("aspectX", 0);
			    		 intent.putExtra("aspectY", 0);
			    		 intent.putExtra("outputX", 200);
			    		 intent.putExtra("outputY", 150);

			    		 try {

			    		 intent.putExtra("return-data", true);
			    		 startActivityForResult(intent, PICK_FROM_CAMERA);

			    		 } catch (ActivityNotFoundException e) {
			    		 // Do nothing for now
			    		 }
			    		 }
			    	}
			
			    	else if(which == 1){
			    		 Intent intent = new Intent();
			    		// call android default gallery
			    		intent.setType("image/*");
			    		intent.setAction(Intent.ACTION_GET_CONTENT);
			    		// ******** code for crop image
			    		intent.putExtra("crop", "true");
			    		intent.putExtra("aspectX", 0);
			    		intent.putExtra("aspectY", 0);
			    		intent.putExtra("outputX", 2000);
			    		intent.putExtra("outputY", 1500);

			    		try {

			    		intent.putExtra("return-data", true);
			    		startActivityForResult(Intent.createChooser(intent,
			    		"Complete action using"), PICK_FROM_GALLERY);

			    		} catch (ActivityNotFoundException e) {
			    		// Do nothing for now
			    		}

			    	

			    	}
			    	
			    }

				
			});
			builder.show();	
			break;

		case R.id.button1:
			//	dbcon.insertData(e1.toString());
				//System.out.println("DATA INSERTED");
				//Toast.makeText(getApplicationContext(), "Data Inserted", LENGTH_LONG);
				
				System.out.println("hellooo");
				
				if(e1.getText().toString()!="[A-Za-z]" || e1.getText().toString()==" ")
				{
					e1.setError("Invalid Name");
				}
			   
				
				hotornot info=new hotornot(birthday.this);
				info.open();
				String my1=e1.getText().toString();
				String my2=e2.getText().toString();
				String my3=e3.getText().toString();
				
				info.insertbirthday(my1, my2,my3,imageNameForSDCard);
			//	String entries=info.getallmsg();
			//	System.out.println("ITsss:"+entries);
			
				info.close();
				
				Intent obj =new Intent(this,MainActivity.class);
				startActivity(obj);
				
				System.out.println("hi");
			
				//t1.setText("Your Data is saved");
				e1.setText("");
				e2.setText("");
				e3.setText("");
				
		//		Intent obj=new Intent(profile1.this,CreateFragment.class);
		//		startActivity(obj);
				
			

		
				    Calendar current = Calendar.getInstance();
				    
				    Calendar cal = Calendar.getInstance();
				    
				    
				    cal.set(2015, 02, 20, 23, 53, 00);
			/*	    cal.add(Calendar.DATE, 20);
				    cal.add(Calendar.MONTH, 02);
				    cal.add(Calendar.YEAR, 2015);
				    cal.add(Calendar.HOUR, 12);
				    cal.add(Calendar.MINUTE, 05);
				*/    
				    
				    
	/*			    Calendar cal  = Calendar.getInstance();
				    
			try {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			   
				cal.setTime(df.parse(e2.getText().toString()+" "+e4.getText().toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		*/		    
				    
			//	    Toast.makeText(getApplicationContext(), ""+cal, Toast.LENGTH_LONG).show();
				  
				    
				   // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+mYear);
				    //Toast.makeText(this, "aaaaaaa"+mYear, Toast.LENGTH_LONG).show();
				//    Toast.makeText(getApplicationContext(), mMonth, Toast.LENGTH_LONG).show();
				  //  Toast.makeText(getApplicationContext(), mDay, Toast.LENGTH_LONG).show();
				   // Toast.makeText(getApplicationContext(), h, Toast.LENGTH_LONG).show();
				    //Toast.makeText(getApplicationContext(), m, Toast.LENGTH_LONG).show();
				    
				    
				    if(cal.compareTo(current) <= 0){
				     //The set Date/Time already passed
				        Toast.makeText(getApplicationContext(), 
				          "Invalid Date/Time", 
				          Toast.LENGTH_LONG).show();
				    }else{
				    	  Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
						  PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
						  AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
						  alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent); 
				    }

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
				                e2.setText(dayOfMonth + "-"
				                        + (monthOfYear + 1) + "-" + year);
				 
				            }
				        }, mYear, mMonth, mDay);
				dpd.show();
			
				
				
	                
				break;
				
			case R.id.editText4:
			
			
				
				 Calendar mcurrentTime = Calendar.getInstance();
	                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
	                int minute = mcurrentTime.get(Calendar.MINUTE);
	                TimePickerDialog mTimePicker;
	                mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
	                    @Override
	                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
	                        e4.setText( selectedHour + ":" + selectedMinute);
	                       h=selectedHour;
	               		   m=selectedMinute;
	                       
	                       
	                        
	                    }
	                }, hour, minute, false);
	                mTimePicker.setTitle("Select Time");
	                mTimePicker.show();
	                
	                
				break;
			default:
				break;
		}
	}
	
//////////code for profile pic
	
protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	 if (requestCode == PICK_FROM_CAMERA) {
	 Bundle extras = data.getExtras();
	 if (extras != null) {
		 Toast.makeText(getBaseContext(), "Selected"+extras, Toast.LENGTH_LONG).show();
	 Bitmap photo = extras.getParcelable("data");
	  if(photo==null)
	  {
		  
	  }
	  else
	  {
	 iv1.setImageBitmap(photo);
	  }
	 }
	 }

	 if (requestCode == PICK_FROM_GALLERY) {
	 Bundle extras2 = data.getExtras();
	 if (extras2 != null) {
	 Bitmap photo = extras2.getParcelable("data");
	 
	 iv1.setImageBitmap(photo);
	 //////////////////////////////////////
	 BitmapDrawable btmpDr = (BitmapDrawable) iv1.getDrawable();
	 Bitmap bmp = btmpDr.getBitmap();

	 /*File sdCardDirectory = Environment.getExternalStorageDirectory();*/
	 try
	 {
	     File sdCardDirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "Alerts");
	     sdCardDirectory.mkdirs();

	     imageNameForSDCard = "image_" + System.currentTimeMillis() + ".jpg";

	     File image = new File(sdCardDirectory, imageNameForSDCard);
	     FileOutputStream outStream;

	     outStream = new FileOutputStream(image);
	     bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream); 
	     /* 100 to keep full quality of the image */
	     outStream.flush();
	     outStream.close();



	     //Refreshing SD card
	     sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
	 }
	 catch (Exception e) 
	 {
	     e.printStackTrace();
	     Toast.makeText(getBaseContext(), "Image could not be saved : Please ensure you have SD card installed " +
	                                                                             "properly", Toast.LENGTH_LONG).show();
	 }
	 
	 
	 }
	 }
	 
}



}


