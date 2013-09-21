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

import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class LolayBaseTrackerTest {

    @Test
    public void testBuildParamsFromObjectArray() {
        LolayBaseTracker tracker = new LolayBaseTracker();
        Map<Object, Object> results = tracker.buildParamsFromObjectArray("k1", "v1", "k2", "v2", "k3", "v3");
        assertEquals(3, results.size());
        assertEquals("v1", results.get("k1"));
        assertEquals("v2", results.get("k2"));
        assertEquals("v3", results.get("k3"));

        results = tracker.buildParamsFromObjectArray("k1", "v1", "k2", "v2", "k3");
        assertEquals(2, results.size());
        assertEquals("v1", results.get("k1"));
        assertEquals("v2", results.get("k2"));

        results = tracker.buildParamsFromObjectArray("k1", "v1", null, "v2", "k3", "v3");
        assertEquals(2, results.size());
        assertEquals("v1", results.get("k1"));
        assertEquals("v3", results.get("k3"));

        results = tracker.buildParamsFromObjectArray("k1", "v1", "k2", null, "k3", "v3");
        assertEquals(3, results.size());
        assertEquals("v1", results.get("k1"));
        assertNull(results.get("k2"));
        assertEquals("v3", results.get("k3"));

    }
}
