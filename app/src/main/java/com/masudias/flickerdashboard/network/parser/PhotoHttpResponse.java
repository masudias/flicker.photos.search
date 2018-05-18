package com.masudias.flickerdashboard.network.parser;

import com.masudias.flickerdashboard.domain.db.Photo;

import java.util.List;

public interface PhotoHttpResponse {
    int PHOTO_SOURCE_FLICKR = 1;

    List<Photo> getPhotosFromResponse();
}