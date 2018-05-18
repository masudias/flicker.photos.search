package com.masudias.flickerdashboard.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.CursorWrapper;
import android.database.MatrixCursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.lang.reflect.Field;

public class PhotoContentProvider extends ContentProvider {

    // The URI Matcher used by this content provider.
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    static final int PHOTO = 100;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PhotoContract.CONTENT_AUTHORITY;
        // matches photo/<any number> meaning any photo ID
        matcher.addURI(authority, PhotoContract.PATH_PHOTO + "/#", PHOTO);

        return matcher;
    }

    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);

        // Note: We always return single row of data, so content-type is always "a dir"
        switch (match) {
            case PHOTO:
                return PhotoContract.PhotoEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }


    @Override
    public boolean onCreate() {
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor retCursor = new MatrixCursor(projection);

        // open the specified image through the MediaStore to get base columns
        // then open image file through ExifInterface to get detail columns
        Long IMAGE_ID = PhotoContract.PhotoEntry.getImageIdFromUri(uri);
        Uri baseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        baseUri = Uri.withAppendedPath(baseUri, "" + IMAGE_ID);

        // http://androidsnippets.com/get-file-path-of-gallery-image
        // run query against MediaStore, projection = null means "get all fields"
        Cursor c = getContext().getContentResolver().query(baseUri, null, null, null, null);
        if (!c.moveToFirst()) {
            return null;
        }
        // match returned fields against projection, and copy into row[]
        Object[] row = new Object[projection.length];

        int i = 0;

        //http://stackoverflow.com/questions/11658239/cursor-gettype-for-api-level-11
        CursorWrapper cw = (CursorWrapper) c;
        Class<?> cursorWrapper = CursorWrapper.class;
        Field mCursor = null;
        try {
            mCursor = cursorWrapper.getDeclaredField("mCursor");
            mCursor.setAccessible(true);
            AbstractWindowedCursor abstractWindowedCursor = (AbstractWindowedCursor) mCursor.get(cw);
            CursorWindow cursorWindow = abstractWindowedCursor.getWindow();
            int pos = abstractWindowedCursor.getPosition();
            // NB! Expect resulting cursor to contain data in same order as projection!
            for (String colName : projection) {
                int idx = c.getColumnIndex(colName);

                // simple solution: If column name NOT FOUND in MediaStore, assume it's an EXIF tag
                // and skip
                if (idx >= 0) {
                    if (cursorWindow.isNull(pos, idx)) {
                        //Cursor.FIELD_TYPE_NULL
                        row[i++] = null;
                    } else if (cursorWindow.isLong(pos, idx)) {
                        //Cursor.FIELD_TYPE_INTEGER
                        row[i++] = c.getLong(idx);
                    } else if (cursorWindow.isFloat(pos, idx)) {
                        //Cursor.FIELD_TYPE_FLOAT
                        row[i++] = c.getFloat(idx);
                    } else if (cursorWindow.isString(pos, idx)) {
                        //Cursor.FIELD_TYPE_STRING
                        row[i++] = c.getString(idx);
                    } else if (cursorWindow.isBlob(pos, idx)) {
                        //Cursor.FIELD_TYPE_BLOB
                        row[i++] = c.getBlob(idx);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // have now handled the first i fields in projection. If there are any more, we expect
        // these to be valid EXIF tags. Should obviously make this more robust...
        try {
            ExifInterface exif = new ExifInterface((String) row[2]);
            while (i < projection.length) {
                row[i] = exif.getAttribute("UserComment"); //projection[i]);  // String (or null)
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        retCursor.addRow(row);
        return retCursor;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}