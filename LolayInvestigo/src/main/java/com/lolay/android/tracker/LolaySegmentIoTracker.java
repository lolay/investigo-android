package com.lolay.android.tracker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.segment.analytics.Analytics;
import com.segment.analytics.Options;
import com.segment.analytics.Properties;
import com.segment.analytics.Traits;

import java.util.Map;


/**
 * Created by Kaz on 4/29/2014.
 *
 * @update Josh Musselwhite on 2/24/2015. Updated segment apis and changed class name
 *
 * Wrap around class for segment.io interface. This class implements LolayTracker interface for segmen.io (analytics)
 */
public class LolaySegmentIoTracker extends LolayBaseTracker {
    private static final String TAG = LolayLogTracker.class.getSimpleName();

    private Traits traits;
    private String userId;
	private Analytics analytics;

    private Traits getTraits() { return traits; }
    private String getUserId() { return userId; }

    public LolaySegmentIoTracker(Context context, String apiKey, String version, Boolean logEnabled) {
		traits = new Traits();
		Analytics.Builder builder = new Analytics.Builder(context, apiKey);
		builder.debugging(logEnabled);
		Analytics.setSingletonInstance(analytics = builder.build());

//		options.setDebug(logEnabled);
//        Analytics.initialize(context, apiKey, options);

    }

    private Properties buildParameters(Map<Object, Object> parameters) {
		Properties props =  new Properties();

        if (parameters == null || parameters.size() == 0) {
            return props;
        }

        for (Map.Entry<Object,Object> entry : parameters.entrySet()) {
            if (entry!=null && entry.getKey()!=null && entry.getValue()!=null)
                props.put(entry.getKey().toString(), entry.getValue());
        }

        return props;
    }

    private void updateIdentity() {
		analytics.identify(getUserId(), getTraits(), new Options());
//        Analytics.identify(getUserId(), getTraits());
    }

    @Override
    public void setIdentifier(String identifier) {
        userId = identifier;
        updateIdentity();
    }

    @Override
    public void setAge(int age) {
        getTraits().put("age", age);
        updateIdentity();
    }

    @Override
    public void logEvent(Context context, String name) {
		Analytics.with(context).track(name);
//        Analytics.track(name);
    }

    @Override
    public void logEventWithParams(Context context, String name, Map<Object, Object> parameters) {
        Analytics.with(context).track(name, buildParameters(parameters));
    }

    @Override
    public void logPage(Context context, String name) {
        Analytics.with(context).screen(name, name);
    }

    @Override
    public void logPageWithParams(Context context, String name, Map<Object, Object> parameters) {
        Analytics.with(context).screen(name, name, buildParameters(parameters));
    }

    @Override
    public void logException(Context context, Throwable throwable) {
        Analytics.with(context).track(context.getClass().getName() + " Exception");
    }

    @Override
    public void logException(Context context, String errorId, String message, Throwable throwable) {
        Analytics.with(context).track(context.getClass().getName() + " Exception: " + errorId + ": " + message);
    }

    @Override
    public void onCreate(Activity activity, Bundle savedInstanceState) {
//        Analytics.with(ac).onCreate(activity);
    }

    @Override
    public void onStart(Activity activity){
//        Analytics.activityStart(activity);
    }

    @Override
    public void onRestart(Activity activity){
        // do nothing
    }

    @Override
    public void onResume(Activity activity){
//        Analytics.activityResume(activity);
    }

    @Override
    public void onPause(Activity activity){
//        Analytics.activityPause(activity);
    }

    @Override
    public void onStop(Activity activity){
//        Analytics.activityStop(activity);
    }


}
