package com.example.demoservicefuncation;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		//2.接受到绑定信息.使用代理模式，将服务中的myMethod()暴露出去给activity中使用
		CallMethod mCallMethod=new CallMethod();
		return mCallMethod; //返回的mCallmethod对象会在onServiceConnected()中调用
	}
	
	private void method1(){
		Toast.makeText(getApplicationContext(), "我是服务里的方法", 0).show();
	}
	
	private void method2(){
		Toast.makeText(getApplicationContext(), "我是222222222222", 0).show();
	}
	
	private class CallMethod extends Binder implements MyInterface{

		@Override
		public void callMethod() {
			//调用myMethod()
			method1();
		}

		@Override
		public void callMethod2() {
			method2();
		}
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	
	

}
