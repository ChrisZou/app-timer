/**
 * AlarmExecutor.java
 * 
 * Created by zouyong on Jul 7, 2014,2014
 */
package com.chriszou.apptimer.alarm;

import android.content.Context;
import android.content.Intent;

import com.chriszou.apptimer.appmanager.AppManager;
import com.chriszou.apptimer.model.AlarmItem;

/**
 * @author zouyong
 *
 */
public class AlarmExecutor {
    public static void execute(Context context, AlarmItem item) {
    	//Start the application
        AppManager manager = new AppManager(context);
        Intent startIntent = manager.getStartIntent(item.packageName);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        context.startActivity(startIntent);
    }
}
