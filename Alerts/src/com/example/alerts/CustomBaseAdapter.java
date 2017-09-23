 package com.example.alerts;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import android.R.integer;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.net.ParseException;
import android.text.InputFilter.LengthFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

 
public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    List<RowItem> rowItems;
    ViewHolder holder = null;
    final static int RQS_1 = 1;
    private PendingIntent pendingIntent;
     
    public CustomBaseAdapter()
    {}
    public CustomBaseAdapter(Context context, List<RowItem> items) {
        this.context = context;
        this.rowItems = items;
    }
     
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtdate;
        TextView datedifference;
        TextView totalyear;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
    
         
        LayoutInflater mInflater = (LayoutInflater)
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
           holder.txtdate= (TextView) convertView.findViewById(R.id.rating);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.thumbnail);
            holder.datedifference=(TextView)convertView.findViewById(R.id.genre);
            holder.totalyear=(TextView)convertView.findViewById(R.id.releaseYearr);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
         
        RowItem rowItem = (RowItem) getItem(position);
        System.out.println("get path"+rowItem.getpath());
        if(rowItem.getpath().equalsIgnoreCase("1"))
        {
        	System.out.println("Get Path ---"+rowItem.getpath());
        	holder.imageView.setImageResource(R.drawable.bimg);
        }
        else
        {
        	 
        Bitmap myBitmap= BitmapFactory.decodeFile("/mnt/sdcard/Alerts/"+rowItem.getpath());
        holder.imageView.setImageBitmap(roundCornerImage(myBitmap,10));
        }
        CustomBaseAdapter obj = new CustomBaseAdapter();
	      SimpleDateFormat simpleDateFormat = 
	                new SimpleDateFormat("dd-M");
	      String date = simpleDateFormat.format(Calendar.getInstance().getTime());
	     	    		
	    		
	     System.out.println("current datewrwe =="+date);
	     // System.out.print("current date =="+date);
       
        holder.txtdate.setText(rowItem.getDesc());
        holder.txtTitle.setText(rowItem.getTitle());
        System.out.println("startDate : " + rowItem.getDesc());
        
        try {

	        Date date1 = simpleDateFormat.parse(date);
	        Date date2 = simpleDateFormat.parse(rowItem.getDesc());	
	        
	        long k=obj.printDifference(date1, date2);
	        //k=365-k;
	      //  long a=Math.abs(k);
	        holder.datedifference.setText(k+" ddays left");

	      } catch (ParseException e) {
	        e.printStackTrace();
	      } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ////
        
        CustomBaseAdapter obj1 = new CustomBaseAdapter();
	      SimpleDateFormat simpleDateFormatt = 
	                new SimpleDateFormat("dd-M-yyyy");
	      String datee = simpleDateFormatt.format(Calendar.getInstance().getTime());
	     
	      try {

	        Date date1 = simpleDateFormatt.parse(rowItem.getDesc());
	     System.out.println("jane ->>"+date1.getYear()+1);
	        Date date2 = simpleDateFormatt.parse(datee);
	       
	       long k= obj1.trunprintDifference(date1, date2);
	      // holder.dat.setText(a+" days left");
	       holder.totalyear.setText(k+"");
	      } catch (ParseException e) {
	        e.printStackTrace();
	      } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ////
        
	      setAlarm(rowItem.getDesc());
        
        
        //holder.imageView.setImageResource(rowItem.getImageId());
	
	     
	      
        return convertView;
    }
    private void setAlarm(String a){
    	 try {
	    	  SimpleDateFormat simpleDateFormattt = 
		                new SimpleDateFormat("dd-MM-yyyy");
	    	  Date d = simpleDateFormattt.parse(a);
	    	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	    	  String folderName = formatter.format(d);
	    	  int yy=Integer.parseInt(folderName);
	    	  System.out.println("Folder Name = " + folderName);
	    	  Calendar cal = Calendar.getInstance();
		  	    //cal.set(yy, 
		  	    	//	d.getMonth()+1, 
		  	    		//d.getDate(),0,52,00);
	    	 // cal.set(2014, 8, 20,20,7,00);
	    	 // cal.set(yy, d.getMonth(), d.getDate(),10,15,00);
	    	  //cal.set(year, month, day, hourOfDay, minute, second)
		  	    System.out.println("check date-->"+d.getDate()+"--"+d.getMonth()+"--"+yy);
		  	 Intent myIntent = new Intent(context, MyReceiver.class);
		        pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent,0);
		       
		        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
		        alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
			
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	        
    }
    
    @Override
    public int getCount() {    
        return rowItems.size();
    }
    
    
    
 
    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
    public Bitmap roundCornerImage(Bitmap src, float round) {

  	  // Source image size
  	  int width = src.getWidth();
  	  int height = src.getHeight(
  			  );
  	  // create result bitmap output
  	  Bitmap result = Bitmap.createBitmap(width, height, Config.ARGB_8888);
  	  // set canvas for painting
  	  Canvas canvas = new Canvas(result);
  	  canvas.drawARGB(0, 0, 0, 0);
  	 
  	  // configure paint
  	  final Paint paint = new Paint();
  	  paint.setAntiAlias(true);
  	  paint.setColor(Color.BLACK);
  	 
  	  // configure rectangle for embedding
  	  final Rect rect = new Rect(0, 0, width, height);
  	  final RectF rectF = new RectF(rect);
  	 
  	  // draw Round rectangle to canvas
  	  canvas.drawRoundRect(rectF, round, round, paint);
  	 
  	  // create Xfer mode
  	  paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
  	  // draw source image to canvas
  	  canvas.drawBitmap(src, rect, rect, paint);
  	 
  	  // return final image
  	  return result;
  	 }
  //1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400
    public long printDifference(Date startDate, Date endDate){
    	// setalarm obj=new setalarm();
    	// obj.settt(endDate);
    	  
    	 long differnentyear=0,d,m;
    	//String  a=endDate.getDay()+"-"+endDate.getMonth();
    	        //milliseconds
        long different = endDate.getTime() - startDate.getTime();
    System.out.println("year------------>"+endDate.getYear());
    //System.out.println("Date------------->"+startDate.getDate());
long sum=0;

if(endDate.getMonth()==startDate.getMonth())
{
	for(int i=startDate.getDate();i<=endDate.getDate();i++)
	 {
	sum=sum+1;
	 }
	return sum;
}
else
 if(endDate.getMonth()>startDate.getMonth())
 {
	 for(int i=startDate.getMonth()+1;i<endDate.getMonth();i++)
	 {
		 	System.out.println(startDate.getMonth()+2);
				 System.out.println("i-->"+i);
		if (i==1)
		{
			sum=sum+31;
   		}
		else if(i==2)
		{
			sum=sum+28;
		}
		else if(i==3)
		{
			sum=sum+31;
		}
		else if(i==4)
		{
			sum=sum+30;
		}
		else if(i==5)
		{
			sum=sum+31;
		}
		else if(i==6)
		{
			sum=sum+30;
		}
		else if(i==7)
		{
			sum=sum+31;
		}
		else if(i==8)
		{
			sum=sum+31;
		}
		else if(i==9)
		{
			sum=sum+30;
		}
		else if(i==10)
		{
			sum=sum+31;
		}
		else if(i==11)
		{
			sum=sum+30;
		}
		else if(i==12)
		{
			sum=sum+31;
		}
	  
	 }
	 System.out.println("aa jova nu che  sum---->"+sum);
 }
 else
 {
   
    	 for(int i=startDate.getMonth()+2;i<=12;i++)
    	 {
    	System.out.println("i-->"+i);
    		if (i==1)
    		{
    			sum=sum+31;
       		}
    		else if(i==2)
    		{
    			sum=sum+28;
    		}
    		else if(i==3)
    		{
    			sum=sum+31;
    		}
    		else if(i==4)
    		{
    			sum=sum+30;
    		}
    		else if(i==5)
    		{
    			sum=sum+31;
    		}
    		else if(i==6)
    		{
    			sum=sum+30;
    		}
    		else if(i==7)
    		{
    			sum=sum+31;
    		}
    		else if(i==8)
    		{
    			sum=sum+31;
    		}
    		else if(i==9)
    		{
    			sum=sum+30;
    		}
    		else if(i==10)
    		{
    			sum=sum+31;
    		}
    		else if(i==11)
    		{
    			sum=sum+30;
    		}
    		else if(i==12)
    		{
    			sum=sum+31;
    		}
    	  
    	 }
    	 System.out.println("sum is:-"+sum);
    	 for(int i=1;i<=endDate.getMonth();i++)
    	 {
    	System.out.println("ii-->"+i);
    	if (i==1)
		{
			sum=sum+31;
   		}
		else if(i==2)
		{
			sum=sum+28;
		}
		else if(i==3)
		{
			sum=sum+31;
		}
		else if(i==4)
		{
			sum=sum+30;
		}
		else if(i==5)
		{
			sum=sum+31;
		}
		else if(i==6)
		{
			sum=sum+30;
		}
		else if(i==7)
		{
			sum=sum+31;
		}
		else if(i==8)
		{
			sum=sum+31;
		}
		else if(i==9)
		{
			sum=sum+30;
		}
		else if(i==10)
		{
			sum=sum+31;
		}
		else if(i==11)
		{
			sum=sum+30;
		}
		else if(i==12)
		{
			sum=sum+31;
		}
    	 }
    	System.out.println("final sum is:-"+sum);
 }
    long l=startDate.getMonth()+1;
    
    long k = 0;
     if(l==1)
     {
    	k=30-startDate.getDate();
     }
     else if(l==2)
     {
    	 k=28-startDate.getDate(); 
     }
     else if(l==3)
     {
    	 k=31-startDate.getDate(); 
     }
     else if(l==4)
     {
    	 k=30-startDate.getDate();
    	 
     }
     else if(l==5)
     {
    	 k=31-startDate.getDate();
     }
     else if(l==6)
     {
    	 k=30-startDate.getDate();
     }
     else if(l==7)
     {
    	 k=31-startDate.getDate(); 
     }
     
     else if(l==8)
     {
    	 k=31-startDate.getDate();
     }
     else if(l==9)
     {
    	 k=30-startDate.getDate();
    	 
     }
     else if(l==10)
     {
    	 k=31-startDate.getDate();
     }
     else if(l==11)
     {
    	 k=30-startDate.getDate(); 
     }
     else if(l==12)
     {
    	 k=31-startDate.getDate();
     }
 
//     System.out.println("start date-->:-"+k);
     k=k+ endDate.getDate();
    
     sum=sum+k;
 
     System.out.println("fff==>"+sum);
  //differnentyear=differnentyear+1;
 //  long k=differnentyear*365;
  //      System.out.println("startDate : " + startDate);
    //    System.out.println("endDate : "+ endDate);
      //  System.out.println("different : " + different);

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
      
        	return(sum);
    }
    public long trunprintDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

       // System.out.println("startDate : " + startDate);
        //System.out.println("endDate : "+ endDate);
        //System.out.println("different : " + different);

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
            "%d dddays, %d hours, %d minutes, %d seconds%n", 
            elapsedDays,
            elapsedHours, elapsedMinutes, elapsedSeconds);
        	long finallyear=elapsedDays/365;
        	//System.out.println("ffff===>"+finallyear);
        	return(finallyear+1);
    }
   
    
public long getyearr(Date d)
{
	return d.getYear()+1;
} 
	
    
}