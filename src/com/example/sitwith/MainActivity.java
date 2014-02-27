package com.example.sitwith;

import java.util.List;

import com.facebook.Session;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

	
public class MainActivity extends FragmentActivity {
	
	private MainFragment mainFragment;
	
	private UserService userService;
	
	private RestaurantService restaurantService;
	
	private RequestService requestService;
	
	private FeedbackService feedbackService;
	
	private Handler handler = new Handler();
	
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
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		userService = new UserService();
		restaurantService = new RestaurantService();
		requestService = new RequestService();
		feedbackService = new FeedbackService();
		
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		if (savedInstanceState == null) {  
            // Add the fragment on initial activity setup  
            mainFragment = new MainFragment();  
            getSupportFragmentManager()
            .beginTransaction()  
            .add(android.R.id.content, mainFragment)  
            .commit();  
		} else {
          // Or set the fragment from restored state info  
           mainFragment = (MainFragment) getSupportFragmentManager()  
           .findFragmentById(android.R.id.content);  
		}
		setContentView(R.layout.activity_main);

		Button btLogin = (Button) this
				.findViewById(R.id.get_in_button);

		btLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();// stop the current activity
			}

		});

		//Log.i("test", "test");
			
		//test();
		
		handler.postDelayed(checker, 500);
		//test();
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void test() {
		userService.login("100002049770344", "ztonyfz", "Tony", "Zhu", 23, "male");
		List<Neiborhood> neiborhoods = restaurantService.getRestaurants();
		Log.i("neiborhood", neiborhoods.get(0).name);
		Restaurant restaurant = restaurantService.getRestaurant(neiborhoods.get(0).restaurants.get(0).id);
		Log.i("restaurant", restaurant.address);
		List<Table> tables = restaurantService.getAvailableTables(restaurant.id);
		Log.i("table", tables.size() + "");
		requestService.makeRequest("100002049770344", tables.get(0).id);
		List<Request> requests = requestService.getRequests("100002049770344");
		Log.i("request", requests.get(0).restaruantName);
	}
	
	private void issueNotification() {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.com_facebook_button_blue)
        .setContentTitle("Your Request is Confirmed")
        .setContentText("Other users are matched. Click to view details.");
		Intent resultIntent = new Intent(this, MainActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
		
	}
	
	

	
}
