package com.masudias.flickerdashboard.provider;

import android.content.ContentResolver;
import android.net.Uri;

import com.masudias.flickerdashboard.util.ApplicationConstants;

public class PhotoContract {

    public static final String CONTENT_AUTHORITY = ApplicationConstants.ApplicationPackage;

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PHOTO = "photo";

    public static final class PhotoEntry {
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PHOTO;

        public static Long getImageIdFromUri(Uri uri) {
            return Long.parseLong(uri.getPathSegments().get(1));  // Always in position 1
        }
    }
}