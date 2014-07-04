package com.chriszou.apptimer.ui;

import android.app.Activity;
import android.os.Bundle;

import com.chriszou.apptimer.R;

public class MainActivity extends Activity {
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment_())
					.commit();
		}
	}


}
