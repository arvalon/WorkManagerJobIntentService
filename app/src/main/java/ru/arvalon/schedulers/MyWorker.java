package ru.arvalon.schedulers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

	private static final int TIMEOUT = 3;

	public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
		super(context, workerParams);
	}

	@NonNull
	@Override
	public Result doWork() {

		MainActivity.foo("MyWorker");

		return Result.success();
	}
}
