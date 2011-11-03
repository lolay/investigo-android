/*
 * Created by Lolay, Inc.
 * Copyright 2011 Lolay, Inc. All rights reserved.
 */
package com.lolay.android.tracker;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import com.flurry.android.Constants;
import com.flurry.android.FlurryAgent;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LolayFlurryTracker extends LolayBaseTracker {
	private static final String TAG = LolayFlurryTracker.class.getSimpleName();
	private WeakReference<Application> applicationReference;
	private String apiKey;
    private String platform;
    private Map<Object, Object> globalParametersValue = Collections.emptyMap();

    public LolayFlurryTracker(Application application, String apiKey, String version) {
    	this.applicationReference = new WeakReference<Application>(application);
    	this.apiKey = apiKey;
        this.platform = clientPlatform();
        FlurryAgent.setVersionName(version);
		Log.i(TAG, String.format("Intialized apiKey=%s,platform=%s", this.apiKey, this.platform));
    }

    @Override
	public void startSession() {
    	Application application = applicationReference.get();
    	if (application != null) {
            FlurryAgent.onStartSession(application, this.apiKey);
    	}
	}
	
    @Override
	public void endSession() {
    	Application application = applicationReference.get();
    	if (application != null) {
            FlurryAgent.onEndSession(application);
    	}
	}
	
    @Override
    public void setIdentifier(String identifier) {
        FlurryAgent.setUserId(identifier);
    }

    @Override
    public void setVersion(String version) {
        FlurryAgent.setVersionName(version);
    }

    @Override
    public void setAge(int age) {
        FlurryAgent.setAge(age);
    }

    @Override
    public void setGender(LolayTrackerGender gender) {
        switch (gender) {
            case MALE:
                FlurryAgent.setGender(Constants.MALE);
                break;
            case FEMALE:
                FlurryAgent.setGender(Constants.FEMALE);
                break;
            case UNKNOWN:
                break;
        }
    }

    @Override
    public void logEvent(String name) {
        logEventWithParams(name, null);
    }

    @Override
    public void logEventWithParams(String name, Map<Object, Object> parameters) {
        FlurryAgent.logEvent(name, buildParameters(parameters));
    }

    @Override
    public void logPage(String name) {
        logPageWithParams(name, null);
    }

    @Override
    public void logPageWithParams(String name, Map<Object, Object> parameters) {
        FlurryAgent.logEvent(name, buildParameters(parameters));
        FlurryAgent.onPageView();
    }

    @Override
    public void logException(Throwable throwable) {
        FlurryAgent.onError(throwable.getMessage(), throwable.getMessage(), throwable.getClass().getSimpleName());
    }

    @Override
    public void logException(String errorId, String message, Throwable throwable) {
        FlurryAgent.onError(errorId,message, throwable.getClass().getName());
    }

    public Map<Object, Object> getGlobalParametersValue() {
        return globalParametersValue;
    }

    public void setGlobalParametersValue(Map<Object, Object> globalParametersValue) {
        this.globalParametersValue = globalParametersValue;
    }

    Map<Object, Object> buildParameters(Map<Object, Object> parameters) {
        Map<Object, Object> flurryParameters;

        if (parameters == null) {
            flurryParameters = new HashMap<Object, Object>(1 + globalParametersValue.size());
        } else {
            flurryParameters = parameters;
        }

        if (!getGlobalParametersValue().isEmpty()) {
            flurryParameters.putAll(globalParametersValue);
        }

        flurryParameters.put("platform", platform);
        
        return flurryParameters;
    }

    private String clientPlatform() {
        String manufacturer = Build.MANUFACTURER;
        String product = Build.PRODUCT;
        String model = Build.MODEL;
        String systemVersion = Build.VERSION.RELEASE;
        int sdk = Build.VERSION.SDK_INT;
        return String.format("%s %s (%s): %s %d", manufacturer, product, model, systemVersion, sdk);
    }
}
