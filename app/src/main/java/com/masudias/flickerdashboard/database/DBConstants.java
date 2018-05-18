package com.masudias.flickerdashboard.database;

import android.net.Uri;

import com.masudias.flickerdashboard.util.ApplicationConstants;

public class DBConstants {
    public static final String DB_PATH = "/data/data/" + ApplicationConstants.ApplicationPackage + "/databases/";
    public static final String DB_FLICKR = "flickr";
    public static final String DB_TABLE_PHOTO = "photo";
    public static final Uri DB_TABLE_PHOTO_URI = Uri
            .parse("sqlite://" + ApplicationConstants.ApplicationPackage + "/" + DB_TABLE_PHOTO);

    // Photo table columns
    public static final String KEY_ID = "id";
    public static final String KEY_PHOTO_ID = "photo_id";
    public static final String KEY_PHOTO_URL = "photo_url";
    public static final String KEY_OWNER = "owner";
    public static final String KEY_OWNER_PHOTO_URL = "owner_photo_url";
    public static final String KEY_TITLE = "title";
    public static final String KEY_PHOTO_HEIGHT = "photo_height";
    public static final String KEY_PHOTO_WIDTH = "photo_width";
    public static final String KEY_PHOTO_SOURCE = "source";
}
