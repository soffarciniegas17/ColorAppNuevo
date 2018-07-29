package com.sophia1.colorappnuevo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    Context context;
    private static final String dataName ="partida.db";
    private static final String tableName = "CREATE TABLE JUEGO (ID INTEGER PRIMARY KEY AUTOINCREMENT, PUNTAJE DOUBLE)";
    private static final int version = 1;

    public DataBase(Context context){
        super(context, dataName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableName);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CREATE"+tableName);
        db.execSQL(tableName);

    }

    public void guardar (double puntaje){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PUNTAJE", puntaje);
        db.insert("JUEGO", null, values);
    }

    public Cursor puntajes (){
        SQLiteDatabase db = getReadableDatabase();
        String find  [] = {"PUNTAJE"};
        String orderBy = "PUNTAJE DESC";
        String limit = "5";

        Cursor cursor;
        try {
            cursor= db.query("JUEGO",find, null, null, null, orderBy, limit );
            return cursor;
        } catch (Exception e){}

        return null;
    }
}
