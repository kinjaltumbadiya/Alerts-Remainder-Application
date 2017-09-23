package com.example.alerts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;



import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.opengl.ETC1Util;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class profile2 extends Activity implements OnClickListener{

	private static final int PICK_FROM_FILE = 0;
	String imageNameForSDCard="";
	ImageView iv;
	EditText et2,et3;
	Button b1,b2;
	TextView tv;
	private static final int PICK_CONTACT = 3;

	protected static final int PICK_FROM_GALLERY = 0;

	protected static final int PICK_FROM_CAMERA = 0;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.profile);
		iv=(ImageView)findViewById(R.id.iv1);
	//	et2=(EditText)findViewById(R.id.et2);
	//	et3=(EditText)findViewById(R.id.et3);
		iv.setOnClickListener(this);
		b1=(Button)findViewById(R.id.btnext);
		b1.setOnClickListener(this);
		b2=(Button)findViewById(R.id.btskip);
		b2.setOnClickListener(this);
	//	et2.setOnClickListener(this);
	//	et2.setInputType(InputType.TYPE_NULL);
	//	et3.setOnClickListener(this);
		setTitle("Choose Profile Picture");
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#007FFF")));
		bar.setDisplayHomeAsUpEnabled(true);
		
	//	tv =(TextView)findViewById(R.id.textView1);
		
		
		//tv.setText(message);
		
		
		hotornot info=new hotornot(this);
		info.open();
		
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
	
	
	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		int mYear;
		int mMonth;
		int mDay;
		switch (v.getId()) {
		case R.id.iv1:
			
			
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
			    		intent.putExtra("outputX", 200);
			    		intent.putExtra("outputY", 150);

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
			
			
		case R.id.btnext:
			
			if(imageNameForSDCard==""){
				Toast.makeText(getBaseContext(), "Please select profile pic and then click next otherwise click skip", Toast.LENGTH_LONG).show();
			}
			else
			{
			Intent obj = new Intent(profile2.this,profile1.class);
			obj.putExtra("path",imageNameForSDCard);
			startActivity(obj);
			}
	        break;
	        
		case R.id.btskip:
			Intent obj1 = new Intent(profile2.this,profile1.class);
			imageNameForSDCard = "1";
			obj1.putExtra("path",imageNameForSDCard);
			startActivity(obj1);
			
			 
		    
	        break;
	        

	        
			
			
		/*case R.id.et2:
		

			CharSequence colors[] = new CharSequence[] {"Choose from contacts", "Write manually"};
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Choose a type");
			builder.setItems(colors, new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        // the user clicked on colors[which]
			    		
			    	if(which == 0){
			    		et2.setInputType(InputType.TYPE_NULL);
			    	
			    		
			    		
			    		 Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			    	     startActivityForResult(intent, PICK_CONTACT);
			    		Toast.makeText(getBaseContext(), "Selected Storage Path : Phone Memory", Toast.LENGTH_SHORT).show();

		                
			    	}
			    	else if(which == 1){
			    		et2.setInputType(InputType.TYPE_CLASS_PHONE);
			    	}
			    	
			    }

				
			});
			builder.show();	
			
			
			
			break;
			
			
			
			
		case R.id.et3:
			
			final Calendar c = Calendar.getInstance();
			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);
			 
			DatePickerDialog dpd = new DatePickerDialog(this,
			        new DatePickerDialog.OnDateSetListener() {
			 
			            @Override
			            public void onDateSet(DatePicker view, int year,
			                    int monthOfYear, int dayOfMonth) {
			                
							et3.setText(dayOfMonth + "-"
			                        + (monthOfYear + 1) + "-" + year);
			 
			            }

						
			        }, mYear, mMonth, mDay);
			dpd.show();
			break;
			*/
			

			
			
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
		 iv.setImageBitmap(photo);
		  }
		 }
		 }

		 if (requestCode == PICK_FROM_GALLERY) {
		 Bundle extras2 = data.getExtras();
		 if (extras2 != null) {
		 Bitmap photo = extras2.getParcelable("data");
		 
		 iv.setImageBitmap(photo);
		 //////////////////////////////////////
		 BitmapDrawable btmpDr = (BitmapDrawable) iv.getDrawable();
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
	
	
	/*
	//code for profile pic
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode != RESULT_OK) return;

	    Bitmap bitmap   = null;
	    String path     = "";

	    Uri mImageCaptureUri = null;
		if (requestCode == PICK_FROM_FILE) {
	        mImageCaptureUri = data.getData(); 
	        path = getRealPathFromURI(mImageCaptureUri); //from Gallery
	        Log.v("Path", ""+path);

	        if (path == null)
	            path = mImageCaptureUri.getPath(); //from File Manager
	        Log.v("Path", ""+path);

	        if (path != null) 
	            bitmap  = BitmapFactory.decodeFile(path);
	    } else {
	        path    = mImageCaptureUri.getPath();
	        Log.v("Path", ""+path);
	        bitmap  = BitmapFactory.decodeFile(path);
	    }

	    ImageView profile_pic = null;
		iv.setImageBitmap(bitmap);     
	}

	public String getRealPathFromURI(Uri contentUri) {
	    String [] proj      = {MediaStore.Images.Media.DATA};
	    Cursor cursor       = managedQuery( contentUri, proj, null, null,null);

	    if (cursor == null) return null;

	    int column_index    = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

	    cursor.moveToFirst();

	    return cursor.getString(column_index);
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	   Rect rect = new Rect();
	   View rootView = null;
	rootView.getHitRect(rect);
	   if (!rect.contains((int)event.getX(), (int)event.getY())){
	       setFinishOnTouchOutside(false); 
	          return true;
	   }
	      return false;       
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {

	        finish();
	    }
	    return true;
	}
	}
	*/

