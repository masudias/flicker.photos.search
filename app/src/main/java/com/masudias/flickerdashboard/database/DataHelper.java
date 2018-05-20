package com.masudias.flickerdashboard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.masudias.flickerdashboard.domain.db.Photo;
import com.masudias.flickerdashboard.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {

    private static final int DATABASE_VERSION = 1;

    private final Context context;
    private static DataHelper instance = null;
    private static DataBaseOpenHelper dOpenHelper;

    private DataHelper(Context context) {
        this.context = context.getApplicationContext();
    }

    public static synchronized DataHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DataHelper(context);
            dOpenHelper = new DataBaseOpenHelper(context, DBConstants.DB_FLICKR,
                    DATABASE_VERSION);
        }

        return instance;
    }

    public void closeDbOpenHelper() {
        if (dOpenHelper != null) dOpenHelper.close();
        instance = null;
    }

    synchronized public List<Photo> insertPhotoListIntoDatabase(List<Photo> photoList) {

        if (photoList != null && photoList.size() > 0) {
            ArrayList<Photo> validPhotoList = new ArrayList<Photo>();
            SQLiteDatabase db = dOpenHelper.getWritableDatabase();
            db.beginTransaction();

            try {
                for (Photo photo : photoList) {
                    if (!photo.isValid()) continue;
                    else validPhotoList.add(photo);

                    ContentValues values = new ContentValues();
                    values.put(DBConstants.KEY_PHOTO_ID, photo.photoId);
                    values.put(DBConstants.KEY_PHOTO_URL, photo.photoUrl);
                    values.put(DBConstants.KEY_OWNER, photo.owner);
                    values.put(DBConstants.KEY_OWNER_PHOTO_URL, photo.ownerPhotoUrl);
                    values.put(DBConstants.KEY_TITLE, photo.title);
                    values.put(DBConstants.KEY_PHOTO_HEIGHT, photo.height);
                    values.put(DBConstants.KEY_PHOTO_WIDTH, photo.width);
                    values.put(DBConstants.KEY_PHOTO_SOURCE, photo.photoSource);
                    values.put(DBConstants.KEY_CREATED_AT, System.currentTimeMillis());
                    db.insertWithOnConflict(DBConstants.DB_TABLE_PHOTO, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            db.setTransactionSuccessful();
            db.endTransaction();

            LoggerUtil.debug(LoggerUtil.DB_LOG, "Inserted photos into the database");
            return validPhotoList;
        }

        return null;
    }

    public Cursor getAllPhotosStored() {
        Cursor cursor = null;
        SQLiteDatabase db = dOpenHelper.getReadableDatabase();

        try {
            String queryString = "SELECT * FROM " + DBConstants.DB_TABLE_PHOTO;
            cursor = db.rawQuery(queryString, null);
            if (cursor != null) cursor.moveToFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cursor;
    }

    public long getPhotosCountInPhotoTable() {
        SQLiteDatabase db = dOpenHelper.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, DBConstants.DB_TABLE_PHOTO);
    }

    synchronized public void deleteAllPhotos() {
        SQLiteDatabase db = dOpenHelper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.delete(DBConstants.DB_TABLE_PHOTO, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.setTransactionSuccessful();
        db.endTransaction();

        LoggerUtil.debug(LoggerUtil.DB_LOG, "All Photos Deleted.");
    }
}