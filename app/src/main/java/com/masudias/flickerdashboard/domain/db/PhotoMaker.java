package com.masudias.flickerdashboard.domain.db;

public interface PhotoMaker {

    int PHOTO_SOURCE_FLICKER = 1;

    Photo getPhoto();

    String getPhotoUrl();

    String getPhotoOwnerUrl();

    int getPhotoSource();
}