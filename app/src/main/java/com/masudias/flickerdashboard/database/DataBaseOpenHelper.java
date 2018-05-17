package com.masudias.flickerdashboard.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DataBaseOpenHelper extends SQLiteOpenHelper {

    private final int newVersion;
    private final String name;

    public DataBaseOpenHelper(Context context, String name, int version) {
        super(context, name, null, version);
        this.newVersion = version;
        this.name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createPhotoTable(db);
    }

    private void createPhotoTable(SQLiteDatabase db) {
        db.execSQL("create table if not exists "
                + DBConstants.DB_TABLE_PHOTO + "("
                + DBConstants.KEY_ID + " integer primary key autoincrement, "
                + DBConstants.KEY_PHOTO_ID + " text not null, "
                + DBConstants.KEY_PHOTO_URL + " text not null, "
                + DBConstants.KEY_OWNER + " text not null, "
                + DBConstants.KEY_OWNER_PHOTO_URL + " text not null, "
                + DBConstants.KEY_TITLE + " text not null, "
                + DBConstants.KEY_PHOTO_HEIGHT + " integer not null, "
                + DBConstants.KEY_PHOTO_WIDTH + " integer not null, "
                + DBConstants.KEY_PHOTO_SOURCE + " integer not null, "
                + "UNIQUE("
                + DBConstants.KEY_PHOTO_ID + ","
                + DBConstants.KEY_PHOTO_SOURCE + ") ON CONFLICT REPLACE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The last case will contain the break statement only. As the migration will take place one by one.
        // Here's a nice explanation - http://stackoverflow.com/a/26916986/3145960
    }
}