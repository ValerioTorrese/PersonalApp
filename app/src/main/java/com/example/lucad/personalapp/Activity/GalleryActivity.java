package com.example.lucad.personalapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lucad.personalapp.Adapter.GalleryAdapter;
import com.example.lucad.personalapp.Model.AppoggioClass.JsonScripture;
import com.example.lucad.personalapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by LucaD on 08/03/2017.
 */

public class GalleryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    RecyclerView gallery_rv;
    GalleryAdapter adapter;
    StaggeredGridLayoutManager layoutManager;
    RelativeLayout layout;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity_gallery);
        gallery_rv=(RecyclerView) findViewById(R.id.gallery_rv);
        adapter=new GalleryAdapter();
        layout=(RelativeLayout) findViewById(R.id.scrollImmagini);
        i=getIntent();
        layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        gallery_rv.setLayoutManager(layoutManager);
        gallery_rv.setAdapter(adapter);
JsonScripture js=new JsonScripture();
        adapter.setStudents(js.fetchStudentsFromJSON(readLocalJson()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_gallery_layout);
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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_gallery_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view  clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            System.out.println("ok");
        } else if (id == R.id.Appunti) {
            Intent i = new Intent(GalleryActivity.this, AppuntiActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.link_utili) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.Carrelli) {
            Intent i=new Intent(GalleryActivity.this,CarrelliActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.Centralino) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_gallery_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private String readLocalJson(){
        Writer writer=new StringWriter();
        char[] buffer=new char[1024];
        try(InputStream is=getResources().openRawResource(R.raw.students_official)){
            Reader read=new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while((n=read.read(buffer)) != -1){
                writer.write(buffer, 0 ,n);
            }
        }  catch(IOException e){
            e.printStackTrace();
        }
        return writer.toString();
    }
/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }*/
}
