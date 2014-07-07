/**
 * TimeHelper.java
 * 
 * Created by zouyong on Jul 7, 2014,2014
 */
package com.chriszou.apptimer.utils;

/**
 * @author zouyong
 *
 */
public class TimeHelper {
    /**
     * Determine whether time has expires
     * @param timeInMillis
     * @return
     */
    public static boolean timeExpires(long timeInMillis) {
    	return timeInMillis < System.currentTimeMillis();
    }
}
