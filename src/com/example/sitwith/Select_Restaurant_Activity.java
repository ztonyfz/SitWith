package com.example.sitwith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Select_Restaurant_Activity extends Activity {

	private ListView listview;
	private ArrayList<Restaurant> list = new ArrayList<Restaurant>();
	private ArrayList<String> resIDList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_up_lunch_select_restaurant);

		listview = (ListView) findViewById(R.id.listview);
		list.add(new Restaurant("1","Union Grill", "North Oakland",
				"North Craigh Street"));
		list.add(new Restaurant("2","Steeler", "Shadyside", "Walnut"));
		
		for(int i=0;i<list.size();i++){
			resIDList.add(list.get(i).id);
		}
		
		ListAdapter adapter3 = new ListAdapter(this, R.layout.listview, list);

		listview.setAdapter(adapter3);

		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {

				Bundle bundle = new Bundle(); // bundle could add multiple parameters
				String resID = resIDList.get(arg2);
				bundle.putString("id", resID);
				// bundle.putString("info", "info");
				Intent intent = new Intent(Select_Restaurant_Activity.this,
						Restaurant_Information_Activity.class); // create intent object
				// intent.putExtra("id",id); //parma
				intent.putExtras(bundle); // params
				Select_Restaurant_Activity.this.startActivity(intent); // start activity
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
