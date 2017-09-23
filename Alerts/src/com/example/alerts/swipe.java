package com.example.alerts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class swipe extends PagerAdapter {
Context context;
private int[] GalImages = new int[] {
R.drawable.ic_launcher,
R.drawable.ic_launcher,
R.drawable.ic_launcher
};
void ImageAdapter(Context context){
this.context=context;
}
@Override
public int getCount() {
return GalImages.length;
}
 
public boolean isViewFromObject1(View view, Object object) {
return view == ((ImageView) object);
}
 
@Override
public Object instantiateItem(ViewGroup container, int position) {
	
ImageView imageView = new ImageView(context);
int padding = context.getResources().getDimensionPixelSize(100);
imageView.setPadding(padding, padding, padding, padding);
imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
imageView.setImageResource(GalImages[position]);
((ViewPager) container).addView(imageView, 0);
return imageView;
}
 
@Override
public void destroyItem(ViewGroup container, int position, Object object) {
((ViewPager) container).removeView((ImageView) object);
}
@Override
public boolean isViewFromObject(View arg0, Object arg1) {
	// TODO Auto-generated method stub
	return false;
}
}