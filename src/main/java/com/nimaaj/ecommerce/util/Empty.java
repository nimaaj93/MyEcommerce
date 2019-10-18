package com.nimaaj.ecommerce.util;

import java.util.Collection;

/**
 * Created by K550 VX on 3/3/2019.
 */
public abstract class Empty {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isBlank(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Object obj) {
        if (obj instanceof String) {
            return isEmpty(isEmpty((String)obj));
        } else if (obj instanceof Collection) {
            return isEmpty((Collection)obj);
        } else {
            return obj == null;
        }
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

}
