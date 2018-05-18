package com.masudias.flickerdashboard.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.masudias.flickerdashboard.domain.http.response.FlickrSearchResponse;
import com.masudias.flickerdashboard.network.parser.FlickrImageResponseParser;
import com.masudias.flickerdashboard.network.receiver.PhotosResponseReceiver;
import com.masudias.flickerdashboard.util.NetworkUtil;

public class GetFlickrImagesREST {

    public static void getFlickrImages(final Context context, String tag, final int page, final PhotosResponseReceiver listener) {
        if (!NetworkUtil.isConnectionAvailable(context)) return;

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = NetworkConstants.getUrlForFlickrImage(tag, page);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        FlickrSearchResponse searchResponse = gson.fromJson(response, FlickrSearchResponse.class);
                        FlickrImageResponseParser flickrParser = new FlickrImageResponseParser(searchResponse);
                        listener.onPhotosReceived(flickrParser.getPhotosFromResponse());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onPhotosReceived(null);
            }
        });

        queue.add(stringRequest);
    }
}
