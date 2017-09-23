package com.example.alerts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;


public class home extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swipe);
		ImageView iv1=(ImageView)findViewById(R.id.imageView1);
		iv1.setOnClickListener(this);
		
		Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
				.getBoolean("isfirstrun", true);

		if (isFirstRun) {
			// do some thing
			Toast.makeText(home.this, "First Run", Toast.LENGTH_LONG)
					.show();
			ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
			ImageAdapter adapter = new ImageAdapter(this);
			viewPager.setAdapter(adapter);
			getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
					.putBoolean("isfirstrun", false).commit();
			
		//	ImageAdapter obj=new ImageAdapter(getApplicationContext());
		//	int l=obj.getCount();
		//	for(int k=9;k>=1;k--){
		//		
		//	if(k==1){
		//		Toast.makeText(getApplicationContext(), "called", Toast.LENGTH_LONG).show();
		//		iv1.setVisibility(1);
		//	}
		//	}
			
			
		}
		else
		{
		Intent obj=new Intent(home.this,MainActivity.class);
		startActivity(obj);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
