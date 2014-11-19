package com.marksky.quickpayment;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	
	private static final int MESSAGE_REQUEST = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	/** Called when the user clicks the buttons */
	public void onClick(View v) {

		// 		 
		  switch (v.getId()) {
		  case R.id.btnRequest:
			  //Toast.makeText(getApplicationContext(), "Request Button is clicked", Toast.LENGTH_LONG).show();
			  
				Intent intent = new Intent(MainActivity.this, PrepareRequest.class);
				startActivityForResult(intent, MESSAGE_REQUEST);
				
			  break;
			  
		  case R.id.btnPay:
			  //Toast.makeText(getApplicationContext(), "Pay Button is clicked", Toast.LENGTH_LONG).show();
				Intent intentPay = new Intent(this, ReadPayment.class);
				startActivity(intentPay);

				break;
		  
		  case R.id.btnExit:
			  Toast.makeText(getApplicationContext(), "Exit Button is clicked", Toast.LENGTH_LONG).show();
			  break;
		  }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
