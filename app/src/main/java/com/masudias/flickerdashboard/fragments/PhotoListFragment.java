package com.masudias.flickerdashboard.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.masudias.flickerdashboard.R;
import com.masudias.flickerdashboard.adapter.EndlessScroller;
import com.masudias.flickerdashboard.adapter.PhotoListAdapter;
import com.masudias.flickerdashboard.database.DataHelper;
import com.masudias.flickerdashboard.database.SQLiteCursorLoader;
import com.masudias.flickerdashboard.domain.db.Photo;
import com.masudias.flickerdashboard.network.ImageProviderFactory;
import com.masudias.flickerdashboard.network.receiver.PhotosResponseReceiver;
import com.masudias.flickerdashboard.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import static com.masudias.flickerdashboard.network.parser.PhotoHttpResponse.PHOTO_SOURCE_FLICKR;

public class PhotoListFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor>, PhotosResponseReceiver, EndlessScroller {
    private static final int PHOTO_LIST_QUERY_LOADER = 0;
    private boolean isLoading = false;
    private boolean loadedOnceFromCache = false;

    private TextView emptyTextView;
    private RecyclerView photoListRecyclerView;
    private PhotoListAdapter photoListAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Photo> photoListFromServer = new ArrayList<Photo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo_list, container, false);
        setupViewElements(rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getSupportLoaderManager()
                .initLoader(PHOTO_LIST_QUERY_LOADER, null, this).forceLoad();
    }

    private void setupViewElements(View rootView) {
        emptyTextView = (TextView) rootView.findViewById(R.id.photo_list_empty);
        photoListRecyclerView = (RecyclerView) rootView.findViewById(R.id.photo_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        photoListRecyclerView.setLayoutManager(mLayoutManager);

        // Set adapter
        photoListAdapter = new PhotoListAdapter(getActivity(), photoListFromServer, this);
        photoListRecyclerView.setAdapter(photoListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().getSupportLoaderManager().destroyLoader(PHOTO_LIST_QUERY_LOADER);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new SQLiteCursorLoader(getActivity()) {
            @Override
            public Cursor loadInBackground() {
                DataHelper dataHelper = DataHelper.getInstance(getActivity());
                Cursor cursor = dataHelper.getAllPhotosStored();
                return cursor;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (loadedOnceFromCache) return;
        else loadedOnceFromCache = true;

        if (data != null && data.getCount() > 0) {
            emptyTextView.setVisibility(View.GONE);
            populatePhotoListFromCache(data);
        }

        // Load the images first time from cursor if available.
        // Then launch the background operation to fetch latest photos
        getImagesFromServer(PHOTO_SOURCE_FLICKR);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void populatePhotoListFromCache(Cursor data) {
        ArrayList<Photo> photoListFromCache = Photo.populatePhotoListFromCursor(data);
        photoListAdapter.setPhotoList(photoListFromCache);
        photoListAdapter.notifyDataSetChanged();
    }

    public void getImagesFromServer(int imageSource) {
        if (isLoading || !NetworkUtil.isConnectionAvailable(getActivity())) return;

        isLoading = true;
        ImageProviderFactory
                .getInstance(getActivity(), this)
                .getImagesFromExternalSource("sports", imageSource);
    }

    @Override
    public void onRequestForLoadingMoreImages(int imageSource) {
        getImagesFromServer(imageSource);
    }

    @Override
    synchronized public void onPhotosReceived(List<Photo> photoList) {
        isLoading = false;
        if (photoList.size() > 0)
            emptyTextView.setVisibility(View.GONE);

        this.photoListFromServer.addAll(photoList);
        photoListAdapter.setPhotoList(photoListFromServer);
        photoListAdapter.notifyDataSetChanged();
    }
}