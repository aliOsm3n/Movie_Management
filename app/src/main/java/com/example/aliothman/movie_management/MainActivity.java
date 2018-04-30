package com.example.aliothman.movie_management;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adaptor adaptor;
    List<Movie> movieList = new ArrayList<>();
    Button bat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bat = findViewById(R.id.bat);
        bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.RecyclerMovies);


    }

    @Override
    protected void onStart() {
        super.onStart();

        adaptor = new Adaptor(setData(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptor);
    }

    List<Movie> setData() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        return dataBaseHelper.listProducts();
    }


}
