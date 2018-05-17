package com.masudias.flickerdashboard.domain.http;

import com.masudias.flickerdashboard.domain.db.Photo;
import com.masudias.flickerdashboard.domain.db.PhotoMaker;

public class FlickerPhoto implements PhotoMaker {

    public String id;
    public String owner;
    public String secret;
    public String server;
    public Integer farm;
    public String title;
    public Integer ispublic;
    public Integer isfriend;
    public Integer isfamily;
    public String url_o;
    public String height_o;
    public String width_o;

    @Override
    public Photo getPhoto() {
        return new Photo.Builder()
                .photoId(this.id)
                .photoUrl(getPhotoUrl())
                .owner(this.owner)
                .ownerPhotoUrl(getPhotoOwnerUrl())
                .title(this.title)
                .height(Integer.parseInt(this.height_o))
                .width(Integer.parseInt(this.width_o))
                .photoSource(getPhotoSource())
                .build();
    }

    @Override
    public String getPhotoUrl() {
        return this.url_o;
    }

    @Override
    public String getPhotoOwnerUrl() {
        return "http://farm" + this.farm + ".staticflickr.com/" + this.server + "/buddyicons/" + this.owner + ".jpg";
    }

    @Override
    public int getPhotoSource() {
        return PhotoMaker.PHOTO_SOURCE_FLICKER;
    }
}
