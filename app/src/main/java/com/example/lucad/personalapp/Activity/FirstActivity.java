package com.example.lucad.personalapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lucad.personalapp.Model.AppoggioClass.JsonScripture;
import com.example.lucad.personalapp.Model.Students;
import com.example.lucad.personalapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by LucaD on 05/03/2017.
 */

public class FirstActivity extends AppCompatActivity {
static String userNAME;
static String passWORD;
    public static Students studente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        final EditText loginUser=(EditText) findViewById(R.id.username_login);
        final EditText loginPass=(EditText) findViewById(R.id.password_login);
        SharedPreferences shared=getSharedPreferences("Share",Context.MODE_PRIVATE);

            loginUser.setText(shared.getString("LastAccount",""));
            loginPass.setText(shared.getString("LastPassword", ""));

        Button loginBtn=(Button) findViewById(R.id.login_btn);
        JsonScripture js=new JsonScripture();
        final ArrayList <Students> students= js.fetchStudentsFromJSON(readLocalJson());
        final String[] user=new String[students.size()];
        final String[] pass=new String[students.size()];
        for(int i=0;i<user.length;i++){
            user[i]=students.get(i).getUsername();
            pass[i]=students.get(i).getPassword();
            System.out.println(user[i]);
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (int i = 0; i < user.length; i++) {
                    if (user[i].equals(loginUser.getText().toString()) && pass[i].equals(loginPass.getText().toString())) {
                        System.out.println(user[i]);
                        studente=students.get(i);
                        userNAME=loginUser.getText().toString();
                        passWORD=loginPass.getText().toString();
                        Intent a = new Intent(FirstActivity.this, MainActivity.class);
                        startActivity(a);
                    }
                }
            }});
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences share= getSharedPreferences("Share", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=share.edit();
        editor.putString("LastAccount", userNAME);
        editor.putString("LastPassword", passWORD);
        editor.apply();
    }
}
