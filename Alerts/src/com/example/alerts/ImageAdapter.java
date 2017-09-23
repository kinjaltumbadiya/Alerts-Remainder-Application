package com.example.alerts;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageAdapter extends PagerAdapter{
	Context context;
	private int[] GalImages = new int[] {
	R.drawable.ss1,
	R.drawable.ss2,
	R.drawable.ss3,
	R.drawable.ss4,
	R.drawable.ss5,
	R.drawable.ss6,
	R.drawable.ss7,
	R.drawable.ss8,
	R.drawable.ss9
	};
	ImageAdapter(Context context){
	this.context=context;
	}
	@Override
	public int getCount() {
	return GalImages.length;
	}
	 
	@Override
	public boolean isViewFromObject(View view, Object object) {
	return view == ((ImageView) object);
	}
	 
	public Object instantiateItem(ViewGroup container, int position) {
	ImageView imageView = new ImageView(context);
	//int padding = context.getResources().getDimensionPixelSize(R.dimen.year);
	
	//imageView.setPadding(padding, padding, padding, padding);
	//imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
	imageView.setImageResource(GalImages[position]);
	((ViewPager) container).addView(imageView, 0);
	
	if(position==9)
	{
		Toast.makeText(context, "postion="+position, Toast.LENGTH_LONG);
		
		
		
		
	}
	return imageView;
	}
	 
	public void destroyItem(ViewGroup container, int position, Object object) {
	((ViewPager) container).removeView((ImageView) object);
	}
}
