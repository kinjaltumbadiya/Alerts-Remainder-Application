package com.example.alerts;

import java.util.List;

import android.app.Activity;
import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;




	public class CustomBaseAdapterCustom extends BaseAdapter {
	    Context context;
	    List<RowItemCustom> rowItems;
	     
	    public CustomBaseAdapterCustom(Context context, List<RowItemCustom> items) {
	        this.context = context;
	        this.rowItems = items;
	    }
	     
	    /*private view holder class*/
	    private class ViewHolder {
	       
	        TextView txtname;
	        TextView txtdate;
	    }
	 
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ViewHolder holder = null;
	         
	        LayoutInflater mInflater = (LayoutInflater)
	            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	        if (convertView == null) {
	            convertView = mInflater.inflate(R.layout.list_custom, null);
	            holder = new ViewHolder();
	           holder.txtname= (TextView) convertView.findViewById(R.id.title);
	            holder.txtdate = (TextView) convertView.findViewById(R.id.rating);
	            
	            convertView.setTag(holder);
	        }
	        else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	         
	        RowItemCustom rowItem = (RowItemCustom) getItem(position);
	        
	       
	        
	        
	     
	        holder.txtname.setText(rowItem.getname());
	        holder.txtdate.setText(rowItem.getdt());
	        
	         
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
	   
	
}
