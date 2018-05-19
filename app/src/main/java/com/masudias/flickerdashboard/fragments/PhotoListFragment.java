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
import com.masudias.flickerdashboard.activity.FlickrDashboardActivity;
import com.masudias.flickerdashboard.adapter.EndlessScroller;
import com.masudias.flickerdashboard.adapter.PhotoListAdapter;
import com.masudias.flickerdashboard.database.DBConstants;
import com.masudias.flickerdashboard.database.DataHelper;
import com.masudias.flickerdashboard.database.SQLiteCursorLoader;
import com.masudias.flickerdashboard.domain.db.Photo;

import java.util.ArrayList;

public class PhotoListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, EndlessScroller {
    private static final int PHOTO_LIST_QUERY_LOADER = 0;

    private TextView emptyTextView;
    private RecyclerView photoListRecyclerView;
    private PhotoListAdapter photoListAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Photo> photoList = new ArrayList<Photo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo_list, container, false);
        setupViewElements(rootView);

        getActivity().getSupportLoaderManager()
                .initLoader(PHOTO_LIST_QUERY_LOADER, null, this).forceLoad();
        return rootView;
    }

    private void setupViewElements(View rootView) {
        emptyTextView = (TextView) rootView.findViewById(R.id.photo_list_empty);
        photoListRecyclerView = (RecyclerView) rootView.findViewById(R.id.photo_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        photoListRecyclerView.setLayoutManager(mLayoutManager);

        // Set adapter
        photoListAdapter = new PhotoListAdapter(getActivity(), photoList, this);
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
                Cursor cursor;
                cursor = dataHelper.getAllPhotosStored();

                if (cursor != null)
                    this.registerContentObserver(cursor, DBConstants.DB_TABLE_PHOTO_URI);

                return cursor;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.getCount() > 0) {
            emptyTextView.setVisibility(View.GONE);
            populatePhotoListFromCache(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void populatePhotoListFromCache(Cursor data) {
        photoList = Photo.populatePhotoListFromCursor(data);
        photoListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestForLoadingMoreImages(int imageSource) {
        ((FlickrDashboardActivity) getActivity()).getImagesFromServer(imageSource);
    }
}
