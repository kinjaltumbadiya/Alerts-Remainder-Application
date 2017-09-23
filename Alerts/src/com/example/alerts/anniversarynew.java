package com.example.alerts;



import java.io.File;
import java.io.FileOutputStream;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class anniversarynew extends Activity implements OnClickListener{
	
	private static final int PICK_FROM_FILE = 0;
	String imageNameForSDCard="";
	ImageView iv1,iv2;
	
	protected static final int PICK_FROM_GALLERY = 0;

	protected static final int PICK_FROM_CAMERA = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anniversarynew);
		setTitle("Fill Anniversary Info");
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#007FFF")));
		
		iv1=(ImageView)findViewById(R.id.imageView1);
		iv2=(ImageView)findViewById(R.id.imageView2);
		iv2.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
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


