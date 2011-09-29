/*
 * Created by Lolay, Inc.
 * Copyright 2011 Lolay, Inc. All rights reserved.
 */
package com.lolay.tracker;

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
    public void setGlobalParameters(Map<Object, Object> globalParameters) {
        // do nothing
    }

    @Override
    public void logEvent(String name) {
        // do nothing
    }

    @Override
    public void logEventWithParams(String name, Map<Object, Object> parameters) {
        // do nothing
    }

    @Override
    public void logEventWithObjectsAndKeys(String name, Object... objectsAndKeys) {
        logEventWithParams(name, buildParamsFromObjectArray(objectsAndKeys));
    }

    @Override
    public void logPage(String name) {
        // do nothing
    }

    @Override
    public void logPageWithParams(String name, Map<Object, Object> parameters) {
        // do nothing
    }

    @Override
    public void logPageWithObjectsAndKeys(String name, Object... objectsAndKeys) {
        logPageWithParams(name, buildParamsFromObjectArray(objectsAndKeys));

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
    public void logException(Exception exception) {
        // do nothing
    }
}
