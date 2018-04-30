package com.example.aliothman.movie_management;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.List;

public class Adaptor extends RecyclerView.Adapter<Adaptor.ViewHolder> {

    List<Movie> movieList;
    Context context ;
    Uri uri;
    Movie movie;

    public Adaptor(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptor.ViewHolder holder, int position) {
      movie = movieList.get(position);
     holder.imageView.setImageURI(uri.parse(movie.getImg()));
     holder.tittleTV.setText(movie.getTittle());
     holder.DateTV.setText(movie.getDate());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView tittleTV , DateTV;
        ImageButton Love;

        public ViewHolder(final View view) {
            super(view);
            imageView = view.findViewById(R.id.movie_image);
            tittleTV = view.findViewById(R.id.movie_tittle);
            DateTV = view.findViewById(R.id.movie_Date);
            Love = view.findViewById(R.id.Add_To_favourit);
            Love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Done to favourite", Toast.LENGTH_SHORT).show();
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(view.getContext());
                    dataBaseHelper.deleteProduct(movie.getId());
                    ((Activity)context).finish();
                    context.startActivity(((Activity) context).getIntent());
                    return true;
                }
            });
        }
    }
}
