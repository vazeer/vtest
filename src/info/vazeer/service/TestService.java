package info.vazeer.service;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {

	private final String TAG = "TestService";
private  CallBacks msgSendObj;
final static String MY_ACTION = "MY_ACTION";
	
	  private int poolSize = 8;
	  private int maxPoolSize = 10;
	  private long keepAliveTime = 10;
	  
	 private ThreadPoolExecutor threadPool = null;

	  private final LinkedBlockingQueue<Runnable> threadsQueue =
	      new LinkedBlockingQueue<Runnable>();
	  
	@Override
	public void onCreate() {
		Log.v(TAG, "vazeer test service onCreate()");  
		super.onCreate();
		threadPool = new ThreadPoolExecutor(poolSize, maxPoolSize, keepAliveTime,
		        TimeUnit.SECONDS, threadsQueue);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(TAG, "vazeer test service onStartCommand");
		
		 for (int i = 0; i < 10; i++) {
	            Runnable worker = new WorkerThread("" + i);
	            threadPool.execute(worker);
	          }
		 
		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.v(TAG, "vazeer test service onDestroy()");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.v(TAG, "vazeer test service onBind");
		return null;
	}

	public class WorkerThread implements Runnable {

		private String command;

		public WorkerThread(String s) {
			this.command = s;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()
					+ " Start. Command = " + command);
			
			
			 Intent intent = new Intent();
		       intent.setAction(MY_ACTION);
		      
		       intent.putExtra("DATAPASSED", Thread.currentThread().getName()
						+ " Start. Command = " + command);
		      
		       sendBroadcast(intent);
		       
			
			processCommand();
			
			System.out.println(Thread.currentThread().getName() + " End.");
		}

		private void processCommand() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return this.command;
		}
	}
	


}
