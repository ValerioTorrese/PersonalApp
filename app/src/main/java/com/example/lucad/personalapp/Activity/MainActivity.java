package com.example.lucad.personalapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lucad.personalapp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header=navigationView.inflateHeaderView(R.layout.general_nav_header_main);
        TextView email=(TextView) header.findViewById(R.id.email_sbarra_menu);
        TextView nome=(TextView) header.findViewById(R.id.nome_sbarra_menu);
        ImageView image=(ImageView) header.findViewById(R.id.imageView);
        email.setText(FirstActivity.studente.getEmail());
        nome.setText(FirstActivity.studente.getNome());
        Glide.with(image.getContext())
                .load(FirstActivity.studente.getUrl())
                .placeholder(R.drawable.side_nav_bar)
                .into(image);
        navigationView.setNavigationItemSelectedListener(this);
    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar appunti_item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view appunti_item clicks here.
        int id = item.getItemId();

        if (id == R.id.Appunti) {
            Intent i=new Intent(MainActivity.this, AppuntiActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i=new Intent(MainActivity.this, GalleryActivity.class);
            startActivity(i);
        } else if (id == R.id.link_utili) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.Carrelli) {
            Intent i=new Intent(MainActivity.this,CarrelliActivity.class);
            startActivity(i);
        } else if (id == R.id.Centralino) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
