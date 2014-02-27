package com.example.sitwith;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Check_History_Activity extends Activity{
	private ListView listview;
	private List<Request> list = new ArrayList<Request>();
	
	private RequestService requestService = new RequestService();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_history);
		
		listview = (ListView) findViewById(R.id.history_listview);
		
		/*Request rq1 = new Request();
		Request rq2 = new Request();
		Request rq3 = new Request();
		Request rq4 = new Request();
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		Calendar c4 = Calendar.getInstance();
		c1.set(113, 2, 27, 12, 0);
		c2.set(113, 2, 27, 12, 30);
		c3.set(113, 2, 28, 12, 0);
		c4.set(113, 2, 28, 12, 30);
		rq1.restaruantName="Union Grill";
		rq1.status="available";
		rq1.time=c1.getTime();
		rq2.restaruantName="Union Grill";
		rq2.status="available";
		rq2.time=c1.getTime();
		rq3.restaruantName="Union Grill";
		rq3.status="available";
		rq3.time=c1.getTime();
		rq4.restaruantName="Union Grill";
		rq4.status="available";
		rq4.time=c1.getTime();
		
		list.add(rq1);list.add(rq2);list.add(rq3);list.add(rq4);*/
		
		list = requestService.getRequests(Global.session.userId);
		
		
		CheckListAdapter adapter = new CheckListAdapter(this, R.layout.history_listview, list);
		listview.setAdapter(adapter);
		
	}
}
