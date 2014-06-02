package com.lolay.android.tracker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import io.segment.android.Analytics;
import io.segment.android.Options;
import io.segment.android.models.Traits;
import io.segment.android.models.Props;

/**
 * Created by Kaz on 4/29/2014.
 *
 * Wrap around class for segmen.io interface. This class implements LolayTracker interface for segmen.io (analytics)
 */
public class LolayAnalyticsTracker extends LolayBaseTracker {
    private static final String TAG = LolayLogTracker.class.getSimpleName();

    private Traits traits = new Traits();
    private String userId;

    private Traits getTraits() { return traits; }
    private String getUserId() { return userId; }

    public LolayAnalyticsTracker(Context context, String apiKey, String version, Boolean logEnabled) {
        Options options = new Options();
        options.setDebug(logEnabled);

        Analytics.initialize(context, apiKey, options);

    }

    private Props buildParameters(Map<Object, Object> parameters) {
        Props props =  new Props();

        if (parameters == null || parameters.size() == 0) {
            return props;
        }

        for (Map.Entry<Object,Object> entry : parameters.entrySet()) {
            if (entry!=null && entry.getKey()!=null && entry.getValue()!=null)
                props.put(entry.getKey().toString(), entry.getValue());
        }

        return props;
    }

    private void updateIdentity()
    {
        Analytics.identify(getUserId(), getTraits());
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
        Analytics.track(name);
    }

    @Override
    public void logEventWithParams(Context context, String name, Map<Object, Object> parameters) {
        Analytics.track(name, buildParameters(parameters));
    }

    @Override
    public void logPage(Context context, String name) {
        Analytics.screen(name);
    }

    @Override
    public void logPageWithParams(Context context, String name, Map<Object, Object> parameters) {
        Analytics.screen(name, buildParameters(parameters));
    }

    @Override
    public void logException(Context context, Throwable throwable) {
        Analytics.track(context.getClass().getName() + " Exception");
    }

    @Override
    public void logException(Context context, String errorId, String message, Throwable throwable) {
        Analytics.track(context.getClass().getName() + " Exception: " + errorId + ": " + message);
    }

    @Override
    public void onCreate(Activity activity, Bundle savedInstanceState) {
        Analytics.onCreate(activity);
    }

    @Override
    public void onStart(Activity activity){
        Analytics.activityStart(activity);
    }

    @Override
    public void onRestart(Activity activity){
        // do nothing
    }

    @Override
    public void onResume(Activity activity){
        Analytics.activityResume(activity);
    }

    @Override
    public void onPause(Activity activity){
        Analytics.activityPause(activity);
    }

    @Override
    public void onStop(Activity activity){
        Analytics.activityStop(activity);
    }


}
