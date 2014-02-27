package com.example.sitwith;

import java.util.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainPageActivity extends Activity {

	private GridView gridview;
	private List<GridInfo> list;
	private GridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		gridview = (GridView) findViewById(R.id.gridView);
		list = new ArrayList<GridInfo>();
		list.add(new GridInfo("Set up a Lunch", R.drawable.set_up_a_lunch));
		list.add(new GridInfo("Check history", R.drawable.check_history));
		list.add(new GridInfo("Account setting", R.drawable.account));
		list.add(new GridInfo("Q&A", R.drawable.qa));
		adapter = new GridAdapter(this);
		adapter.setList(list);
		gridview.setAdapter(adapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				switch (position) {
					case 0: {
						startActivity(new Intent(MainPageActivity.this,
								Select_Restaurant_Activity.class));
					}break;
					case 1: {
						startActivity(new Intent(MainPageActivity.this,
								Check_History_Activity.class));
					}break;

				}
			}
		});
	}

}
