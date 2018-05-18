package com.masudias.flickerdashboard.network.parser;

import com.masudias.flickerdashboard.domain.db.Photo;
import com.masudias.flickerdashboard.domain.http.FlickerPhoto;
import com.masudias.flickerdashboard.domain.http.response.FlickerSearchResponse;

import java.util.ArrayList;
import java.util.List;

public class FlickrImageResponseParser implements PhotoHttpResponse {

    FlickerSearchResponse response;

    public FlickrImageResponseParser(FlickerSearchResponse response) {
        this.response = response;
    }

    @Override
    public List<Photo> getPhotosFromResponse() {
        List<Photo> photoList = new ArrayList<Photo>();

        for (FlickerPhoto photo : response.photos.photo)
            photoList.add(photo.getPhoto());

        return photoList;
    }
}
