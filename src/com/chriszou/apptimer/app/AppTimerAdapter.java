/**
 * AppTimerAdapter.java
 * 
 * Created by zouyong on 11:28:40 AM, 2014
 */
package com.chriszou.apptimer.app;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chriszou.androidlibs.BaseListAdapter;
import com.chriszou.androidlibs.CalendarUtil;
import com.chriszou.apptimer.R;
import com.chriszou.apptimer.appmanager.AppManager;
import com.chriszou.apptimer.model.AlarmItem;

/**
 * @author zouyong
 *
 */
public class AppTimerAdapter extends BaseListAdapter<AlarmItem>{

	private AppManager mManager;

	/**
	 * @param context
	 * @param data
	 */
	public AppTimerAdapter(Context context, List<AlarmItem> data) {
		super(context, data);
        mManager = new AppManager(context);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
        	convertView = mInflater.inflate(R.layout.app_timer_item, null);
        }
        AlarmItem item = getItem(position);
        TextView nameView = (TextView) convertView.findViewById(R.id.app_item_name);
        nameView.setText(mManager.getAppNameFromPkg(item.packageName));
        TextView pkgView = (TextView)convertView.findViewById(R.id.app_item_package);
        pkgView.setText(item.packageName);
        ImageView iconView = (ImageView)convertView.findViewById(R.id.app_item_icon);
        iconView.setImageDrawable(mManager.getAppIcon(item.packageName));
        TextView timeView = (TextView)convertView.findViewById(R.id.app_timer_item_time);
        
        int hour = CalendarUtil.getHour24(item.time);
        int minute = CalendarUtil.getMinute(item.time);
        timeView.setText(hour+":"+minute);
        
        return convertView;
	}

}
