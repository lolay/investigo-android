package com.lolay.android.tracker;/*
 * Created by Lolay, Inc.
 * Copyright 2011 Lolay, Inc. All rights reserved.
 */

import android.content.Context;

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
}
