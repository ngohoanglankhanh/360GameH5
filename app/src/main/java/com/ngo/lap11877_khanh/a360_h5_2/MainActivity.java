package com.ngo.lap11877_khanh.a360_h5_2;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        ViewPager viewPager = findViewById(R.id.viewPager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

//        initFragment();
    }

//    public void initFragment() {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//
//        fragmentTransaction.replace(R.id.my_placeholder, new NewsFragment());
//
//        fragmentTransaction.commit();
//    }
}