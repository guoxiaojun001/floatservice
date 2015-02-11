package com.example.demoservicefuncation;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private View btnCallServiceFunc;
	private Intent mService;
	private MyInterface mBinder;
	private MyServiceConnection conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		bindService();
		Log.d("MainActivity", ">>>001>>>>>>>>>>>>mBinder = " + mBinder);
	}

	private void initView() {
		btnCallServiceFunc = this.findViewById(R.id.btn);
		btnCallServiceFunc.setOnClickListener(new MainOnclickListenter());
		mService = new Intent(MainActivity.this, MyService.class);

	}

	private class MainOnclickListenter implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == btnCallServiceFunc) {
				// 调用服务里的方法
//				bindService();
				Log.d("MainActivity", ">>>002>>>>>>>>>>>>mBinder = " + mBinder);
				mBinder.callMethod2();

			}
		}

	}

	public void bindService() {
		conn = new MyServiceConnection();
		bindService(mService, conn, BIND_AUTO_CREATE); // 1.调用次方法，会调用MyService里的onBind()
		Log.d("MainActivity", ">>>003>>>>>>>>>>>>mBinder = " + mBinder);
	}

	public void unBindService() {
		// 接触绑定
		unbindService(conn);
	}

	private class MyServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// 3,绑定成功,在此处就可以用服务中的方法
			Log.d("MainActivity", ">>>004>>>>>>>>>>>>mBinder = " + mBinder);
			MainActivity.this.mBinder = (MyInterface) service;
			Log.d("MainActivity", ">>>0aaa>>>>>>>>mBinder = " + mBinder);
//			mBinder.callMethod();
//			mBinder.callMethod2();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d("MainActivity", ">>>005>>>>>>>>>>>>mBinder = " + mBinder);
		}

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("MainActivity", ">>>006>>>>>>>>>>>>mBinder = " + mBinder);
		startService(mService); // Myservice需要在清单文件中配置
		Log.d("MainActivity", ">>>007>>>>>>>>>>>>mBinder = " + mBinder);
	};

	@Override
	protected void onStop() {
		super.onStop();
		stopService(mService);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//接触绑定
		unbindService(conn);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
