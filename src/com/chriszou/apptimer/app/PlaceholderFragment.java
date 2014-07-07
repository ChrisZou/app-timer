/**
 * PlaceholderFragment.java
 * 
 * Created by zouyong on 6:14:18 PM, 2014
 */
package com.chriszou.apptimer.app;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.chriszou.apptimer.R;
import com.chriszou.apptimer.alarm.AlarmHelper;
import com.chriszou.apptimer.model.AlarmItem;
import com.chriszou.apptimer.model.AlarmItem.RepeatType;

/**
 * @author zouyong
 *
 */
@EFragment
public class PlaceholderFragment extends Fragment {

    @ViewById(R.id.main_add_timer)
    Button mAddButtom;
    @ViewById(R.id.main_listView)
    ListView mListView;
    
    private AlarmItem mAlarmItem;
    
	public PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		return rootView;
	}

	/**
	 * @param listView
	 */
	private void loadAppTimers() {
        List<AlarmItem> alarms = AlarmItem.all();
        AppTimerAdapter adapter = new AppTimerAdapter(getActivity(), alarms);
        mListView.setAdapter(adapter);
	}
    
	private void reloadAlarms() {
		loadAppTimers();
	}
    
	@AfterViews
	void initViews() {
		loadAppTimers();
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlarmItem item = (AlarmItem) parent.getItemAtPosition(position);
                item.delete();
                AppTimerAdapter adapter = (AppTimerAdapter)parent.getAdapter();
                adapter.remove(position);
                return true;
			}
		});
	}
    
    private static final int REQUEST_SELECT_APP = 1;
    private static final int REQUEST_PICK_TIME = 2;
    
	@Click(R.id.main_add_timer)
	void addTimer() {
		startActivityForResult(new Intent(getActivity(), AppSelectorActivity_.class), REQUEST_SELECT_APP);
	}
    
	private void pickTime() {
		Intent intent = new Intent(getActivity(), TimePickerActivity_.class);
        startActivityForResult(intent, REQUEST_PICK_TIME);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_SELECT_APP&&resultCode==Activity.RESULT_OK) {
            String packageName = data.getStringExtra(AppSelectorActivity.RESULT_PACKAGE);
        	mAlarmItem = new AlarmItem(packageName, 0, RepeatType.DAILY);
        	pickTime();
        }
        if(requestCode==REQUEST_PICK_TIME&&resultCode==Activity.RESULT_OK) {
            long time = data.getLongExtra(TimePickerActivity.EXTRA_LONG_TIME, 0);
            mAlarmItem.time = time;
            mAlarmItem.save();
            new AlarmHelper(getActivity()).setAlarm(mAlarmItem);
            reloadAlarms();
        }
		super.onActivityResult(requestCode, resultCode, data);
	}

}
