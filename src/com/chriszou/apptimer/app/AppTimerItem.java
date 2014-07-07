/**
 * AppTimerItem.java
 * 
 * Created by zouyong on 4:55:53 PM, 2014
 */
package com.chriszou.apptimer.app;

import java.io.Serializable;

import com.chriszou.androidlibs.Prefs;
import com.chriszou.apptimer.appmanager.AppInfo;
import com.chriszou.apptimer.model.AlarmItem.RepeatType;

/**
 * @author zouyong
 *
 */
public class AppTimerItem implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    public long time;
    public RepeatType repeatType;
    public AppInfo appInfo;
	public AppTimerItem(long time, RepeatType repeatType, AppInfo appInfo) {
		this.time = time;
		this.repeatType = repeatType;
		this.appInfo = appInfo;
        id = newId();
	}
	
	public AppTimerItem(AppInfo info) {
		this(0, RepeatType.NONE, info);
	}

	@Override
	public boolean equals(Object o) {
		if(o!=null && o instanceof AppTimerItem) {
            AppTimerItem item = (AppTimerItem)o; 
			return item.appInfo.equals(this.appInfo) && item.time == this.time;
		}
        return false;
	}
    
	public int getId() {
		return id;
	}
    
    private static final String PREF_INT_ID = "pref_int_id";
	private int newId() {
        int id = Prefs.getInt(PREF_INT_ID, 0);
        Prefs.putInt(PREF_INT_ID, id+1);
        return id;
	}
}
