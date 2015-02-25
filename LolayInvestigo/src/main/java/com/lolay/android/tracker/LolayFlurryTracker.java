//
//  Copyright 2011, 2012, 2013 Lolay, Inc.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//
package com.lolay.android.tracker;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.flurry.android.Constants;
import com.flurry.android.FlurryAgent;

import java.util.HashMap;
import java.util.Map;

public class LolayFlurryTracker extends LolayBaseTracker {
	private static final String TAG = LolayFlurryTracker.class.getSimpleName();
	private String apiKey;
    private String platform;
    private Map<Object, Object> globalParametersValue;

    public LolayFlurryTracker(String apiKey, String version, Boolean logEnabled, Integer logLevel) {
    	this.apiKey = apiKey;
        this.platform = clientPlatform();
		FlurryAgent.setLogLevel(logLevel);
		FlurryAgent.setLogEnabled(logEnabled);
        FlurryAgent.setVersionName(version);
		Log.i(TAG, String.format("Initialized apiKey=%s,platform=%s", this.apiKey, this.platform));
    }

    public LolayFlurryTracker(String apiKey, String version) {
		this(apiKey, version, false, 10);
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
    public String getTrackerId() {
    	return Settings.Secure.ANDROID_ID;
	}

    @Override
    public void logEvent(Context context, String name) {
        logEventWithParams(context, name, null);
    }

    @Override
    public void logEventWithParams(Context context, String name, Map<Object, Object> parameters) {
		//FlurryAgent.onStartSession(context, this.apiKey);
        FlurryAgent.logEvent(name, buildParameters(parameters));
		//FlurryAgent.onEndSession(context);
    }

    @Override
    public void logPage(Context context, String name) {
        logPageWithParams(context, name, null);
    }

    @Override
    public void logPageWithParams(Context context, String name, Map<Object, Object> parameters) {

        //FlurryAgent.onStartSession(context, this.apiKey);
		FlurryAgent.logEvent(name, buildParameters(parameters));
        FlurryAgent.onPageView();
		//FlurryAgent.onEndSession(context);
	}

    @Override
    public void logException(Context context, Throwable throwable) {
		//FlurryAgent.onStartSession(context, this.apiKey);
		FlurryAgent.onError(throwable.getMessage(), throwable.getMessage(), throwable.getClass().getSimpleName());
		//FlurryAgent.onEndSession(context);
	}

    @Override
    public void logException(Context context, String errorId, String message, Throwable throwable) {
		//FlurryAgent.onStartSession(context, this.apiKey);
		FlurryAgent.onError(errorId,message, throwable.getClass().getName());
		//FlurryAgent.onEndSession(context);
	}

    public void setGlobalParametersValue(Map<Object, Object> globalParametersValue) {
        this.globalParametersValue = globalParametersValue;
    }

    @Override
    public void setGlobalParameter(Object key, Object object) {
        if (this.globalParametersValue == null) {
        	this.globalParametersValue = new HashMap<Object,Object>();
        }
        
        this.globalParametersValue.put(key, object);
    }
    
    @Override
    public void removeGlobalParameter(Object key) {
        if (this.globalParametersValue != null) {
        	this.globalParametersValue.remove(key);
        }
    }
    
    Map<String, String> buildParameters(Map<Object, Object> parameters) {
        Map<String, String> flurryParameters = new HashMap<String, String>(1 + (globalParametersValue != null ? globalParametersValue.size() : 0));
        if (parameters != null) {
        	for(Object o : parameters.keySet()) {
        		Object v = parameters.get(o);
        		flurryParameters.put(o.toString(), v.toString());
        	}
        }

        if (globalParametersValue != null && !globalParametersValue.isEmpty()) {
        	for(Object o : globalParametersValue.keySet()) {
        		Object v = globalParametersValue.get(o);
        		flurryParameters.put(o.toString(), v.toString());
        	}
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

    @Override
    public void onStart(Activity activity){
        super.onStart(activity);
        FlurryAgent.onStartSession(activity, this.apiKey);
    }

    @Override
    public void onStop(Activity activity){
        super.onStop(activity);
        FlurryAgent.onEndSession(activity);
    }
}
