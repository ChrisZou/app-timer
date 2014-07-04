/**
 * AppSelectorActivity.java
 * 
 * Created by zouyong on 4:27:25 PM, 2014
 */
package com.chriszou.apptimer.ui;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.chriszou.apptimer.R;
import com.chriszou.apptimer.appmanager.AppInfo;
import com.chriszou.apptimer.appmanager.AppManager;

/**
 * @author zouyong
 *
 */
@EActivity(R.layout.app_selector_activity)
public class AppSelectorActivity extends Activity {
    @ViewById(R.id.app_selector_listview)
    ListView mListView;
    
    public static final String RESULT_PACKAGE = "result_package";
    
	/**
	 * Get the installed app list and show it in a ListView
	 */
    @AfterViews
	void loadApps() {
		List<AppInfo> apps = new AppManager(this).getAllApps(this);
		mListView.setAdapter(new AppsAdapter(this, apps));
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo info = (AppInfo) parent.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.putExtra(RESULT_PACKAGE, info.packageName);
                setResult(Activity.RESULT_OK, intent);
                finish();
			}
		});
	}
}
