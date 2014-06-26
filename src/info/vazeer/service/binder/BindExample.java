/**
 * 
 */
package info.vazeer.service.binder;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.androidcameraapi.R;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * @author vazeer
 * 
 */
public class BindExample extends ListActivity {
	List<String> wordList;
	ArrayAdapter<String> adapter;
	LocalWordService s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bind_service);
		wordList = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				wordList);

		setListAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent intent = new Intent(this, LocalWordService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
		startService(intent);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unbindService(mConnection);
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			s = null;

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalWordService.MyBinder b = (LocalWordService.MyBinder) service;
			s = b.getService();
			Toast.makeText(BindExample.this, "Connected", Toast.LENGTH_SHORT)
					.show();

		}
	};

	public void onClick(View view) {
		if (s != null) {
			Toast.makeText(this, "Number of elements" + s.getWordList().size(),
					Toast.LENGTH_SHORT).show();
			wordList.clear();
			wordList.addAll(s.getWordList());
			adapter.notifyDataSetChanged();
		}
	}
}
