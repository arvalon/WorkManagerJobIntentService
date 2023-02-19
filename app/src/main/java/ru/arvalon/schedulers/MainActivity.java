package ru.arvalon.schedulers;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import ru.arvalon.schedulers.databinding.ActivityMainBinding;

/** Увлекательное исследование Workmanager'а и JobIntentService */
public class MainActivity extends AppCompatActivity {

	static final String TAG = "work.log";

	private static final int REQUEST_DANGEROUS_PERMISSIONS = 1;

	private static final int TIMEOUT = 3;

	static final String DATA_EXTRA = "extra_data";
	static final String DATA = "Hello World!";

	/** Попробовать что-нибудь кроме findviewbyid */
	ActivityMainBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		binding.startWorkmanagerBtn.setOnClickListener(view -> startWorkManager());

		binding.jobIntentserviceBtn.setOnClickListener(view -> startJobIntentService());
	}

	private void startWorkManager() {

		OneTimeWorkRequest myWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();

		WorkManager.getInstance().enqueue(myWorkRequest);

		WorkManager.getInstance().getWorkInfoByIdLiveData(myWorkRequest.getId())
				.observe(this, workInfo -> Log.d(TAG, "myWorkRequest, onChanged: " + workInfo.getState()));
	}

	private void startJobIntentService() {
		Log.d(TAG,"startJobIntentService");

		Intent intent = new Intent(this, MyJobIntentService.class);

		intent.putExtra(DATA_EXTRA, DATA);

		MyJobIntentService.enqueueWork(this, intent);
	}

	public static void foo(String name){

		Log.d(TAG, name+": start, timeout: "+TIMEOUT);

		try {
			TimeUnit.SECONDS.sleep(TIMEOUT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Log.d(TAG, name+": end");
	}
}
