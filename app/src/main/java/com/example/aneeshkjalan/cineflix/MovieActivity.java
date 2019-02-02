package com.example.aneeshkjalan.cineflix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.aneeshkjalan.cineflix.Adapters.MovieAdapter;
import com.example.aneeshkjalan.cineflix.Models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    private static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3";
    private static final String REQUEST_TYPE = "/movie/now_playing?";
    private final String TMDB_API_KEY = "api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        movies = new ArrayList<>();
        RecyclerView rvMovies = findViewById(R.id.movie_list_view);
        final MovieAdapter dispMovies = new MovieAdapter(this, movies);

        rvMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvMovies.setAdapter(dispMovies);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(TMDB_API_BASE_URL + REQUEST_TYPE + TMDB_API_KEY, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray movieData = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieData));
                    dispMovies.notifyDataSetChanged();
                    Log.d("aneesh", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
