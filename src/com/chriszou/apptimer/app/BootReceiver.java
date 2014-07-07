/**
 * BootReceiver.java
 * 
 * Created by zouyong on Jul 7, 2014,2014
 */
package com.chriszou.apptimer.app;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.chriszou.apptimer.alarm.AlarmHelper;
import com.chriszou.apptimer.model.AlarmItem;
import com.chriszou.apptimer.utils.TimeHelper;

/**
 * @author zouyong
 *
 */
public class BootReceiver extends BroadcastReceiver {

	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
        //Setup all alarms
        setupAlarms(context);
	}
    
	private void setupAlarms(Context context) {
		List<AlarmItem> items = AlarmItem.all();
        for(AlarmItem item: items) {
            if(TimeHelper.timeExpires(item.time)) {
            	item.time = item.getRepeatType().getNextValidTime(item.time);
            }
        	new AlarmHelper(context).setAlarm(item);
        }
	}

}
