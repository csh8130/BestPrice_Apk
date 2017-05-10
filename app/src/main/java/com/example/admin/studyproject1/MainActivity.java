package com.example.admin.studyproject1;

import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.AdView;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*

        AdView mAdView = (AdView) findViewById(R.id.adView);

        //테스트 에뮬
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        // 실제용
        //AdRequest adRequest = new AdRequest.Builder().build();


        mAdView.loadAd(adRequest);

        */
        FragmentMain m = new FragmentMain();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,m).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
       // }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentFirst fragmentFirst;
        FragmentSecond fragmentSecond;
        FragmentThird fragmentThird;
        FragmentForth fragmentForth;

        if (id == R.id.nav_coolnjoy)
        {
            fragmentFirst = new FragmentFirst();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
        } else if (id == R.id.nav_ruli) {
            fragmentSecond = new FragmentSecond();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentSecond).commit();
        } else if (id == R.id.nav_ppom1) {
            fragmentThird = new FragmentThird();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentThird).commit();
        } else if (id == R.id.nav_ppom2) {
            fragmentForth = new FragmentForth();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentForth).commit();
        } else if (id ==R.id.nav_help)
        {
            Toast.makeText(getApplicationContext(),"추후 추가예정 : 추가 게시판, 카톡 및 문자 공유" +
                    "\n추가 하고싶은 기능 : 새 게시물 알림 기능\n\n많은 후기 부탁드립니다",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
