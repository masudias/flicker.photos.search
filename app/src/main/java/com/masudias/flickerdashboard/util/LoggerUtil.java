package com.masudias.flickerdashboard.util;

import android.util.Log;

public class LoggerUtil {

    public static final String DB_LOG = "FLICKR_DEMO_DATABASE";
    public static final String RECYCLER_VIEW_LOG = "RECYCLER_VIEW";

    public static void debug(String logType, String message) {
        if (ApplicationConstants.DEBUG) Log.d(logType, message);
    }
}
