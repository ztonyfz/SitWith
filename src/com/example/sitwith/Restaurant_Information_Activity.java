package com.example.sitwith;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Restaurant_Information_Activity extends Activity {

	public final String TAG = "Actity:";
	Bitmap bmImg;
	ImageView imView;
	TextView resNameText;
	TextView resCategoryText;
	TextView resNeiborhoodText;
	TextView resAddressText;
	TextView resPhoneText;

	List<Table> availabletables = new ArrayList<Table>();

	ListView listview;
	ArrayList<String> tableIDList = new ArrayList<String>();

	String selectedTableID;

	final Context context = this;
	
	private RestaurantService restaurantService = new RestaurantService();
	
	private RequestService requestService = new RequestService();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant__information);
		/* Get the bundle object */
		Bundle bundle = this.getIntent().getExtras();

		/* get the bundle data */
		String resID = bundle.getString("id");

		/*Restaurant res1 = new Restaurant(
				// This is the facked restaurant information
				"1",
				"North Oakland",
				"Union Grill",
				"American",
				"15213 Craig Street",
				"",
				"412-487-5964",
				"http://aislefilesblog.com/wp-content/uploads/2009/11/Union-Grill.JPG",
				"");*/
		Restaurant res1 = restaurantService.getRestaurant(resID);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		imView = (ImageView) findViewById(R.id.restaurant_info_image);
		URL url;
		try {
			url = new URL(res1.picture);
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;//
			Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
			imView.setImageBitmap(bitmap);

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		resNameText = (TextView) findViewById(R.id.restaurant_info_name);
		resCategoryText = (TextView) findViewById(R.id.restaurant_info_category);
		resNeiborhoodText = (TextView) findViewById(R.id.restaurant_info_neiborhood);
		resAddressText = (TextView) findViewById(R.id.restaurant_info_address);
		resPhoneText = (TextView) findViewById(R.id.restaurant_info_phone);

		resNameText.setText(res1.name);
		resCategoryText.setText(res1.category);
		resNeiborhoodText.setText(res1.neiborhood);
		resAddressText.setText(res1.address);
		resPhoneText.setText(res1.phone);
		/* Begin of the fake data */
		/*Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		Calendar c4 = Calendar.getInstance();
		c1.set(113, 2, 27, 12, 0);
		c2.set(113, 2, 27, 12, 30);
		c3.set(113, 2, 28, 12, 0);
		c4.set(113, 2, 28, 12, 30);
		Table t1 = new Table("1", c1.getTime());
		Table t2 = new Table("2", c2.getTime());
		Table t3 = new Table("3", c3.getTime());
		Table t4 = new Table("4", c4.getTime());
		availabletables.add(t1);
		availabletables.add(t2);
		availabletables.add(t3);
		availabletables.add(t4);*/
		availabletables = restaurantService.getAvailableTables(resID);

		listview = (ListView) findViewById(R.id.available_table_listview);
		for (int i = 0; i < availabletables.size(); i++) {
			tableIDList.add(availabletables.get(i).id);
		}

		AvailTableListAdapter listAdapter = new AvailTableListAdapter(this,
				R.layout.available_table_listview, availabletables);
		listview.setAdapter(listAdapter);

		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				selectedTableID = tableIDList.get(arg2);
				showRequestDialog();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant__information, menu);
		return true;
	}

	public Bitmap getBitMap(String strUrl) {
		Bitmap bitmap = null;
		InputStream is = null;
		try {
			URL url = new URL(strUrl);
			URLConnection conn = url.openConnection();
			is = conn.getInputStream();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		bitmap = BitmapFactory.decodeStream(is);
		return bitmap;
	}

	private void showRequestDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set title
		alertDialogBuilder.setTitle("Confirmation");

		// set dialog message
		alertDialogBuilder
				.setMessage("Are you sure you want to select this time?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Bundle bundle = new Bundle(); // bundle could
																// add multiple

								bundle.putString("tableID", selectedTableID);
								bundle.putString("info", "info");
								
								requestService.makeRequest(Global.session.userId, selectedTableID);
								
								
								Intent intent = new Intent(
										Restaurant_Information_Activity.this,
										MainPageActivity.class); // create
																	// intent
																	// object
								// intent.putExtras(bundle); // params
								Restaurant_Information_Activity.this
										.startActivity(intent);
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
}
