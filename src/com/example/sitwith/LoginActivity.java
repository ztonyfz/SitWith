package com.example.sitwith;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends FragmentActivity {
	private MainFragment mainFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
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

		/*Button btLogin = (Button) this
				.findViewById(R.id.signin_button);

		btLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, MainPageActivity.class);
				startActivity(intent);
				finish();// stop the current activity
			}

		});*/
	}
}
