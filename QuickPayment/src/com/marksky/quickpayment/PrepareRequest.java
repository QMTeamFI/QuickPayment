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
import android.widget.*;
import android.os.Build;

public class PrepareRequest extends ActionBarActivity {

	private static final int MESSAGE_REQUEST = 2;
	private String selectedsum;
	private RadioGroup radioSumGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prepare_request);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	/** Called when the user clicks the buttons */
	public void ShowQRCode(View v) {
		Intent intent = new Intent(PrepareRequest.this, ShowCode.class);
		//String message = editText.getText().toString();
		// pick selected sum
		radioSumGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		int selectedId = radioSumGroup.getCheckedRadioButtonId();
		switch (selectedId) {
		case R.id.radio0:
			selectedsum = "10";
			break;
		case R.id.radio1:
			selectedsum = "20";
			break;
		case R.id.radio2:
			selectedsum = "50";
			break;
		case R.id.radio3:
			selectedsum = "100";
			break;
		case R.id.radio4:
			EditText othertextsum = (EditText) findViewById(R.id.editText1);
			selectedsum = "12.55";
			break;

		}
		//Toast.makeText(this, Integer.toString(selectedId), Toast.LENGTH_SHORT).show();
		intent.putExtra("sum", selectedsum);
		intent.putExtra("phone", "0408668162");
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prepare_request, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_prepare_request,
					container, false);
			return rootView;
		}
	}

}
