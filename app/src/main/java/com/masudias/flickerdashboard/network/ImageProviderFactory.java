package com.masudias.flickerdashboard.network;

import android.content.Context;

import com.masudias.flickerdashboard.network.parser.PhotoHttpResponse;
import com.masudias.flickerdashboard.network.receiver.PhotosResponseReceiver;

public class ImageProviderFactory {

    private static Context context;
    private static PhotosResponseReceiver listener;
    private static ImageProviderFactory instance = null;

    public static int FLICKER_PAGE_NUMBER = 1;

    private ImageProviderFactory(Context context, PhotosResponseReceiver listener) {
        this.context = context;
        this.listener = listener;
    }

    public static synchronized ImageProviderFactory getInstance(Context context, PhotosResponseReceiver listener) {
        if (instance == null)
            instance = new ImageProviderFactory(context, listener);

        return instance;
    }

    public void getImagesFromExternalSource(String topic, int imageSource) {
        if (imageSource == PhotoHttpResponse.PHOTO_SOURCE_FLICKR) {
            GetFlickrImagesREST
                    .getFlickrImages(context, topic, FLICKER_PAGE_NUMBER, listener);
            // Bring the next page next time.
            FLICKER_PAGE_NUMBER++;
        }
    }
}
