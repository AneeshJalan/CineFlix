package com.example.aneeshkjalan.cineflix.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private static final String posterBaseURL = "https://image.tmdb.org/t/p";
    private static final String posterSize = "/w342/";
    private String title;
    private String relPosterPath;
    private String overview;

    public Movie(JSONObject movieObj) throws JSONException {
        title = movieObj.getString("title");
        relPosterPath = movieObj.getString("poster_path");
        overview = movieObj.getString("overview");
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

    public String getOverview() {
        return overview;
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
