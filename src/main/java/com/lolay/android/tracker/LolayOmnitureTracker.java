/*
 * Created by Lolay, Inc.
 * Copyright 2011 Lolay, Inc. All rights reserved.
 */
package com.lolay.android.tracker;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import android.app.Application;

import com.omniture.AppMeasurement;

public class LolayOmnitureTracker extends LolayBaseTracker {
	private AppMeasurement s;
	private Hashtable<String,String> globalParameters = null;
	private Delegate delegate = null;
	
	public LolayOmnitureTracker(Application application, String trackingServer, String account) {
		s = new AppMeasurement(application);
		s.trackingServer = trackingServer;
		s.account = account;
	}
	
	public AppMeasurement getAppMeasurement() {
		return s;
	}
	
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}
	
	public void setDebug(boolean debug) {
		s.debugTracking = debug;
	}
	
	public void setCurrency(String currency) {
		s.currencyCode = currency;
	}
	
    @Override
    public void setIdentifier(String identifier) {
    	s.visitorID = identifier;
    	if (delegate != null) {
    		delegate.identifierWasSet(this, identifier);
    	}
    }

    @Override
    public void setVersion(String version) {
    	if (delegate != null) {
    		delegate.setVersion(this, version);
    	}
    }

    @Override
    public void setAge(int age) {
    	if (delegate != null) {
    		delegate.setAge(this, age);
    	}
    }

    @Override
    public void setGender(LolayTrackerGender gender) {
    	if (delegate != null) {
    		delegate.setGender(this, gender);
    	}
    }

    @Override
    public void setState(String state) {
    	s.state = state;
    	if (delegate != null) {
    		delegate.stateWasSet(this, state);
    	}
    }

    @Override
    public void setZip(String zip) {
    	s.zip = zip;
    	if (delegate != null) {
    		delegate.zipWasSet(this, zip);
    	}
    }

    @Override
    public void setCampaign(String campaign) {
    	s.campaign = campaign;
    	if (delegate != null) {
    		delegate.campaignWasSet(this, campaign);
    	}
    }
    
    @Override
    public String getTrackerId() {
    	return s.visitorID;
    }
    
    private Hashtable<String,String> stringHashtable(Map<Object,Object> objectMap) {
    	if (this.globalParameters == null) {
    		return null;
    	}
    	if (objectMap == null) {
    		return this.globalParameters;
    	}
    	Hashtable<String,String> stringMap = new Hashtable<String,String>(this.globalParameters.size());
    	for (Map.Entry<Object, Object> entry : objectMap.entrySet()) {
    		Object keyObject = entry.getKey();
    		Object valueObject = entry.getValue();
    		
    		String key = keyObject instanceof String ? (String) keyObject : keyObject.toString();
    		String value = valueObject instanceof String ? (String) valueObject : valueObject.toString();
    		stringMap.put(key, value);
    	}
    	return stringMap;
    }
    
    private Map<Object,Object> objectMap(Hashtable<String,String> hashtable) {
    	if (hashtable == null) {
    		return null;
    	}
    	
    	Map<Object,Object> map = new HashMap<Object,Object>(hashtable.size());
    	for (Map.Entry<String,String> entry : hashtable.entrySet()) {
    		map.put(entry.getKey(), entry.getValue());
    	}
    	
    	return map;
    }

    @Override
    public void setChannel(String channel) {
    	s.channel = channel;
    	if (delegate != null) {
    		delegate.channelWasSet(this, channel);
    	}
    }
    
    @Override
    public void setGlobalParameters(Map<Object, Object> globalParameters) {
    	this.globalParameters = globalParameters == null ? null : stringHashtable(globalParameters);
    	this.globalParameters = stringHashtable(globalParameters);
    	if (delegate != null) {
    		delegate.globalParametersWasSet(this, globalParameters);
    	}
    }
    
    @Override
    public void setGlobalParameter(Object key, Object object) {
        if (this.globalParameters == null) {
        	this.globalParameters = new Hashtable<String,String>();
        }
        
        this.globalParameters.put(key.toString(), object.toString());
        
    	if (delegate != null) {
    		delegate.globalParametersWasSet(this, objectMap(this.globalParameters));
    	}
    }
    
    @Override
    public void removeGlobalParameter(Object key) {
        if (this.globalParameters != null) {
        	this.globalParameters.remove(key);
        	
        	if (delegate != null) {
        		delegate.globalParametersWasSet(this, objectMap(this.globalParameters));
        	}
        }
    }
    
    @Override
    public void logEvent(String name) {
    	s.pageName = name;
    	s.track();
    }
    
    public Hashtable<String,String> mergeParameters(Map<Object,Object> parameters) {
    	Hashtable<String,String> mergedParameters;
    	if (this.globalParameters != null) {
    		mergedParameters = new Hashtable<String, String>(this.globalParameters.size() + parameters.size());
    		mergedParameters.putAll(this.globalParameters);
    		mergedParameters.putAll(stringHashtable(parameters));
    	} else {
    		mergedParameters = stringHashtable(parameters);
    	}
    	return mergedParameters;
    }

    @Override
    public void logEventWithParams(String name, Map<Object,Object> parameters) {
    	s.pageName = name;
    	Hashtable<String,String> mergedParameters = mergeParameters(parameters);
    	if (mergedParameters != null) {
        	s.track(mergedParameters);
    	} else {
    		s.track();
    	}
    }

    @Override
    public void logPage(String name) {
    	s.pageName = name;
    	s.track();
    }

    @Override
    public void logPageWithParams(String name, Map<Object,Object> parameters) {
    	s.pageName = name;
    	Hashtable<String,String> mergedParameters = mergeParameters(parameters);
    	if (mergedParameters != null) {
        	s.track(mergedParameters);
    	} else {
    		s.track();
    	}
    }

    @Override
    public void logException(Throwable throwable) {
    	s.pageName = "Exception";
    	s.track();
    }

    @Override
    public void logException(String errorId, String message, Throwable throwable) {
    	s.pageName = "Exception";
    	s.track();
    }
    
    public static interface Delegate {
    	public void identifierWasSet(LolayOmnitureTracker tracker, String identifier);
    	public void setVersion(LolayOmnitureTracker tracker, String version);
    	public void setAge(LolayOmnitureTracker tracker, int age);
    	public void setGender(LolayOmnitureTracker tracker, LolayTrackerGender gender);
    	public void stateWasSet(LolayOmnitureTracker tracker, String state);
    	public void zipWasSet(LolayOmnitureTracker tracker, String zip);
    	public void campaignWasSet(LolayOmnitureTracker tracker, String campaign);
    	public void globalParametersWasSet(LolayOmnitureTracker tracker, Map<Object, Object> globalParameters);
    	public void channelWasSet(LolayOmnitureTracker tracker, String channel);
    }
}
