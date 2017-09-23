package com.example.alerts;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       
        setContentView(R.drawable.settings);
        setTitle("Settings");
       	
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#007FFF")));
		bar.setDisplayHomeAsUpEnabled(true);
    }


@Override
public boolean onOptionsItemSelected(MenuItem item) 
{
    // Handle item selection
    switch (item.getItemId()) 
    {
        case android.R.id.home:
            Intent intent = new Intent(this, MainActivity.class);
            NavUtils.navigateUpTo(this, intent);
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
}
}
