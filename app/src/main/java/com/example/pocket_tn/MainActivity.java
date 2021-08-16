package com.example.pocket_tn;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pocket_tn.databinding.ActivityMainBinding;
import com.example.pocket_tn.fragments.FragmentHome;
import com.example.pocket_tn.fragments.FragmentMeals;
import com.example.pocket_tn.fragments.FragmentProfile;
import com.example.pocket_tn.fragments.FragmentWorkout;
import com.example.pocket_tn.utils.TabViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TabViewPagerAdapter tabViewPagerAdapter;
    Fragment fragment;

    final int[] ICONS = new int[]{
            R.drawable.ic_baseline_home_24,
            R.drawable.ic_gym,
            R.drawable.ic_dinner,
            R.drawable.ic_baseline_settings_24
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBar.toolbar);
        setTitle("");
        // binding.appBar.maincontent.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.appBar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());

        // loadFragment(new FragmentHome()); //changes are made here
        binding.appBar.tvTitle.setText("Home Page");
        //  binding.appBar.maincontent.navigation.setSelectedItemId(R.id.home);

        fragment = new FragmentHome();
        tabViewPagerAdapter.addFragment(fragment, "Home");

        fragment = new FragmentWorkout();
        tabViewPagerAdapter.addFragment(fragment, "Workout");

        fragment = new FragmentMeals();
        tabViewPagerAdapter.addFragment(fragment, "Meals");

        fragment = new FragmentProfile();
        tabViewPagerAdapter.addFragment(fragment, "Settings");

        binding.appBar.maincontent.viewpager.setAdapter(tabViewPagerAdapter);
        binding.appBar.maincontent.tabs.setupWithViewPager(binding.appBar.maincontent.viewpager);

        Objects.requireNonNull(binding.appBar.maincontent.tabs.getTabAt(0)).setIcon(ICONS[0]);
        Objects.requireNonNull(binding.appBar.maincontent.tabs.getTabAt(1)).setIcon(ICONS[1]);
        Objects.requireNonNull(binding.appBar.maincontent.tabs.getTabAt(2)).setIcon(ICONS[2]);
        Objects.requireNonNull(binding.appBar.maincontent.tabs.getTabAt(3)).setIcon(ICONS[3]);

        binding.appBar.maincontent.viewpager.setCurrentItem(0);
        binding.appBar.maincontent.viewpager.setOffscreenPageLimit(1);

        binding.appBar.maincontent.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Fragment fr = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + binding.appBar.maincontent.viewpager.getCurrentItem());

                if(fr instanceof FragmentHome){
                    Log.d("dng","Home Fragment");
                    binding.appBar.tvTitle.setText("Home Page");

                } else if(fr instanceof FragmentWorkout){
                    Log.d("dng","workout Fragment");
                    binding.appBar.tvTitle.setText("Workouts");
                }else if(fr instanceof FragmentMeals){
                    Log.d("dng","Meals Fragment");
                    binding.appBar.tvTitle.setText("Meals");
                }else if(fr instanceof FragmentProfile){
                    Log.d("dng","Settings Fragment");
                    binding.appBar.tvTitle.setText("Settings");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        navigationView.setNavigationItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home) {
                binding.appBar.maincontent.viewpager.setCurrentItem(0);
            }else if (item.getItemId() == R.id.workouts){
                binding.appBar.maincontent.viewpager.setCurrentItem(1);
            }else if (item.getItemId() == R.id.meals){
                binding.appBar.maincontent.viewpager.setCurrentItem(2);
            }else if (item.getItemId() == R.id.settings) {
                binding.appBar.maincontent.viewpager.setCurrentItem(3);
            }

            drawer.closeDrawer(GravityCompat.START);
            return false;
        });


    }

    /*private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/

    /*  @SuppressLint("NonConstantResourceId")
      private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
              = item -> {
          Fragment fragment;
          switch (item.getItemId()) {
              case R.id.home:
                  binding.appBar.tvTitle.setText("Home Page");
                  fragment = new FragmentHome();
                  loadFragment(fragment);
                  return true;
              case R.id.workout:
                  binding.appBar.tvTitle.setText("Workouts");
                  fragment = new FragmentWorkout();
                  loadFragment(fragment);
                  return true;
              case R.id.meals:
                  binding.appBar.tvTitle.setText("Meals");
                  fragment = new FragmentMeals();
                  loadFragment(fragment);
                  return true;
              case R.id.profile:
                  binding.appBar.tvTitle.setText("Profile");
                  fragment = new FragmentProfile();
                  loadFragment(fragment);
                  return true;
          }

          return false;
      };
  */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}