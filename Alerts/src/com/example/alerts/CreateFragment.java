package com.example.alerts;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.ads.v;







import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.DateSorter;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CreateFragment extends Fragment implements OnItemClickListener,OnItemLongClickListener{

	TextView mSearchText;
	
	
	ListView listView;
    List<RowItem> rowItems;
 
    
    
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    
		View rootView = inflater.inflate(R.layout.fragment_create, container, false);
		
		try
		{
	
			final ListView l=(ListView) rootView.findViewById(R.id.listView1);
		 // Defined Array values to show in ListView
     
      hotornot info=new hotornot(getActivity());
		info.open();
		Cursor cursor = info.getallmsgok();
		
		List list = new ArrayList();
		list.add("abc");
		 rowItems = new ArrayList<RowItem>();
		 int i=1;
	    for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
		{
	    	list.add(cursor.getString(0) + "\n " + cursor.getString(1)+ "\n" + cursor.getString(2));
//			result=result + c.getString(irow) + "  " + c.getString(iname) +"  " + c.getString(ihotness) +"\n";
	    	RowItem item = new RowItem(i, cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4));
	    Toast.makeText(getActivity(),cursor.getString(4) , Toast.LENGTH_LONG).show();
	    
	    	rowItems.add(item);
            i++;
	
		}
	    cursor.close();
	    info.close();
	    
		//ListAdapter k=new SimpleAdapter(getActivity(),val,R.layout.list,new String[]{"TaskId","heading","status"},new int[]{R.id.txt,R.id.cur,R.id.textView2});
     // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, list);
	    CustomBaseAdapter adapter = new CustomBaseAdapter(getActivity(), rowItems);
      l.setAdapter(adapter);
   l.setOnItemClickListener(this);
   l.setOnItemLongClickListener(this);
    //  Toast.makeText(getActivity(), "No Task to Display", Toast.LENGTH_SHORT).show();
     // l.setAdapter(adapter);
        
		}catch(Exception e)
		{
			System.out.println(e);
		}
	    return rootView;
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);

		   SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		   SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() 
	        {
			   @SuppressLint("NewApi")
				public boolean onQueryTextChange(String newText) {
			        newText = newText.isEmpty() ? "" : "Query so far: " + newText;
			      //  ((Filterable) adapter).getFilter().filter(newText);
			        mSearchText.setText(newText);
			        mSearchText.setTextColor(Color.GREEN);
			        return true;
			    }
			 
			    public boolean      onQueryTextSubmit      (String query) {
			        //Toast.makeText(this, "Searching for: " + query + "...", Toast.LENGTH_SHORT).show();
			    //	 ((Filterable) adapter).getFilter().filter(query);
			     //   mSearchText.setText("Searching for: " + query + "...");
			        mSearchText.setTextColor(Color.RED);
			        return true;
			    }

	        };
		   
		return true;
	}
	
	
	
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Toast toast = Toast.makeText(getActivity(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        
    	
    	
        
        
        CharSequence colors[] = new CharSequence[] {"Make Call", "Message"};
        
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	
	//	builder.setTitle("Select Option");
		builder.setItems(colors,new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        // the user clicked on colors[which]
		    		
		    	if(which == 0){
		    		Intent intent = new Intent(Intent.ACTION_DIAL);
		    		intent.setData(Uri.parse("tel:0123456789"));
		    		startActivity(intent); 						
		   
		    	//	Intent a= new Intent(MainActivity.this,profile2.class);
		    	//	a.putExtra("message", "abc");
				//	startActivity(a);
		    	}
		    	else if (which == 1) {
		    		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		    		smsIntent.setType("vnd.android-dir/mms-sms");
		    		smsIntent.putExtra("address", "12125551212");
		    		smsIntent.putExtra("sms_body","Body of Message");
		    		startActivity(smsIntent);


		    		
		    	//	Intent a= new Intent(MainActivity.this,anniversary.class);
				//	startActivity(a);
				}
		    	else if (which == 2) {
		    //		Intent a= new Intent(MainActivity.this,Custom.class);
				//	startActivity(a);
				}
		    	
		    }
		});
		builder.show();
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1,  final int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
CharSequence colors[] = new CharSequence[] {"Edit Record", "Delete Record"};
        
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	
	//	builder.setTitle("Select Option");
		builder.setItems(colors,new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        // the user clicked on colors[which]
		    		
		    	if(which == 0){
		    	//	Intent intent = new Intent(Intent.ACTION_DIAL);
		    		//intent.setData(Uri.parse("tel:0123456789"));
		    		//startActivity(intent); 						
		    		
		    		
		    		
		    		Intent a= new Intent(getActivity(),profile1.class);
		    		a.putExtra("message", rowItems.get(arg2).getid());
		    		Toast.makeText(getActivity(), rowItems.get(arg2).getid(), Toast.LENGTH_LONG).show();
					
					startActivity(a);
					
		    	}
		    	else if (which == 1) {
		    	//	Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		    		//smsIntent.setType("vnd.android-dir/mms-sms");
		    		//smsIntent.putExtra("address", "12125551212");
		    	//	smsIntent.putExtra("sms_body","Body of Message");
		    		//startActivity(smsIntent);
		    		AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
				    adb.setTitle("Delete?");
				    adb.setMessage("Are you sure you want to delete ");
				   
				    adb.setNegativeButton("Cancel", null);
				    adb.setPositiveButton("Ok",
					    new AlertDialog.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
							int which) {
						    // MyDataObject.remove(positionToRemove);
							 hotornot info=new hotornot(getActivity());
				    			info.open();
				    		Integer k=Integer.parseInt(rowItems.get(arg2).getid());
				    		//	Toast.makeText(getActivity(),  rowItems.get(arg2).getTitle(), Toast.LENGTH_LONG).show();
				    			info.Delete_Contact(k);
				    			info.close();
						    CreateFragment.this.onResume();

						}
					    });
				    adb.show();
		    		
		    		
		    		
		    			
		    			
		    			
		    		//Intent a= new Intent(getActivity(),CreateFragment.class);
					//startActivity(a);
				}
		    	
		    	
		    }
		});
		builder.show();
		
		return false;
	}
	

	
	
	
}


	


	
   

   
    

