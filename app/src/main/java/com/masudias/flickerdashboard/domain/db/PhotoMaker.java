package com.masudias.flickerdashboard.domain.db;

public interface PhotoMaker {
    Photo getPhoto();

    String getPhotoUrl();

    String getPhotoOwnerUrl();

    int getPhotoSource();
}