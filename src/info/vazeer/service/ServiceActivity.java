package info.vazeer.service;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.androidcameraapi.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceActivity extends Activity implements CallBacks {
	Button btStart, btEnd;
	TextView textview;
	MyReceiver myReceiver;
	// http://www.sadacanada.com/wp-content/uploads/2012/02/Blue-Single-Rose-Flower-Image.jpg
	// http://cdn.freshflowersandgifts.com.au/shop/images/big/F7-1.jpg
	// http://www.hdwallpaperscool.com/wp-content/uploads/2013/12/lotus-flower-high-definition-wallpapers-best-desktop-background-images-widescreen.jpg
	// http://fin6.com/wp-content/uploads/2013/08/68c6a030f10820fde539b7acd20a4bdb.jpg
	// http://p1.pichost.me/i/26/1486235.jpg
	// http://www.wallpaper.in.net/wp-content/uploads/2014/02/Beautiful-Red-n-White-Flowers.jpg

	private List<String> test;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_activty);
		test = new ArrayList<String>();
		btStart = (Button) findViewById(R.id.buttonView1);
		btEnd = (Button) findViewById(R.id.buttonView2);
		textview = (TextView) findViewById(R.id.textViewMsg);

		test.add("");
		test.add("");
		test.add("");
		test.add("");
		test.add("");
		test.add("");

		btStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ServiceActivity.this, TestService.class);
				i.putExtra("name", "SurvivingwithAndroid");
				ServiceActivity.this.startService(i);
			}
		});

		btEnd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ServiceActivity.this, TestService.class);
				ServiceActivity.this.stopService(i);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		myReceiver = new MyReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(TestService.MY_ACTION);
		registerReceiver(myReceiver, intentFilter);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		unregisterReceiver(myReceiver);
		super.onStop();
	}

	@Override
	public void updateUi(String msg) {
		

	}

	private class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub

			String datapassed = arg1.getStringExtra("DATAPASSED");
			textview.setText(datapassed + " \n");
		

		}

	}

}
