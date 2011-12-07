package com.lolay.android.tracker;/*
 * Created by Lolay, Inc.
 * Copyright 2011 Lolay, Inc. All rights reserved.
 */

import java.util.Map;

public interface LolayTracker {
	public void startSession();
	public void endSession();
    public void setIdentifier(String identifier);
    public void setVersion(String version);
    public void setAge(int age);
    public void setGender(LolayTrackerGender gender);
    public void setState(String state);
    public void setZip(String zip);
    public void setCampaign(String campaign);
    public void setChannel(String channel);
    public void setGlobalParameters(Map<Object, Object> globalParameters);
    public void logEvent(String name);
    public void logEventWithParams(String name, Map<Object, Object> parameters);
    public void logEventWithObjectsAndKeys(String name, Object... objectsAndKeys);
    public void logPage(String name);
    public void logPageWithParams(String name, Map<Object, Object> parameters);
    public void logPageWithObjectsAndKeys(String name, Object... objectsAndKeys);
    public void logException(Throwable exception);
    public void logException(String errorId, String message, Throwable throwable);
    public String getTrackerId();
    public String getTrackerId(Class<?> clazz);
}
