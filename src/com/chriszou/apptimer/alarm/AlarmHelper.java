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

import com.chriszou.apptimer.model.AlarmItem;
import com.chriszou.apptimer.ui.TimerReceiver;

/**
 * @author zouyong
 *
 */
public class AlarmHelper {
    
    public static final String EXTRA_LONG_ITEM_ID = "extra_long_item_id";
    
    public void setAlarm(Context context, AlarmItem item) {
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, item.time, getPendingIntent(context, item));
    }
    
    public void cancelAlarm(Context context, AlarmItem item) {
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(getPendingIntent(context, item));
    }
    
    private PendingIntent getPendingIntent(Context context, AlarmItem item) {
    	Intent intent = new Intent(context, TimerReceiver.class);
    	intent.putExtra(AlarmItem.EXTRA_SERIAL_APP_TIMER_ITEM, item);
        intent.putExtra(EXTRA_LONG_ITEM_ID, item.getId().longValue());
        PendingIntent pi = PendingIntent.getBroadcast(context, item.getId().intValue(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pi;
    }
}
