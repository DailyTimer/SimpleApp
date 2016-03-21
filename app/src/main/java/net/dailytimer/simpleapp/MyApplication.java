package net.dailytimer.simpleapp;

import android.app.Application;
import android.content.Context;

import org.acra.*;
import org.acra.annotation.*;

@ReportsCrashes(
	formUri = "https://collector.tracepot.com/your_id",
	sharedPreferencesMode = Context.MODE_PRIVATE)

public class MyApplication extends Application
{
	@Override
	public void onCreate()
	{
		ACRA.init(this);
		super.onCreate();
	}
}
