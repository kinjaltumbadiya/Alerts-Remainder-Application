package com.example.alerts;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listfrag extends ListFragment{

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		}

		@Override

		public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] values = new String[] { "Enterprise", "Star Trek", "Next Generation", "Deep Space 9", "Voyager"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);

		setListAdapter(adapter);

		}

		@Override

		public void onListItemClick(ListView l, View v, int position, long id) {

		

		}

}
	

