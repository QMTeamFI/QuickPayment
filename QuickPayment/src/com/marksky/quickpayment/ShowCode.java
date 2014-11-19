package com.marksky.quickpayment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Build;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class ShowCode extends ActionBarActivity {

	private static final String HARDCODED_SAMPLE = "0123456789";
	private static String requestedSum;
	private String LOG_TAG = "GenerateQRCode";
	private static String mPhoneNumber;
	private static int smallerDimension;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_code);

		   //Find screen size
		   WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
		   Display display = manager.getDefaultDisplay();
		   Point point = new Point();
		   display.getSize(point);
		   int width = point.x;
		   int height = point.y;
		   smallerDimension = width < height ? width : height;
		   smallerDimension = smallerDimension * 3/4;
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		// try get phone number
		TelephonyManager tMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		
		try {
			mPhoneNumber = tMgr.getLine1Number();
			
			if (mPhoneNumber == null){
				mPhoneNumber = HARDCODED_SAMPLE;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mPhoneNumber = "nullValue";
		}
		
	    Intent intent = getIntent();
	    requestedSum = intent.getStringExtra("sum");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_code, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_show_code,
					container, false);
			
			ImageView imgV = (ImageView) rootView.findViewById(R.id.imageView1);
		    TextView tv = (TextView) rootView.findViewById(R.id.textView1);
		    TextView tvPhone = (TextView) rootView.findViewById(R.id.textView4);
		    tv.setTextSize(10);
		    tvPhone.setTextSize(10);
		    
		    tv.setText(requestedSum);
		    tvPhone.setText(mPhoneNumber);
		    
		    String qrText = "sum:" + requestedSum + ", phone:" + mPhoneNumber;
		    
		       String qrInputText = qrText;
			   //Log.rootView(LOG_TAG, qrInputText);
			 
		       //Encode with a QR Code image
			   QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrInputText, 
			             null, 
			             Contents.Type.TEXT,  
			             BarcodeFormat.QR_CODE.toString(), 
			             smallerDimension);
			   
			   //smallerDimension can be a constant integer, if image size is wanted as hard coded dimensions
			   
			   try {
			    Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
			    ImageView myImage = imgV;
			    myImage.setImageBitmap(bitmap);
			 
			   } catch (WriterException e) {
			    e.printStackTrace();
			   }
			   
			return rootView;
		}
	}

}
