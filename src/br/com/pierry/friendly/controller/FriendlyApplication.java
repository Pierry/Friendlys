package br.com.pierry.friendly.controller;

import android.app.Application;
import android.content.Context;

import com.littlefluffytoys.littlefluffylocationlibrary.LocationLibrary;

public class FriendlyApplication extends Application {

	private static Context context;

	public void onCreate(){
		super.onCreate();
		LocationLibrary.showDebugOutput(true);
		LocationLibrary.initialiseLibrary(getBaseContext(), 0, 2 * 60 * 1000, "br.com.ravex.vexpromoter.broadcast");		
		FriendlyApplication.context = getApplicationContext();			
	}

	public static Context getAppContext() {
		return FriendlyApplication.context;
	}
}