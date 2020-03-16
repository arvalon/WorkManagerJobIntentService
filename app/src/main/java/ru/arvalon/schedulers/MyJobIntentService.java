package ru.arvalon.schedulers;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import static ru.arvalon.schedulers.MainActivity.DATA_EXTRA;

public class MyJobIntentService extends JobIntentService {

	private static final int JOB_ID = 2;

	public static void enqueueWork(Context context, Intent intent) {
		enqueueWork(context, MyJobIntentService.class, JOB_ID, intent);
	}

	@Override
	protected void onHandleWork(@NonNull Intent intent) {

		String data = "No data";

		if (intent.hasExtra(DATA_EXTRA)){
			data = intent.getStringExtra(DATA_EXTRA);
		}

		MainActivity.foo("MyJobIntentService, data: "+data);
	}
}