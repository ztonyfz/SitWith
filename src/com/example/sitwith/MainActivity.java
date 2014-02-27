package com.example.sitwith;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;

import android.os.Bundle;
import android.os.StrictMode;
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
	
	

	
}
