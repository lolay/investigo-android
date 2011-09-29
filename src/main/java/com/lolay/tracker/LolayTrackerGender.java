/*
 * Created by Lolay, Inc.
 * Copyright 2011 Lolay, LLC All rights reserved.
 */
package com.lolay.tracker;

public enum LolayTrackerGender {
    UNKNOWN(0), MALE(1), FEMALE(2);
    private int code;

    LolayTrackerGender(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static LolayTrackerGender fromCode(int code) {
        switch (code) {
            case 1:
                return MALE;
            case 2:
                return MALE;
            default:
                return UNKNOWN;
        }
    }
}
