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

import java.util.Map;

import android.content.Context;
import android.util.Log;

public class LolayLogTracker extends LolayBaseTracker {
	private static final String TAG = LolayLogTracker.class.getSimpleName();
	
	private String buildParameters(Map<Object, Object> parameters) {
		if (parameters == null || parameters.size() == 0) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("?");
		boolean first = true;
		for (Map.Entry<Object,Object> entry : parameters.entrySet()) {
			if (first) {
				first = false;
			} else {
				builder.append("&");
			}
			builder.append(entry.getKey());
			builder.append("=");
			builder.append(entry.getValue());
		}
		
		return builder.toString();
	}
	
    @Override
    public void logEvent(Context context, String name) {
		Log.i(TAG, context.getClass().getName() + " Event: " + name);
    }

    @Override
    public void logEventWithParams(Context context, String name, Map<Object, Object> parameters) {
		Log.i(TAG, context.getClass().getName() + " Event: " + name + buildParameters(parameters));
    }

    @Override
    public void logPage(Context context, String name) {
		Log.i(TAG, context.getClass().getName() + " Page: " + name);
    }

    @Override
    public void logPageWithParams(Context context, String name, Map<Object, Object> parameters) {
    	Log.i(TAG, context.getClass().getName() + " Page: " + name + buildParameters(parameters));
    }

    @Override
    public void logException(Context context, Throwable throwable) {
    	Log.i(TAG, context.getClass().getName() + " Exception", throwable);
    }

    @Override
    public void logException(Context context, String errorId, String message, Throwable throwable) {
    	Log.i(TAG, context.getClass().getName() + " Exception: " + errorId + ": " + message, throwable);
    }
}
