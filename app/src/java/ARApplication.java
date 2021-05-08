package com.zaker.android.sapeh;

import android.content.Context;
import android.os.Handler;
import com.akexorcist.localizationactivity.ui.LocalizationApplication;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.zaker.android.sapeh.app.main.easysharedpreferences.EasySharedPreferenceConfig;
import com.zaker.android.sapeh.util.AndroidUtils;
import org.jetbrains.annotations.NotNull;
import java.util.Locale;

import timber.log.Timber;

public class ARApplication extends LocalizationApplication {

	private static String PACKAGE_NAME ;
	public static volatile Handler applicationHandler;

	/** Screen width in dp */
	private static float screenWidthDp = 0;

	public static Injector injector;

	private static boolean isRecording = false;

	public static Injector getInjector() {
		return injector;
	}

	public static String appPackage() {
		return PACKAGE_NAME;
	}

	/**
	 * Calculate density pixels per second for record duration.
	 * Used for visualisation waveform in view.
	 * @param durationSec record duration in seconds
	 */
	public static float getDpPerSecond(float durationSec) {
		if (durationSec > AppConstants.LONG_RECORD_THRESHOLD_SECONDS) {
			return AppConstants.WAVEFORM_WIDTH * screenWidthDp / durationSec;
		} else {
			return AppConstants.SHORT_RECORD_DP_PER_SECOND;
		}
	}

	public static int getLongWaveformSampleCount() {
		return (int)(AppConstants.WAVEFORM_WIDTH * screenWidthDp);
	}

	@Override
	public void onCreate() {
		if (BuildConfig.DEBUG) {
			//Timber initialization
			Timber.plant(new Timber.DebugTree() {
				@Override
				protected String createStackElementTag(StackTraceElement element) {
					return "AR-AR " + super.createStackElementTag(element) + ":" +
							element.getLineNumber();
				}
			});

		}

		super.onCreate();
		FirebaseApp.initializeApp(getApplicationContext());
		FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);

		PACKAGE_NAME = getApplicationContext().getPackageName();
		applicationHandler = new Handler(getApplicationContext().getMainLooper());
		screenWidthDp = AndroidUtils.pxToDp(AndroidUtils.getScreenWidth(getApplicationContext()));
		injector = new Injector(getApplicationContext());
		EasySharedPreferenceConfig.Companion.initDefault(new EasySharedPreferenceConfig.Builder()
				.inputFileName("easy_preference")
				.inputMode(Context.MODE_PRIVATE)
				.build());

	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		Timber.v("onTerminate");
		injector.releaseMainPresenter();
		injector.closeTasks();
	}

	public static boolean isRecording() {
		return isRecording;
	}

	public static void setRecording(boolean recording) {
		ARApplication.isRecording = recording;
	}

	@NotNull
	@Override
	public Locale getDefaultLanguage() {
		return new Locale("ar", "EG");
	}
}