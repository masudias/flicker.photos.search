package com.masudias.flickerdashboard.network;

import android.net.Uri;

public class NetworkConstants {
    private static final String FLICKR_API_KEY = "949e98778755d1982f537d56236bbb42";
    private static final String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/";
    private static final String FLICKR_SEARCH_METHOD = "flickr.photos.search";

    public static String getUrlForFlickerImage(String tag, int page) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(FLICKR_BASE_URL)
                .appendQueryParameter("method", FLICKR_SEARCH_METHOD)
                .appendQueryParameter("api_key", FLICKR_API_KEY)
                .appendQueryParameter("page", page + "")
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nojsoncallback", "1")
                .appendQueryParameter("tags", tag)
                .appendQueryParameter("extras", "url_o");
        return builder.build().toString();
    }
}
