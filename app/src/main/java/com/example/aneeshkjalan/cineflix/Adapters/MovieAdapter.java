package com.example.aneeshkjalan.cineflix.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aneeshkjalan.cineflix.DetailActivity;
import com.example.aneeshkjalan.cineflix.Models.Movie;
import com.example.aneeshkjalan.cineflix.R;

import org.parceler.Parcels;

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

        RelativeLayout movieHolder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.detail_title);
            overview = itemView.findViewById(R.id.movie_overview);
            poster= itemView.findViewById(R.id.movie_poster);
            movieHolder = itemView.findViewById(R.id.movie_holder);
        }

        public void bind(final Movie toSet) {
            Log.d("testing", "Inside bind of ViewHolder.");
            title.setText(toSet.getTitle());
            overview.setText(toSet.getOverview());
            Glide.with(context).load(toSet.getposterPath()).into(poster);

            // Add onClickListener to the holder
            movieHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent showDetail = new Intent(context, DetailActivity.class);
                    showDetail.putExtra("movie", Parcels.wrap(toSet));
                    context.startActivity(showDetail);
                }
            });
        }
    }
}
