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
import android.os.Bundle;

import java.util.Map;

public interface LolayTracker {
    public void setIdentifier(String identifier);
    public void setVersion(String version);
    public void setAge(int age);
    public void setGender(LolayTrackerGender gender);
    public void setState(String state);
    public void setZip(String zip);
    public void setCampaign(String campaign);
    public void setChannel(String channel);
    public void setGlobalParameters(Map<Object, Object> globalParameters);
    public void setGlobalParameter(Object key, Object object);
    public void removeGlobalParameter(Object key);
    public void logRegistration(Context context, Map<Object, Object> registrationData);
    public void logPurchase(Context context, Map<Object, Object> purchaseData);
    public void logTiming(Context context, Map<Object, Object> timingData);
    public void logEvent(Context context, String name);
    public void logEventWithParams(Context context, String name, Map<Object, Object> parameters);
    public void logEventWithObjectsAndKeys(Context context, String name, Object... objectsAndKeys);
    public void logPage(Context context, String name);
    public void logPageWithParams(Context context, String name, Map<Object, Object> parameters);
    public void logPageWithObjectsAndKeys(Context context, String name, Object... objectsAndKeys);
    public void logException(Context context, Throwable exception);
    public void logException(Context context, String errorId, String message, Throwable throwable);
    public String getTrackerId();
    public String getTrackerId(Class<?> clazz);

    // Activity life cycle hooks.
    public void onCreate(Activity activity, Bundle savedInstanceState);
    public void onStart(Activity activity);
    public void onRestart(Activity activity);
    public void onResume(Activity activity);
    public void onPause(Activity activity);
    public void onStop(Activity activity);
    public void onDestroy(Activity activity);
}
