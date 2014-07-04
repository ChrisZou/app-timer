///**
// * AppTimerModel.java
// * 
// * Created by zouyong on 5:15:38 PM, 2014
// */
//package com.chriszou.apptimer.model;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.TreeSet;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//
//import com.chriszou.apptimer.ui.AppTimerItem;
//import com.google.gson.Gson;
//
///**
// * @author zouyong
// *
// */
//public class AppTimerModel {
//	private static List<AppTimerItem> sTimers;
//    private Context mContext;
//    
//    private static AppTimerModel sInstance;
//    private AppTimerModel(Context context) {
//    	mContext = context;
//    	sTimers = new ArrayList<AppTimerItem>();
//        read();
//    }
//    
//    public static AppTimerModel getInstance(Context context) {
//		if(sInstance==null) {
//			sInstance = new AppTimerModel(context);
//		}
//        return sInstance;
//	}
//    
//    private void read() {
//    	SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);
//        Set<String> timers = pref.getStringSet("TIMERS", new TreeSet<String>());
//        Gson gson = new Gson();
//        for(String timer:timers) {
//        	AppTimerItem item = gson.fromJson(timer, AppTimerItem.class);
//            sTimers.add(item);
//        }
//    }
//
//    public List<AppTimerItem> getAll() {
//        List<AppTimerItem> results = new ArrayList<AppTimerItem>();
//        results.addAll(sTimers);
//        return results;
//    }
//    
//    public void add(AppTimerItem item){
//        sTimers.add(item);
//        save();
//        
//        new AppTimer(mContext, item).set();
//    }
//    
//    private void save() {
//    	Set<String> timers = new HashSet<String>();
//        for(AppTimerItem item:sTimers) {
//        	Gson gson = new Gson();
//            timers.add(gson.toJson(item));
//        }
//        
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);
//        pref.edit().putStringSet("TIMERS", timers);
//    }
//
//	/**
//	 * @param item
//	 */
//	public void remove(AppTimerItem item) {
//		sTimers.remove(item);
//        save();
//        new AppTimer(mContext, item).cancel();
//	}
//}
