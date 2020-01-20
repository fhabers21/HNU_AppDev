package com.example.felix.extraschicht_v10;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private MainScreen csActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.w150);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new SearchFregment()).commit();



    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_search:
                            selectedFragment = new SearchFregment();
                            csActivity = MainScreen.this;
                            csActivity.getSupportActionBar().show();
                            break;

                        case R.id.nav_events:
                            selectedFragment = new EventsFregment();
                            break;

                        case R.id.nav_map:
                            selectedFragment = new MapFregment();
                            csActivity = MainScreen.this;
                            csActivity.getSupportActionBar().show();
                            break;

                        case R.id.nav_company:
                            selectedFragment = new CompFregment();
                            break;

                        case R.id.nav_news:
                            selectedFragment = new NewsFregment();
                            csActivity = MainScreen.this;
                            csActivity.getSupportActionBar().show();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment selectedFragment = null;

        if (item.getItemId() == R.id.nav_settings) {

            selectedFragment = new SettingsFregment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();

        }


        return true;


    }



}


