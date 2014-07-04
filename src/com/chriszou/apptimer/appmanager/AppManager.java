/**
 * AppManager.java
 * 
 * Created by zouyong on 6:56:55 AM, 2014
 */
package com.chriszou.apptimer.appmanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;

/**
 * @author zouyong
 *
 */
public class AppManager{
    private Context mContext;
    private PackageManager mManager;
	public AppManager(Context context){
		mContext = context;
        mManager = mContext.getPackageManager();
	}
    
    /**
     * Get all the apps installed on this device
     * @param context
     * @return
     */
    public List<AppInfo> getAllApps(Context context) {
    	
    	//get a list of installed apps.
    	List<ApplicationInfo> packages = mManager.getInstalledApplications(PackageManager.GET_META_DATA);

        List<AppInfo> results = new ArrayList<AppInfo>();
    	for (ApplicationInfo packageInfo : packages) {
            Intent startIntent = mManager.getLaunchIntentForPackage(packageInfo.packageName);
            if(startIntent==null) {
            	continue;
            }
            
            AppInfo appInfo = new AppInfo();
            appInfo.packageName = packageInfo.packageName;
            appInfo.name = packageInfo.loadLabel(mManager).toString();
            results.add(appInfo);
    	}
        
    	return results;
    }
    
    /**
     * Get the app icon for the given package name
     * @param packageName
     * @return
     */
    public Drawable getAppIcon(String packageName){
        try {
			return mManager.getApplicationIcon(packageName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
        return null;
    }
    
    /**
     * Get the app name from given package name
     * @param pkgName
     * @return
     */
    public String getAppNameFromPkg(String pkgName){
    	try {
			return mManager.getApplicationLabel(mManager.getApplicationInfo(pkgName, 0)).toString();
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
        return "";
    }
    
    /**
     * Get the start intent for the given package name
     * @param pkg
     * @return
     */
    public Intent getStartIntent(String pkg) {
    	return mManager.getLaunchIntentForPackage(pkg);
    }
    
}
