package com.example.sitwith;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btEnterLogin = (Button)this.findViewById(R.id.enterLogin_button);
		
		btEnterLogin.setOnClickListener(new View.OnClickListener(){  
			  
            @Override  
            public void onClick(View v) {  
                Intent intent = new Intent();  
                intent.setClass(MainActivity.this, LoginActivity.class);  
                startActivity(intent);  
                finish();//stop the current activity       
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
