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
import android.content.IntentFilter;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Iterator;
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
    public void logRegistration(Context context, Map<Object, Object> registrationData) {
        // do nothing
    }

    @Override
    public void logPurchase(Context context, Map<Object, Object> purchaseData) {
        // do nothing
    }

    @Override
    public void logTiming(Context context, Map<Object, Object> timingData)
    {
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

    // Activity life cycle
    public void onCreate(Activity activity, Bundle savedInstanceState) {
        // do nothing
    }

    public void onStart(Activity activity){
        // do nothing
    }

    public void onRestart(Activity activity){
        // do nothing
    }

    public void onResume(Activity activity){
        // do nothing
    }

    public void onPause(Activity activity){
        // do nothing
    }

    public void onStop(Activity activity){
        // do nothing
    }

    public void onDestroy(Activity activity){
        // do nothing
    }

}
