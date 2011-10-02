package com.lolay.tracker;/*
 * Created by Lolay, Inc.
 * Copyright 2011 Lolay, Inc. All rights reserved.
 */

import java.util.Map;

public interface LolayTracker {
    
    void setIdentifier(String identifier);
    void setVersion(String version);
    void setAge(int age);
    void setGender(LolayTrackerGender gender);
    void setState(String state);
    void setZip(String zip);
    void setCampaign(String campaign);
    void setGlobalParameters(Map<Object, Object> globalParameters);
    void logEvent(String name);
    void logEventWithParams(String name, Map<Object, Object> parameters);
    void logEventWithObjectsAndKeys(String name, Object... objectsAndKeys);
    void logPage(String name);
    void logPageWithParams(String name, Map<Object, Object> parameters);
    void logPageWithObjectsAndKeys(String name, Object... objectsAndKeys);
    void logException(Exception exception);
    void logException(String errorId, String message, String errorClass);

}
