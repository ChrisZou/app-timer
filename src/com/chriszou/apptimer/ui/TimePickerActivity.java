/**
 * TimePickerActivity.java
 * 
 * Created by zouyong on 7:02:37 PM, 2014
 */
package com.chriszou.apptimer.ui;

import java.util.Calendar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.widget.TimePicker;

import com.chriszou.apptimer.R;

/**
 * @author zouyong
 *
 */
@EActivity(R.layout.time_picker_activity)
public class TimePickerActivity extends Activity{
    
    public static final String EXTRA_LONG_TIME = "extra_long_time";
    
    @ViewById(R.id.time_picker)
    TimePicker mTimePicker;
    
    @AfterViews
    void initViews() {
    	mTimePicker.setIs24HourView(true);
    }
    
	@Click(R.id.time_picker_ok) 
	void setTime(){
		Calendar c = Calendar.getInstance();
		int hour = mTimePicker.getCurrentHour();
        int minute = mTimePicker.getCurrentMinute();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        long time = c.getTimeInMillis();
        if(time<System.currentTimeMillis()) {
        	time = time+24*60*60*1000;
        }
        
        Intent intent = new Intent();
        intent.putExtra(EXTRA_LONG_TIME, time);
        setResult(RESULT_OK, intent);
        finish();
	}
}
