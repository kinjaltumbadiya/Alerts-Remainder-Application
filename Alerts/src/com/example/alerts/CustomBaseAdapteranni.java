package com.example.alerts;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CustomBaseAdapteranni extends BaseAdapter {
    Context context;
    List<RowItemAnniversary> rowItems;
     
    public CustomBaseAdapteranni(Context context, List<RowItemAnniversary> items) {
        this.context = context;
        this.rowItems = items;
    }
     
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtdate;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
         
        LayoutInflater mInflater = (LayoutInflater)
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list, null);
            holder = new ViewHolder();
           holder.txtdate= (TextView) convertView.findViewById(R.id.cur);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txt);
            holder.imageView = (ImageView) convertView.findViewById(R.id.flag);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
         
        RowItemAnniversary rowItem = (RowItemAnniversary) getItem(position);
        
        if(rowItem.getpath().equalsIgnoreCase("1"))
        {
        	System.out.println("Get Path ---"+rowItem.getpath());
        	holder.imageView.setImageResource(R.drawable.aiv);
        }
        else
        {
        	 
        Bitmap myBitmap= BitmapFactory.decodeFile("/mnt/sdcard/Alerts/"+rowItem.getpath());
        holder.imageView.setImageBitmap(roundCornerImage(myBitmap,10));
        }
        
        
       
        
        
        //holder.imageView.setImageBitmap( roundCornerImage(myBitmap,10));
        holder.txtdate.setText(rowItem.getdatee());
        holder.txtTitle.setText(rowItem.getname()+" weds "+ rowItem.getpatner());
        //holder.imageView.setImageResource(rowItem.getImageId());
         
        return convertView;
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
    	  int height = src.getHeight();
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
    	
}