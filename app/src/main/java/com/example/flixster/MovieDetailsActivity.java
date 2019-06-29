package com.example.flixster;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.models.Config;
import com.example.flixster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    //the base URL for the API
    public final static String API_BASE_URL = "https://api.themoviedb.org/3";
    //the parameter name for the API key
    public final static String API_KEY_PARAM = "api_key";
    //tag for logging from this activity
    public final static String TAG = "MovieDetailsActivity";

    // the movie to display
    Movie movie;

    // the view objects
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvOverview) TextView tvOverview;
    @BindView(R.id.rbVoteAverage) RatingBar rbVoteAverage;

    //instance fields
    AsyncHttpClient client;
    //the list of currently playing movies
    ArrayList<Movie> movies;
    //the recycler view
    RecyclerView rvMovies;
    // the adapter wired to the recycler view
    MovieAdapter adapter;
    // image config
    Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        // resolve the view objects
        ButterKnife.bind(this);

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
    }
//    // get the trailer videos
//    private void getVideos() {
//        //create the url
//        String url = API_BASE_URL + "/movie/" + movie.getId() + "/videos?api_key" + API_KEY_PARAM + "&language=en-US";
//        //set the request parameters
//        RequestParams params = new RequestParams();
//        params.put(API_KEY_PARAM, getString(R.string.api_key));
//        client.get(url,params, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                //load the results into movies list
//                try {
//
//                    JSONArray results = response.getJSONArray("results");
//                    JSONArray videoID = response.getJSONArray("key");
//
//                    //Log.i(TAG, String.format("Loaded %s movies", results.length()));
//                } catch (JSONException e) {
//                    // logError("Failed to parse now playing movies", e, true);
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                // logError("Failed to get data from now playing endpoint", throwable, true);
//            }
//        });
//
//        //build url for poster image
//        String imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());
//
//        // if in portrait mode, load the poster image
//
//            imageUrl = config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath());
//
//        // get the correct placeholder and imageview for the current orientation
//        int placeholderId = R.drawable.flicks_backdrop_placeholder;
//        ImageView imageView = holder.ivBackdropImage;
//
//        // load image using glide
//        Glide.with(this)
//                .load(imageUrl)
//                .bitmapTransform(new RoundedCornersTransformation(this, 25, 0))
//                .placeholder(placeholderId)
//                .error(placeholderId)
//                .into(imageView);
//
//
//    }
}
