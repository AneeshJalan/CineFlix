package com.example.aneeshkjalan.cineflix;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.aneeshkjalan.cineflix.Models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class DetailActivity extends YouTubeBaseActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyBh0GB-Tc2RtDe_GaktZBYYjoWxbDJxA18";
    private static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3";
    private static final String REQUEST_TYPE = "/movie/%d/videos?";
    private static final String TMDB_API_KEY = "api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView title;
    TextView overview;
    RatingBar ratingBar;
    YouTubePlayerView youtubePlayer;

    Movie toDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = findViewById(R.id.detail_title);
        overview = findViewById(R.id.detail_overview);
        ratingBar = findViewById(R.id.ratingBar);
        youtubePlayer = findViewById(R.id.youtube_player);

        toDisplay = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        title.setText(toDisplay.getTitle());
        overview.setText(toDisplay.getOverview());
        ratingBar.setRating(toDisplay.getRating());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(TMDB_API_BASE_URL + String.format(REQUEST_TYPE, toDisplay.getId()) + TMDB_API_KEY, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");

                    if(results.length() == 0) {
                        return;
                    }

                    int index = 0;
                    while(index < results.length()) {
                        if(results.getJSONObject(index).getString("site").equals("YouTube")
                            && results.getJSONObject(index).getString("type").equals("Trailer")) {
                            break;
                        }
                        index++;
                    }

                    if(index == results.length()) {
                        return;
                    }

                    initializeYoutubePlayer(results.getJSONObject(index).getString("key"), ratingBar.getRating());
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

    private void initializeYoutubePlayer(final String key, final double rating) {
        youtubePlayer.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                if(rating > 7.5){
                    youTubePlayer.loadVideo(key);
                }
                else {
                    youTubePlayer.cueVideo(key);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                youtubePlayer = null;
            }
        });
    }
}
