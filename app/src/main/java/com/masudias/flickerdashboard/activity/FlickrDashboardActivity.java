package com.masudias.flickerdashboard.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.masudias.flickerdashboard.R;
import com.masudias.flickerdashboard.database.DataHelper;
import com.masudias.flickerdashboard.domain.db.Photo;
import com.masudias.flickerdashboard.fragments.PhotoListFragment;
import com.masudias.flickerdashboard.network.ImageProviderFactory;
import com.masudias.flickerdashboard.network.receiver.PhotosResponseReceiver;
import com.masudias.flickerdashboard.util.NetworkUtil;

import java.util.List;

import static com.masudias.flickerdashboard.network.parser.PhotoHttpResponse.PHOTO_SOURCE_FLICKR;

public class FlickrDashboardActivity extends AppCompatActivity {
    private RelativeLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_dashboard);
        setupViewElements();
        showSnackBarWhenConnectionNotAvailable();
        launchPhotoListFragment();
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
}