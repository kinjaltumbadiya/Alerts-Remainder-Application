package com.example.alerts;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class HelpFragment extends Fragment implements OnItemClickListener,OnItemLongClickListener{

	ListView listView;
    List<RowItemCustom> rowItems;
    
	public HelpFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_help, container, false);
		final ListView l=(ListView) rootView.findViewById(R.id.listView1);
		 // Defined Array values to show in ListView
   
    hotornot info=new hotornot(getActivity());
		info.open();
		Cursor cursor = info.getallcustommsg();
		String result = null;
		List list = new ArrayList();
		list.add("abc");
		 rowItems = new ArrayList<RowItemCustom>();
		 int i=1;
	    for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
		{
	    	list.add(cursor.getString(0) + "\n " + cursor.getString(1));
//result=result + cursor.getString(0) + "  " + cursor.getString(1) +"  " + cursor.getString(2)+"  "+cursor.getString(3)+" "+cursor.getString(4)+"\n";
	    	RowItemCustom item = new RowItemCustom(i,cursor.getString(0), cursor.getString(1), cursor.getString(2));
	    
	    	rowItems.add(item);
          i++;
	
		}
	    cursor.close();
	    info.close();
	    System.out.print("all Record in anni-->  "+result);
		//ListAdapter k=new SimpleAdapter(getActivity(),val,R.layout.list,new String[]{"TaskId","heading","status"},new int[]{R.id.txt,R.id.cur,R.id.textView2});
   // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, list);
	    CustomBaseAdapterCustom adapter = new CustomBaseAdapterCustom(getActivity(), rowItems);
  l.setAdapter(adapter);
 l.setOnItemClickListener(this);
 l.setOnItemLongClickListener(this);
 //  Toast.makeText(getActivity(), "No Task to Display", Toast.LENGTH_SHORT).show();
  // l.setAdapter(adapter);
     
	    return rootView;
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
		   
		    	//	Intent a= new Intent(MainActivity.this,profile2.class);
		    	//	a.putExtra("message", "abc");
				//	startActivity(a);
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
		    		 hotornot info=new hotornot(getActivity());
		    			info.open();
		    		Integer k=Integer.parseInt(rowItems.get(arg2).getid());
		    		//	Toast.makeText(getActivity(),  rowItems.get(arg2).getTitle(), Toast.LENGTH_LONG).show();
		    			info.Delete_Contactcus(k);
		    			info.close();
		    			HelpFragment.this.onResume();
		    			
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
