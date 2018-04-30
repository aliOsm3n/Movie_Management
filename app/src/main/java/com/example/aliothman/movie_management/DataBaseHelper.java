package com.example.aliothman.movie_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =	2;
    private	static final String	DATABASE_NAME = "movie";
    private	static final String TABLE_MOVIES = "movies";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_IMG = "movieimg";
    private static final String COLUMN_NAME = "moviename";
    private static final String COLUMN_DATE = "moviedate";


    public  DataBaseHelper (Context context){
        super(context ,DATABASE_NAME ,null , DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String	CREATE_MOVIE_TABLE = "CREATE	TABLE " + TABLE_MOVIES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_IMG + " TEXT," +COLUMN_NAME + " TEXT," + COLUMN_DATE + " TEXT" + ")";
        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);

    }


    public void addmovie(Movie movie){
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMG, movie.getImg());
        values.put(COLUMN_NAME, movie.getTittle());
        values.put(COLUMN_DATE, movie.getDate());
        Log.e("DB add", movie.getImg());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_MOVIES, null, values);
    }


    public List<Movie> listProducts(){
        String sql = "select * from " + TABLE_MOVIES;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Movie> storemovies = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() >  0)
        {
            cursor.moveToFirst();
            do{
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(   COLUMN_ID)));
                String img =cursor.getString(cursor.getColumnIndex(COLUMN_IMG));
                Log.e("DB select", img);

                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                storemovies.add(new Movie(id, img, name , date));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storemovies;
    }

    public void deleteProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }


}
