package com.masudias.flickerdashboard.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.masudias.flickerdashboard.R;
import com.masudias.flickerdashboard.adapter.DashboardPagerAdapter;
import com.masudias.flickerdashboard.util.ApplicationConstants;
import com.masudias.flickerdashboard.util.NetworkUtil;
import com.masudias.flickerdashboard.util.TestUtil;

public class FlickrDashboardActivity extends AppCompatActivity {

    private DashboardPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private final int SPORTS_TAB = 0;
    private final int ARTS_TAB = 1;
    private final int FAVOURITE_TAB = 2;
    private final int MUSIC_TAB = 3;

    private RelativeLayout parentLayout;

    private TabLayout.Tab sportsTab;
    private TabLayout.Tab artsTab;
    private TabLayout.Tab favouriteTab;
    private TabLayout.Tab musicTab;

    private View sportsTabView;
    private View artsTabView;
    private View favouriteTabView;
    private View musicTabView;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_dashboard);
        setupViewElements();
        showSnackBarWhenConnectionNotAvailable();

        if (ApplicationConstants.DEBUG)
            TestUtil.insertDummyDataIntoPhotosTable(this);
        else {
            // TODO: Get the data using Flicker API
        }
    }

    private void showSnackBarWhenConnectionNotAvailable() {
        if (!NetworkUtil.isConnectionAvailable(this))
            Snackbar.make(parentLayout, R.string.no_connection, Snackbar.LENGTH_LONG).show();
    }

    private void setupViewElements() {
        parentLayout = (RelativeLayout) findViewById(R.id.parent_layout);
        setupViewPager();
        setupTabs();
    }

    private void setupTabs() {
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        sportsTab = tabLayout.getTabAt(SPORTS_TAB);
        artsTab = tabLayout.getTabAt(ARTS_TAB);
        favouriteTab = tabLayout.getTabAt(FAVOURITE_TAB);
        musicTab = tabLayout.getTabAt(MUSIC_TAB);

        setupCustomViewsForTabs();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setupViewPager() {
        mSectionsPagerAdapter = new DashboardPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(DashboardPagerAdapter.TOTAL_PAGE_COUNT - 1);
    }

    private void setupCustomViewsForTabs() {
        sportsTabView = getLayoutInflater().inflate(R.layout.view_single_tab_background, null);
        artsTabView = getLayoutInflater().inflate(R.layout.view_single_tab_background, null);
        favouriteTabView = getLayoutInflater().inflate(R.layout.view_single_tab_background, null);
        musicTabView = getLayoutInflater().inflate(R.layout.view_single_tab_background, null);

        setTabIcons();
        sportsTab.setCustomView(sportsTabView);
        artsTab.setCustomView(artsTabView);
        favouriteTab.setCustomView(favouriteTabView);
        musicTab.setCustomView(musicTabView);
    }

    private void setTabIcons() {
        ((ImageView) sportsTabView.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_tab_sports);
        ((ImageView) artsTabView.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_tab_arts);
        ((ImageView) favouriteTabView.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_tab_favourite);
        ((ImageView) musicTabView.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_tab_music);
    }
}