/**
 * MyApplication.java
 * 
 * Created by zouyong on 5:16:38 PM, 2014
 */
package com.chriszou.apptimer.app;

import com.activeandroid.ActiveAndroid;
import com.chriszou.androidlibs.UtilApplication;

/**
 * @author zouyong
 *
 */
public class MyApplication extends UtilApplication {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ActiveAndroid.initialize(this);
	}
    
	@Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
