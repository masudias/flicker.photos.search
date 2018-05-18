package com.masudias.flickerdashboard.network.receiver;

import com.masudias.flickerdashboard.domain.db.Photo;

import java.util.List;

public interface PhotosResponseReceiver {
    void onPhotosReceived(List<Photo> photoList);
}