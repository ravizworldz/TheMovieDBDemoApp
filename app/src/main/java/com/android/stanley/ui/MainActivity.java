package com.android.stanley.ui;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.android.stanley.R;
import com.android.stanley.models.MovieDetailsModel;
import com.android.stanley.models.MovieModel;
import com.android.stanley.network.APIInterface;
import com.android.stanley.network.NetworkClient;
import com.android.stanley.ui.adapter.ViewPagerAdapter;
import com.android.stanley.ui.fragment.HistoryFragment;
import com.android.stanley.ui.fragment.SearchFragment;
import com.android.stanley.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Seting up the adapter for view pager,
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(SearchFragment.newInstance(), getResources().getString(R.string.tab_search_movie));
        adapter.addFragment(HistoryFragment.newInstance(), getResources().getString(R.string.tab_history));
        viewPager.setAdapter(adapter);
    }
}
