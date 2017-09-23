package com.example.alerts;


import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.NavUtils;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class profile1 extends Activity implements OnClickListener,OnFocusChangeListener{

	private static final int PICK_CONTACT = 0;
	protected static final int CONTACT_ACTIVITY = 0;
	EditText e1,e2,e3;
	Button b1,b2;
	ImageView i1;
	String path;
	TextView t1;
	int mYear;
	int mMonth;
	int mDay;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile1);
		setTitle("Fill Birthday Info");
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#007FFF")));
		e1=(EditText)findViewById(R.id.etmb);
		e2=(EditText)findViewById(R.id.editText1);
		e3=(EditText)findViewById(R.id.editText2);
		t1=(TextView)findViewById(R.id.txtsave);
		e3.setOnClickListener(this);
		i1=(ImageView)findViewById(R.id.ivct);
		i1.setOnClickListener(this);
		e1.setOnClickListener(this);
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(this);
		b2=(Button)findViewById(R.id.button2);
		b2.setOnClickListener(this);
		
		bar.setDisplayHomeAsUpEnabled(true);
		
		path = getIntent().getExtras().getString("path");
	//	e1.setText(path);
		
		e2.setOnFocusChangeListener(this);
		//Bundle bundle = getIntent().getExtras();
		//String message = bundle.getString("message");
		if(getIntent().getExtras() == null)
		{
			Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_LONG).show();
		}
		else
		{
			Bundle bundle = getIntent().getExtras();
			Toast.makeText(getApplicationContext(), bundle.getString("message"), Toast.LENGTH_LONG).show();
			
	hotornot info=new hotornot(this);
			info.open();
			String k=bundle.getString("message");
			if(k==null){
				
			}
			else
			{
			Cursor c =info.Get_birthday_info(Integer.parseInt(bundle.getString("message")));
			if(c==null)                                                 
				
			{
				
			}
			else
			{
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
			{
				//result=result + c.getString(irow) + "  " + c.getString(iname) +"  " + c.getString(ihotness)+" "+c.getString(ipath) +"\n";
				e2.setText(c.getString(1));
				e3.setText(c.getString(2));
				e1.setText(c.getString(0));
				
			}
			}
			c.close();
			info.close();
			
		}
		}
		
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    // Handle item selection
	    switch (item.getItemId()) 
	    {
	        case android.R.id.home:
	            Intent intent = new Intent(this, profile2.class);
	            NavUtils.navigateUpTo(this, intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	@Override
	public void onClick(final View v) {
		// TODO Auto-generated method stub
		
		
		switch (v.getId()) {
		case R.id.ivct:
			
			    		
			            Intent i = new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
			            startActivityForResult(i,1);
			            
			    	/*	Intent intent = new Intent(Intent.ACTION_PICK);
				        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
				        startActivityForResult(intent, PICK_CONTACT);*/
			    		
			    		//Uri uri = Uri.parse("content://contacts/people");
						//Intent contacts_intent = new Intent(Intent.ACTION_PICK, uri);
						//startActivityForResult(contacts_intent, CONTACT_ACTIVITY);

			    	
			    	
			    	break;
			
		case R.id.button1:
		//	dbcon.insertData(e1.toString());
			//System.out.println("DATA INSERTED");
			//Toast.makeText(getApplicationContext(), "Data Inserted", LENGTH_LONG);
			
			System.out.println("hellooo");
			
			
			
		   
			
			hotornot info=new hotornot(profile1.this);
			info.open();
			String my1=e1.getText().toString();
			String my2=e2.getText().toString();
			String my3=e3.getText().toString();
			
			info.insertbirthday(my1, my2,my3,path);
		//	String entries=info.getallmsg();
		//	System.out.println("ITsss:"+entries);
		
			info.close();
			
			System.out.println("hi");
		
			t1.setText("Your Data is saved");
			e1.setText("");
			e2.setText("");
			e3.setText("");
			
	//		Intent obj=new Intent(profile1.this,CreateFragment.class);
	//		startActivity(obj);
			break;
			
		case R.id.button2:
			//	dbcon.insertData(e1.toString());
				//System.out.println("DATA INSERTED");
				//Toast.makeText(getApplicationContext(), "Data Inserted", LENGTH_LONG);
			Toast.makeText(getApplicationContext(), "hjbhb", Toast.LENGTH_LONG);
				System.out.println("hellooo");
				
				
				
			   
				Bundle bundle = getIntent().getExtras();
				hotornot info1=new hotornot(profile1.this);
				info1.open();
				String m1=e1.getText().toString();
				String m2=e2.getText().toString();
				String m3=e3.getText().toString();
				
				Toast.makeText(getApplicationContext(), "hjbhb"+m1+m2+m3, Toast.LENGTH_LONG);
				
				info1.updatebirthday(m1, m2,m3,Integer.parseInt(bundle.getString("message")));
			//	String entries=info.getallmsg();
			//	System.out.println("ITsss:"+entries);
			
				info1.close();
				
				System.out.println("hi");
			
				t1.setText("Your Data is updated");
				e1.setText("");
				e2.setText("");
				e3.setText("");
				
		//		Intent obj=new Intent(profile1.this,CreateFragment.class);
		//		startActivity(obj);
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
			                e3.setText(dayOfMonth + "-"
			                        + (monthOfYear + 1) + "-" + year);
			 
			            }
			        }, mYear, mMonth, mDay);
			dpd.show();
			
			break;
		default:
			break;
		}
		
	}

	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
	    if(resultCode == RESULT_OK){        
	        getContactData(data);
	        }
	    }

	    public void getContactData(Intent data){

	    ContentResolver cr = getContentResolver();

	    Uri contactData = data.getData();
	    Log.v("Contact", contactData.toString());
	    Cursor c = managedQuery(contactData,null,null,null,null);

	    if(c.moveToFirst()){
	            String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
	        Log.v("Contact", "ID: " + id.toString());
	            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	        Log.v("Contact", "Name: " + name.toString());

	        if (Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
	                Cursor pCur = cr.query(Phone.CONTENT_URI,null,Phone.CONTACT_ID +" = ?", new String[]{id}, null);

	                while(pCur.moveToNext()){
	                	   Object phone = pCur.getString(pCur.getColumnIndex(Phone.NUMBER));
	                	   e1.setText((CharSequence) phone);
	                	   System.out.println("okkkk");
	                	   
	                	   Log.v("getting phone number", "Phone Number: " + phone);
	                	}
	      }

	    }

	}
	    
	    public void hideTheKeyboard(Context context, EditText editText){
	        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
	        imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
	    }


		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			
			switch (v.getId()) {
			case R.id.editText1:
				
				if(e2.length()>15){
					e2.setError("Name should be max 15 characters");
				}
				
				break;

			

			default:
				break;
			}
			
					
				
			}
			
			
		
	
	/*@SuppressWarnings("deprecation")
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
          case (PICK_CONTACT) :
            if (resultCode == Activity.RESULT_OK) {
                Uri contactData = data.getData();
                  Cursor c =  getContentResolver().query(contactData, null, null, null, null);
                   if (c.moveToFirst()) {
                    @SuppressWarnings("deprecation")
					//String name = c.getString(c.getColumnIndexOrThrow(People.NAME));  
                    String number = c.getString(c.getColumnIndexOrThrow(People.NUMBER));
                   // TextView perrsonname;
                   // perrsonname.setText(name);
                    Toast.makeText(this,"ok", Toast.LENGTH_LONG).show();
                   }
             }
           break;
        }

    }*/
	
	/*public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode){
		case(CONTACT_ACTIVITY): {
			if(resultCode == Activity.RESULT_OK) {
				Uri my_uri = data.getData();
				//Uri.decode(my_uri.toString());
				
				//want to get the number an put it in the editText
				e1.setText(Uri.decode(my_uri.toString()));
			}
		break;
		}
			
		}
	}*/
	
	
}
