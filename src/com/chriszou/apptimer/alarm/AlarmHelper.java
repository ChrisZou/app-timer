/**
 * AlarmHelper.java
 * 
 * Created by zouyong on Jul 4, 2014,2014
 */
package com.chriszou.apptimer.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.chriszou.apptimer.app.TimerReceiver;
import com.chriszou.apptimer.model.AlarmItem;

/**
 * @author zouyong
 *
 */
public class AlarmHelper {
    
    public static final String EXTRA_LONG_ITEM_ID = "extra_long_item_id";
    private Context mContext;
    private AlarmManager mAlarmManager;
    
    /**
	 * @param context
	 */
	public AlarmHelper(Context context) {
        mContext = context;
        mAlarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
	}
    
	private AlarmManager getAlarmManager() {
        if(mAlarmManager==null) {
        	mAlarmManager = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        }
        return mAlarmManager;
	}

	public void setAlarm(AlarmItem item) {
        getAlarmManager().set(AlarmManager.RTC_WAKEUP, item.time, getPendingIntent(mContext, item));
    }
    
    public void cancelAlarm(AlarmItem item) {
        getAlarmManager().cancel(getPendingIntent(mContext, item));
    }
    
    /**
     * Get a PendingIntent from an AlarmItem
     * @param context
     * @param item
     * @return
     */
    private PendingIntent getPendingIntent(Context context, AlarmItem item) {
    	Intent intent = new Intent(context, TimerReceiver.class);
    	intent.putExtra(AlarmItem.EXTRA_SERIAL_APP_TIMER_ITEM, item);
        intent.putExtra(EXTRA_LONG_ITEM_ID, item.getId().longValue());
        PendingIntent pi = PendingIntent.getBroadcast(context, item.getId().intValue(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pi;
    }
}
