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


//    private static final String DATABASE_NAME = "mydbnamedb";
//    private static final int DATABASE_VERSION = 1;
//
//    private static final String DATABASE_CREATE_MOVIE_ITEM = "create table Movie (id integer primary key autoincrement" + ",image text not null,tittle text not null, date text not null);";


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


//    public long insertnewMovie(String img, String tittle,String date) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("img", img);
//        values.put("tittle", tittle);
//        values.put("date", date);
//        return db.insert("Movie", null, values);
//    }


//    public List<Movie> getAllMovies() {
//        List<Movie> notes = new ArrayList<>();
//
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + "Movie" ;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Movie movie = new Movie(cursor.getString(cursor.getColumnIndex("image")),
//                        cursor.getString(cursor.getColumnIndex("tittle")),
//                        cursor.getString(cursor.getColumnIndex("date"))) ;
//
//                notes.add(movie);
//            } while (cursor.moveToNext());
//        }
//
//        // close db connection
//        db.close();
//
//        // return notes list
//        return notes;
//    }

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