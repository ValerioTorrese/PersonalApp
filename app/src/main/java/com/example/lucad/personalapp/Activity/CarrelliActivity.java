package com.example.lucad.personalapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lucad.personalapp.Adapter.AppuntiAdapter;
import com.example.lucad.personalapp.Adapter.CarrelliAdapter;
import com.example.lucad.personalapp.Database.DatabaseHandler;
import com.example.lucad.personalapp.Model.Attività;
import com.example.lucad.personalapp.R;

/**
 * Created by LucaD on 09/03/2017.
 */

public class CarrelliActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView carrelli_rv;
    LinearLayoutManager layoutManager;
    RelativeLayout layout;
    CarrelliAdapter adapter;
    DatabaseHandler db;
    Intent i;
    static String attivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrelli_activity_carrelli_main);
        adapter=new CarrelliAdapter();
        layout=(RelativeLayout) findViewById(R.id.scrollCarrelli);
        carrelli_rv=(RecyclerView) findViewById(R.id.carrelli_rv);
        layoutManager=new LinearLayoutManager(this);
        i=getIntent();
        carrelli_rv.setAdapter(adapter);
        carrelli_rv.setLayoutManager(layoutManager);
        db=DatabaseHandler.getIstanza(this);
        adapter.setAttivitaOk(db.getAllActivita());

        findViewById(R.id.add_carrelli_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTurni();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_appunti_layout);
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

    public void showAddTurni(){
            AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
            LayoutInflater inflater=this.getLayoutInflater();
            View dialog=inflater.inflate(R.layout.aggiungi_attivita,null);
            dialogBuilder.setView(dialog);
            //final TextView title=(TextView) dialog.findViewById(R.id.add_appunti_titolo);
            final Spinner actSpinn=(Spinner) dialog.findViewById(R.id.add_generic_act);
            final EditText dataScad=(EditText) dialog.findViewById(R.id.add_generic_data);
            final EditText body=(EditText) dialog.findViewById(R.id.add_generic_descrizione);

            final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                    R.array.act_array, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            actSpinn.setAdapter(adapter1);
            actSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    attivity= parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            dialogBuilder.setTitle("Articoli da consegnare");
            dialogBuilder.setMessage("Inserisci articoli");
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final Attività act=new Attività();
                    act.setTipo(attivity);
                    act.setData(dataScad.getText().toString());
                    act.setAppunti(body.getText().toString());
                    if(attivity.equals("Carrelli")){
                        adapter.setAct(act);}
                    db.addAttivita(act);
                }
            });
            AlertDialog AD=dialogBuilder.create();
            AD.show();;
        }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_appunti_layout);
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
        if(id== R.id. Appunti){
            Intent i=new Intent(CarrelliActivity.this,AppuntiActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_gallery) {
            Intent i=new Intent(CarrelliActivity.this,GalleryActivity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.link_utili) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.Carrelli) {
            System.out.println("ok");
        } else if (id == R.id.Centralino) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_appunti_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    }
