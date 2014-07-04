///**
// * Timer.java
// * 
// * Created by zouyong on 10:44:41 AM, 2014
// */
//package com.chriszou.apptimer.model;
//
//import com.chriszou.apptimer.ui.AppTimerItem;
//import com.chriszou.apptimer.ui.TimerReceiver;
//
//import android.app.AlarmManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//
///**
// * @author zouyong
// *
// */
//public class AppTimer {
//    public static final String EXTRA_SERIAL_APP_TIMER_ITEM = "extra_serial_app_timer_item";
//    private AppTimerItem mItem;
//    private Context mContext;
//    public AppTimer(Context context, AppTimerItem item) {
//        mContext = context;
//    	this.mItem = item;
//    }
//    
//    /**
//     * Set the app timer to system Alarm
//     */
//    public void set() {
//    	Intent intent = new Intent(mContext, TimerReceiver.class);
//    	intent.putExtra(EXTRA_SERIAL_APP_TIMER_ITEM, mItem);
//        PendingIntent pi = PendingIntent.getBroadcast(mContext, mItem.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager am = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
//        am.set(AlarmManager.RTC_WAKEUP, mItem.time, pi);
//    }
//
//	/**
//	 * Cancel the app timer
//	 */
//	void cancel() {
//		Intent intent = new Intent(mContext, TimerReceiver.class);
//    	intent.putExtra(EXTRA_SERIAL_APP_TIMER_ITEM, mItem);
//        PendingIntent pi = PendingIntent.getBroadcast(mContext, mItem.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager am = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
//        am.cancel(pi);
//	}
//}
