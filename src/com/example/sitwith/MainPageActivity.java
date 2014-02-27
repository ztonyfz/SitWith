package com.example.sitwith;

import java.util.*;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
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
	
	private Handler handler = new Handler();
	
	private RequestService requestService = new RequestService();
	
	private Runnable checker = new Runnable() {

        @Override
        public void run() {
        	SitWithSession session = Global.session;
        	Log.i("checker", "checker is working...");
        	if (session != null) {
        		List<Request> requests = requestService.getRequests(session.userId);
        		for (Request request : requests) {
        			Table table = requestService.getTable(request.tableId);
        			Log.i("test", table.count + " " + request.notificationStatus);
        			if (table.count == 0 && request.notificationStatus.equals("Not Notified")) {
        				issueNotification();
        				requestService.changeToNotified(request.id);
        			}
        			
        		}
        		
        		
        	}
        	handler.postDelayed(this, 5000);
        }

		
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		SitWithSession session = new SitWithSession();
    	session.userId = "100002049770344";
    	Global.session = session;

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
		
		handler.postDelayed(checker, 500);
	}
	
	private void issueNotification() {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.com_facebook_button_blue)
        .setContentTitle("Your Request is Confirmed")
        .setContentText("Other users are matched. Click to view details.");
		Intent resultIntent = new Intent(this, Check_History_Activity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(Check_History_Activity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
		
	}

}
