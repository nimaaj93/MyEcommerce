package com.nimaaj.ecommerce.util;

import java.util.concurrent.TimeUnit;

public class DateUtil {

    private DateUtil() {
    }

    public static long addTimeToEpochDate(long date, TimeUnit timeUnit, int units) {
        return date + timeUnit.toMillis(units);
    }

}