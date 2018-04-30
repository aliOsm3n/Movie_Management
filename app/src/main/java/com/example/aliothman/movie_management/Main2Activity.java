package com.example.aliothman.movie_management;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    ImageView image;
    EditText Edtittle , EdDate ;
    Button Done ;

    Uri imageUri ;


    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/Raleway-ExtraBold.ttf");
        image = findViewById(R.id.loadimage);
        Edtittle = findViewById(R.id.loadTittle);
        Edtittle.setTypeface(myCustomFont);
        EdDate = findViewById(R.id.loadDate);
        EdDate.setTypeface(myCustomFont);
        Done = findViewById(R.id.Done);
        Done.setTypeface(myCustomFont);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              pick();
            }
        });
    }



    void pick(){
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            image.setImageURI(imageUri);

        }}

    public void Donee(View view) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        Movie movie = new Movie(imageUri.toString() ,Edtittle.getText().toString() ,EdDate.getText().toString() );
        dataBaseHelper.addmovie(movie);
        Toast.makeText(this, "ADD success ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Main2Activity.this , MainActivity.class);
        startActivity(intent);
        finish();
    }
}
