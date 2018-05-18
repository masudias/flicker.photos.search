package com.masudias.flickerdashboard.network.parser;

import com.masudias.flickerdashboard.domain.db.Photo;
import com.masudias.flickerdashboard.domain.http.FlickrPhoto;
import com.masudias.flickerdashboard.domain.http.response.FlickrSearchResponse;

import java.util.ArrayList;
import java.util.List;

public class FlickrImageResponseParser implements PhotoHttpResponse {

    FlickrSearchResponse response;

    public FlickrImageResponseParser(FlickrSearchResponse response) {
        this.response = response;
    }

    @Override
    public List<Photo> getPhotosFromResponse() {
        List<Photo> photoList = new ArrayList<Photo>();

        for (FlickrPhoto photo : response.photos.photo)
            photoList.add(photo.getPhoto());

        return photoList;
    }
}
