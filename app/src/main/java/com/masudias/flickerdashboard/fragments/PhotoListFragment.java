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
import com.masudias.flickerdashboard.adapter.PhotoListAdapter;
import com.masudias.flickerdashboard.database.DBConstants;
import com.masudias.flickerdashboard.database.DataHelper;
import com.masudias.flickerdashboard.database.SQLiteCursorLoader;

public class PhotoListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String ARG_SEARCH_TAG = "search_tag";
    private static final int PHOTO_LIST_QUERY_LOADER = 0;

    private TextView emptyTextView;
    private RecyclerView photoListRecyclerView;
    private PhotoListAdapter photoListAdapter;
    private LinearLayoutManager mLayoutManager;

    public PhotoListFragment() {
    }

    public static PhotoListFragment newInstance(String searchTag) {
        PhotoListFragment fragment = new PhotoListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_TAG, searchTag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo_list, container, false);
        emptyTextView = (TextView) getActivity().findViewById(R.id.photo_list_empty);
        photoListRecyclerView = (RecyclerView) getActivity().findViewById(R.id.photo_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        photoListRecyclerView.setLayoutManager(mLayoutManager);

        getActivity().getSupportLoaderManager()
                .initLoader(PHOTO_LIST_QUERY_LOADER, null, this).forceLoad();
        return rootView;
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
        if (data != null && data.getCount() > 0)
            emptyTextView.setVisibility(View.GONE);

        photoListAdapter = new PhotoListAdapter(getActivity(), data);
        photoListRecyclerView.setAdapter(photoListAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
