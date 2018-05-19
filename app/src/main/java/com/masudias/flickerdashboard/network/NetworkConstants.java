package com.masudias.flickerdashboard.network;

import android.net.Uri;

public class NetworkConstants {
    private static final String FLICKR_API_KEY = "949e98778755d1982f537d56236bbb42";
    private static final String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/";
    private static final String FLICKR_SEARCH_METHOD = "flickr.photos.search";
    private static final String FLICKR_PER_PAGE_DEFAULT = "20";

    public static String getUrlForFlickrImage(String tag, int page) {
        Uri.Builder uri = Uri.parse(FLICKR_BASE_URL).buildUpon();
        uri.appendQueryParameter("method", FLICKR_SEARCH_METHOD);
        uri.appendQueryParameter("api_key", FLICKR_API_KEY);
        uri.appendQueryParameter("page", page + "");
        uri.appendQueryParameter("per_page", FLICKR_PER_PAGE_DEFAULT);
        uri.appendQueryParameter("format", "json");
        uri.appendQueryParameter("nojsoncallback", "1");
        uri.appendQueryParameter("tags", tag);
        uri.appendQueryParameter("extras", "url_o");

        return uri.build().toString();
    }
}
