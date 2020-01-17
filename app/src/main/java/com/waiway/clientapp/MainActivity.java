package com.waiway.clientapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout splash;
    RelativeLayout datapanel;
    Handler h = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            splash.setVisibility(View.GONE);
            datapanel.setVisibility(View.VISIBLE);
        }
    };

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new ThreeBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
        splash = findViewById(R.id.spink);
        datapanel = findViewById(R.id.datapanel);
        h.postDelayed(run,3000);

        tabLayout = findViewById(R.id.tabbar);
        viewPager = findViewById(R.id.viewpager);

        // connect tablayout with viewPager adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MoviesFragment(),"movies");
        adapter.addFragment(new SeriesFragment(),"series");
        adapter.addFragment(new CategoryFragment(),"categories");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
