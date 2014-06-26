package info.vazeer.service.binder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalWordService extends Service {

	private final IBinder mBinder = new MyBinder();

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	private ArrayList<String> list = new ArrayList<String>();

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Random random = new Random();
		if (random.nextBoolean()) {
			list.add("Linux");
		}
		if (random.nextBoolean()) {
			list.add("Android");
		}
		if (random.nextBoolean()) {
			list.add("iPhone");
		}
		if (random.nextBoolean()) {
			list.add("Windows7");
		}
		if (list.size() >= 20) {
			list.remove(0);
		}
		Log.v("LocalWordService", "onStartCommand count: "+list.size());
		return START_NOT_STICKY;
	}

	public class MyBinder extends Binder {

		 LocalWordService getService() {
		      return LocalWordService.this;
		    }

	}

	public List<String> getWordList() {
		return list;
	}

}
