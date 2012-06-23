/*
 * Created by Lolay, Inc.
 * Copyright 2011 Lolay, Inc. All rights reserved.
 */
package com.lolay.android.tracker;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class LolayBaseTracker implements LolayTracker {
    @Override
    public void setIdentifier(String identifier) {
        // do nothing
    }

    @Override
    public void setVersion(String version) {
        // do nothing
    }

    @Override
    public void setAge(int age) {
        // do nothing
    }

    @Override
    public void setGender(LolayTrackerGender gender) {
        // do nothing
    }

    @Override
    public void setState(String state) {
        // do nothing
    }

    @Override
    public void setZip(String zip) {
        // do nothing
    }

    @Override
    public void setCampaign(String campaign) {
        // do nothing
    }
    
    @Override
    public void setChannel(String channel) {
    	// do nothing
    }

    @Override
    public void setGlobalParameters(Map<Object, Object> globalParameters) {
        // do nothing
    }

    @Override
    public void setGlobalParameter(Object key, Object object) {
        // do nothing
    }
    
    @Override
    public void removeGlobalParameter(Object key) {
        // do nothing
    }
    
    @Override
    public void logEvent(Context context, String name) {
        // do nothing
    }

    @Override
    public void logEventWithParams(Context context, String name, Map<Object, Object> parameters) {
        // do nothing
    }

    @Override
    public void logEventWithObjectsAndKeys(Context context, String name, Object... objectsAndKeys) {
        logEventWithParams(context, name, buildParamsFromObjectArray(objectsAndKeys));
    }

    @Override
    public void logPage(Context context, String name) {
        // do nothing
    }

    @Override
    public void logPageWithParams(Context context, String name, Map<Object, Object> parameters) {
        // do nothing
    }

    @Override
    public void logPageWithObjectsAndKeys(Context context, String name, Object... objectsAndKeys) {
        logPageWithParams(context, name, buildParamsFromObjectArray(objectsAndKeys));

    }

    /**
     * For an Object array of [k1, v1, k2, v2, k3, v3, ...], take each k[i]/v[i]
     * @param objectsAndKeys
     * @return
     */
    Map<Object, Object> buildParamsFromObjectArray(Object... objectsAndKeys) {
        int length = objectsAndKeys.length / 2;
        Map<Object, Object> parameters = new HashMap<Object, Object>(length);

        for ( int i = 0; i < length; i++){
            int keyIndex = 2 * i;
            int valueIndex = keyIndex + 1;
            Object key = objectsAndKeys[keyIndex];
            Object value = objectsAndKeys[valueIndex];
            if (key != null) {
                parameters.put(key, value);
            }
        }
        return parameters;
    }

    @Override
    public void logException(Context context, Throwable throwable) {
        // do nothing
    }

    @Override
    public void logException(Context context, String errorId, String message, Throwable throwable) {
        // do nothing
    }

    @Override
    public String getTrackerId() {
    	return null;
    }
    
    @Override
    public String getTrackerId(Class<?> clazz) {
    	if (clazz.isInstance(this)) {
    		return getTrackerId();
    	}
    	return null;
    }
}
