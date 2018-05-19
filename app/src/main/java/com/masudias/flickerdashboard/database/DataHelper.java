package com.masudias.flickerdashboard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.masudias.flickerdashboard.domain.db.Photo;

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

    public long insertPhotoIntoDatabase(Photo photo) {

        long rowIdOfSavedPhoto = -1;

        if (!photo.isValid()) return -1;

        if (photo != null) {
            SQLiteDatabase db = dOpenHelper.getWritableDatabase();
            db.beginTransaction();

            try {
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
                rowIdOfSavedPhoto = db.insertWithOnConflict(DBConstants.DB_TABLE_PHOTO, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            } catch (Exception e) {
                e.printStackTrace();
            }

            db.setTransactionSuccessful();
            db.endTransaction();
            deleteOldPhotosToLimitThePhotoStored();

            context.getContentResolver().notifyChange(DBConstants.DB_TABLE_PHOTO_URI, null);

            Log.d("Photos", "Inserted photos into the database");
        }

        return rowIdOfSavedPhoto;
    }

    public void insertPhotoListIntoDatabase(List<Photo> photoList) {
        if (photoList != null && photoList.size() > 0) {
            SQLiteDatabase db = dOpenHelper.getWritableDatabase();
            db.beginTransaction();

            try {
                for (Photo photo : photoList) {
                    if (!photo.isValid()) continue;

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
            deleteOldPhotosToLimitThePhotoStored();

            context.getContentResolver().notifyChange(DBConstants.DB_TABLE_PHOTO_URI, null);

            Log.d("Photos", "Inserted photos into the database");
        }
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

    public void deleteOldPhotosToLimitThePhotoStored() {

        long photosCount = getPhotosCountInPhotoTable();
        if (photosCount < DBConstants.MAX_ROW_COUNT_DB_TABLE_PHOTO) return;

        SQLiteDatabase db = dOpenHelper.getWritableDatabase();
        db.beginTransaction();

        try {
            Cursor cursor = null;
            String getCountQueryString = "SELECT "
                    + DBConstants.KEY_CREATED_AT + " FROM "
                    + DBConstants.DB_TABLE_PHOTO
                    + " ORDER BY " + DBConstants.KEY_CREATED_AT + " DESC"
                    + " LIMIT 1 OFFSET " + DBConstants.MAX_ROW_COUNT_DB_TABLE_PHOTO;

            cursor = db.rawQuery(getCountQueryString, null);
            cursor.moveToFirst();
            long oldImageThresholdTime = cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_CREATED_AT));

            String deleteQueryString = "DELETE FROM "
                    + DBConstants.DB_TABLE_PHOTO
                    + " WHERE " + DBConstants.KEY_CREATED_AT
                    + " <= " + oldImageThresholdTime;

            db.rawQuery(deleteQueryString, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.setTransactionSuccessful();
        db.endTransaction();

        Log.d("DELETE", "Limit the row ids.");
    }

    public long getPhotosCountInPhotoTable() {
        SQLiteDatabase db = dOpenHelper.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, DBConstants.DB_TABLE_PHOTO);
    }

    public void deleteAllPhotos() {
        SQLiteDatabase db = dOpenHelper.getWritableDatabase();
        db.beginTransaction();

        try {
            String queryString = "DELETE FROM " + DBConstants.DB_TABLE_PHOTO;
            db.rawQuery(queryString, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.setTransactionSuccessful();
        db.endTransaction();

        context.getContentResolver().notifyChange(DBConstants.DB_TABLE_PHOTO_URI, null);

        Log.d("DELETE", "All Photos Deleted.");
    }
}