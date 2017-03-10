package com.example.lucad.personalapp.Model.AppoggioClass;

import android.content.res.Resources;
import android.support.v7.widget.VectorEnabledTintResources;

import com.example.lucad.personalapp.Model.Students;
import com.example.lucad.personalapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by LucaD on 08/03/2017.
 */

public class JsonScripture {
    public ArrayList<Students> fetchStudentsFromJSON(String sp){
        ArrayList<Students>students=new ArrayList<>();

        try {
            JSONArray studentsJsonArray=new JSONArray(sp);
            for(int i=0;i<studentsJsonArray.length();i++){

                JSONObject jsonStudent=studentsJsonArray.getJSONObject(i);
                students.add(new Students(jsonStudent));
            } }catch (JSONException e) {
            e.printStackTrace();
        };
        return students;
    }

}
