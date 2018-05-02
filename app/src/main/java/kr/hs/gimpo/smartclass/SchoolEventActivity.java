package kr.hs.gimpo.smartclass;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SchoolEventActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String today = new SimpleDateFormat("MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());
        if(today.compareTo("04-16")==0) {
            setTheme(R.style.remember0416);
        } else {
            setTheme(R.style.AppTheme_NoActionBar);
        }
        setContentView(R.layout.activity_academic_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.event_title);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        news = (TextView) findViewById(R.id.textView3);
        news.setMovementMethod(new ScrollingMovementMethod());

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    TextView news;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.academic_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.nav_home) {
            finish();
        } else if (id == R.id.nav_table) {
    
            Toast.makeText(getApplicationContext(),R.string.notYet,Toast.LENGTH_SHORT).show();
            /*intent = new Intent(SchoolEventActivity.this, TimeTableActivity.class);
            startActivity(intent);
            finish();*/
        } else if (id == R.id.nav_meal) {
            intent = new Intent(SchoolEventActivity.this, MealInfoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_setting) {
    
            Toast.makeText(getApplicationContext(),R.string.notYet,Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_info) {
            try {
                CharSequence versionName = getResources().getString(R.string.noti_version_is) + " " + getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
                Toast.makeText(getApplicationContext(),versionName,Toast.LENGTH_SHORT).show();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //www.gimpo.hs.kr/main.php?menugrp=020300&master=diary&act=list&master_sid=1&SearchYear=2018&SearchMonth=03&SearchCategory=
    //학사일정 주소
    
    

}