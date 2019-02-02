package com.example.aneeshkjalan.cineflix.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    private static final String posterBaseURL = "https://image.tmdb.org/t/p";
    private static final String posterSize = "/w342/";
    private String title;
    private String relPosterPath;
    private String relBackdropPath;
    private String overview;
    private float rating;
    private int id;

    // Empty constructor needed for Parcel to work
    public Movie() {
    }

    public Movie(JSONObject movieObj) throws JSONException {
        title = movieObj.getString("title");
        relPosterPath = movieObj.getString("poster_path");
        overview = movieObj.getString("overview");
        rating = (float) movieObj.getDouble("vote_average");
        id = movieObj.getInt("id");
    }

    public static List<Movie> fromJSONArray(JSONArray data) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for(int idx = 0; idx < data.length(); idx++) {
            movies.add(new Movie(data.getJSONObject(idx)));
        }

        return movies;
    }

    public String getTitle() {
        return title;
    }

    public String getposterPath() {
        return posterBaseURL + posterSize + relPosterPath;
    }

    public String getbackdropPath() {
        return posterBaseURL + posterSize + relBackdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public float getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", relPosterPath='" + relPosterPath + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}
