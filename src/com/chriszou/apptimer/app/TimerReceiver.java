/**
 * TimerReceiver.java
 * 
 * Created by zouyong on 10:46:24 AM, 2014
 */
package com.chriszou.apptimer.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.chriszou.apptimer.alarm.AlarmExecutor;
import com.chriszou.apptimer.alarm.AlarmHelper;
import com.chriszou.apptimer.model.AlarmItem;

/**
 * @author zouyong
 *
 */
public class TimerReceiver extends BroadcastReceiver {

	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
        AlarmItem item = (AlarmItem) intent.getSerializableExtra(AlarmItem.EXTRA_SERIAL_APP_TIMER_ITEM);
        long itemId = intent.getLongExtra(AlarmHelper.EXTRA_LONG_ITEM_ID, 0);
        //Set the next timer for this item
        switch (item.getRepeatType()) {
		case DAILY:
            AlarmItem newItem = new AlarmItem(item.packageName, item.time, item.getRepeatType());
			newItem.time += 24*60*60*1000;
            newItem.save();
            new AlarmHelper(context).setAlarm(newItem);
			break;
		case NONE:
            break;
		default:
			break;
		}
        
        AlarmItem.delete(AlarmItem.class, itemId);
        performAlarmAction(context, item);
	}
    
	private void performAlarmAction(Context context, AlarmItem item) {
		AlarmExecutor.execute(context, item);
	}

}
