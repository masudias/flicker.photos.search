package com.masudias.flickerdashboard.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.masudias.flickerdashboard.R;
import com.masudias.flickerdashboard.database.DataHelper;
import com.masudias.flickerdashboard.domain.db.Photo;
import com.masudias.flickerdashboard.fragments.PhotoListFragment;
import com.masudias.flickerdashboard.network.GetFlickrImagesREST;
import com.masudias.flickerdashboard.network.receiver.PhotosResponseReceiver;
import com.masudias.flickerdashboard.util.ApplicationConstants;
import com.masudias.flickerdashboard.util.NetworkUtil;
import com.masudias.flickerdashboard.util.TestUtil;

import java.util.List;

public class FlickrDashboardActivity extends AppCompatActivity implements PhotosResponseReceiver {
    private RelativeLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_dashboard);
        setupViewElements();
        showSnackBarWhenConnectionNotAvailable();
        launchPhotoListFragment();

        if (ApplicationConstants.DEBUG)
            TestUtil.insertDummyDataIntoPhotosTable(this);
        else GetFlickrImagesREST
                .getFlickrImages(FlickrDashboardActivity.this, "sports", 1, this);
    }

    private void launchPhotoListFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new PhotoListFragment()).commit();
    }

    private void showSnackBarWhenConnectionNotAvailable() {
        if (!NetworkUtil.isConnectionAvailable(this))
            Snackbar.make(parentLayout, R.string.no_connection, Snackbar.LENGTH_LONG).show();
    }

    private void setupViewElements() {
        parentLayout = (RelativeLayout) findViewById(R.id.fragment_container);
    }

    @Override
    synchronized public void onPhotosReceived(List<Photo> photoList) {
        DataHelper.getInstance(FlickrDashboardActivity.this)
                .insertPhotoListIntoDatabase(photoList);
    }
}