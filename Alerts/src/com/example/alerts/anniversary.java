package com.example.alerts;

import java.io.File;
import java.io.FileOutputStream;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
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
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class anniversary extends Activity implements OnClickListener{

	Button b1,b2;
	ImageView iv;
	String imageNameForSDCard;
	
	private static final int PICK_CONTACT = 3;

	protected static final int PICK_FROM_GALLERY = 0;

	protected static final int PICK_FROM_CAMERA = 0;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anniversary);
		b1=(Button)findViewById(R.id.btnext);
		b1.setOnClickListener(this);
		b2=(Button)findViewById(R.id.btskip);
		b2.setOnClickListener(this);
		
		iv=(ImageView)findViewById(R.id.iv1);
		//	et2=(EditText)findViewById(R.id.et2);
		//	et3=(EditText)findViewById(R.id.et3);
			iv.setOnClickListener(this);
		setTitle("Choose Profile Pic");
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
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		 if (requestCode == PICK_FROM_CAMERA) {
		 Bundle extras = data.getExtras();
		 if (extras != null) {
		 Bitmap photo = extras.getParcelable("data");
		 iv.setImageBitmap(photo);

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
	 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.btnext:
			Intent obj = new Intent(anniversary.this,anniversary1.class);
			obj.putExtra("path",imageNameForSDCard);
			startActivity(obj);
			
			break;
			
		case R.id.btskip:
			Intent obj1 = new Intent(anniversary.this,anniversary1.class);
			imageNameForSDCard = "1";
			obj1.putExtra("path",imageNameForSDCard);
			startActivity(obj1);
	        break;
	        
			
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

			    		 intent.putExtra(MediaStore.EXTRA_OUTPUT,
			    		 MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
			    		 // ******** code for crop image
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
			    		startActivityForResult(Intent.createChooser(intent,"Complete action using"), PICK_FROM_GALLERY);

			    		} catch (ActivityNotFoundException e) {
			    		// Do nothing for now
			    		}

			    	

			    	}
			    	
			    }

				
			});
			builder.show();	
		
			break;

		default:
			break;
		}
		
	}
	
}
