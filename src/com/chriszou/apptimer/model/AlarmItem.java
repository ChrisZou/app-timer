/**
 * AlarmDatabaseItem.java
 * 
 * Created by zouyong on Jul 4, 2014,2014
 */
package com.chriszou.apptimer.model;

import java.io.Serializable;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * @author zouyong
 *
 */
@Table(name="alarms")
public class AlarmItem extends Model implements Serializable{
	private static final long serialVersionUID = 3812272325712750545L;

	public static final String EXTRA_SERIAL_APP_TIMER_ITEM = "extra_serial_app_timer_item";

    @Column(name="package_name")
    public String packageName;
    
    @Column(name="time")
    public long time;
    
    @Column(name="repeat_type")
    public String repeatType;
    
    public AlarmItem() {
    }
    
    public AlarmItem(String pkg, long time, RepeatType type){
    	this.packageName = pkg;
    	this.time =time;
        setRepeatType(type);
    }
    /**
     * Get all the Alarms in the database
     * @return
     */
    public static List<AlarmItem> all() {
    	return new Select().from(AlarmItem.class).execute();
    }
    
    public void setRepeatType(RepeatType type) {
    	repeatType = type.name();
    }
    
    public RepeatType getRepeatType() {
    	return RepeatType.fromString(repeatType);
    }
    
    public static enum RepeatType{
    	NONE(0) {
			@Override
			public long getNextValidTime(long time) {
                return time;
			}
		}, DAILY(24*60*60*1000) {
			@Override
			public long getNextValidTime(long time) {
                while(time<System.currentTimeMillis()) {
                	time = time+ this.durationMillis;
                }
                return time;
			}
		};
    	
    	public static RepeatType fromString(String name) {
            for(RepeatType type:values()) {
            	if(type.name().equals(name)) {
            		return type;
            	}
            }
            return null;
    	}
        
    	final long durationMillis;
        /**
		 * 
		 */
		private RepeatType(long durationMillis) {
            this.durationMillis = durationMillis;
		}

		/**
		 * @param time
		 * @return
		 */
		public abstract long getNextValidTime(long time);
    }
}
