package com.example.aneeshkjalan.cineflix.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aneeshkjalan.cineflix.Models.Movie;
import com.example.aneeshkjalan.cineflix.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;


    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.display_movies, viewGroup, false);
        Log.d("testing", "Inside onCreateViewHolder.");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Movie toSet = movies.get(i);
        Log.d("testing", "Inside onBindViewHolder. Position is " + i + ".");
        viewHolder.bind(toSet);
    }

    @Override
    public int getItemCount() {

        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView overview;
        ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            overview = itemView.findViewById(R.id.movie_overview);
            poster= itemView.findViewById(R.id.movie_poster);
        }

        public void bind(Movie toSet) {
            Log.d("testing", "Inside bind of ViewHolder.");
            title.setText(toSet.getTitle());
            overview.setText(toSet.getOverview());
            Glide.with(context).load(toSet.getposterPath()).into(poster);
        }
    }
}
