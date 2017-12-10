package com.recruiting.utils;

import java.util.UUID;

/**
 * @author Marta Ginosyan
 */

public class BusKeyGen {

    public static String nextKey() {
        return UUID.randomUUID().toString();
    }
}
