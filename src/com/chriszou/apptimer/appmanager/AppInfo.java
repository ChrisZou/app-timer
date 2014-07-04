/**
 * AppInfo.java
 * 
 * Created by zouyong on 7:08:27 PM, 2014
 */
package com.chriszou.apptimer.appmanager;

import java.io.Serializable;

/**
 * @author zouyong
 *
 */
public class AppInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String packageName;
	
	public String name;
	
	@Override
	public boolean equals(Object o) {
        if(o!=null && o instanceof AppInfo) {
        	return ((AppInfo) o).packageName.equals(packageName);
        }
        return false;
	}
    
	
}
