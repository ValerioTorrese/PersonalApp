package com.example.lucad.personalapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lucad.personalapp.Model.Attività;

import java.util.ArrayList;

/**
 * Created by LucaD on 05/03/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

        private static DatabaseHandler istanza;

    private static final String KEY_ID="id";
    private static final String KEY_ATTIVITA="attivita";
    private static final String KEY_DATA="data_aggiunte";
    private static final String KEY_APPUNTI="appunti";
    private static final String KEY_MONEY="soldi";

    private static final int DATABASE_VERSION=4;

    private  static final String DATABASE_NAME="Elis";

    private static final String TABLE_ACTIVITY="activity";

    public DatabaseHandler (Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTE_TABLE="CREATE TABLE "+ TABLE_ACTIVITY+ "("
                +KEY_ID+" INTEGER PRIMARY KEY, "+ KEY_ATTIVITA+" TEXT, "+
                KEY_DATA+" TEXT, "+ KEY_APPUNTI+" TEXT, "+ KEY_MONEY+ " INTEGER"+")";
    db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ACTIVITY);
    onCreate(db);
    }

    public void addAttivita(Attività attività){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues value=new ContentValues();
        value.put(KEY_ATTIVITA, attività.getTipo());
        value.put(KEY_DATA, attività.getData());
        value.put(KEY_APPUNTI,attività.getAppunti());
        value.put(KEY_MONEY, attività.getSaldo());

        long id=db.insert(TABLE_ACTIVITY, null, value);
        attività.setID((int)id);
        db.close();
    }

    public ArrayList<Attività> getAllActivita(){
        ArrayList<Attività> activita=new ArrayList<>();
        String Query="SELECT * FROM "+ TABLE_ACTIVITY;

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(Query,null);
        if(cursor.moveToFirst()){
            do{
                Attività attivita=new Attività();
                attivita.setID(Integer.parseInt(cursor.getString(0)));
                attivita.setTipo(cursor.getString(1));
                attivita.setData(cursor.getString(2));
                attivita.setAppunti(cursor.getString(3));
                attivita.setSaldo(Integer.parseInt(cursor.getString(4)));
                activita.add(attivita);
            }
            while(cursor.moveToNext());
        }
        return activita;
    }

    public int deleteAct(Attività act){
        SQLiteDatabase db=this.getWritableDatabase();
        int x=db.delete(TABLE_ACTIVITY , KEY_ID + " = ?", new String[]{String.valueOf(act.getID())});
        db.close();
        return x;
    }

    public int updateAct(Attività act){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(KEY_DATA, act.getData());
        value.put(KEY_APPUNTI,act.getAppunti());
        value.put(KEY_MONEY, act.getSaldo());
        int x=db.update(TABLE_ACTIVITY, value, KEY_ID+ " = ?", new String[]{String.valueOf(act.getID())});
        return x;
          }


public static DatabaseHandler getIstanza(Context context){
    if (istanza == null)
    {
        istanza = new DatabaseHandler(context);
    }

    return istanza;}


}
