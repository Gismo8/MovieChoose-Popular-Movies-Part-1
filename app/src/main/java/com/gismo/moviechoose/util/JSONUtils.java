package com.gismo.moviechoose.util;

import android.graphics.Movie;
import android.text.TextUtils;
import android.util.Log;

import com.gismo.moviechoose.model.MovieObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 2018. 03. 09..
 */

public class JSONUtils {
    public static final String RESULTS = "results";
    public static final String ORIGINAL_TITLE = "original_title";
    public static final String POSTER_PATH = "poster_path";
    public static final String RELEASE_DATE = "release_date";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String OVERVIEW = "overview";

    /**
     * Return a list of {@link Movie} objects that has been built up from
     * parsing the given JSON response.
     */
    public static List<MovieObject> extractFeatureFromJson(String movieJSON) {

        if (TextUtils.isEmpty(movieJSON)) {
            return null;
        }
        List<MovieObject> movies = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(movieJSON);
            JSONArray resultsArray = baseJsonResponse.getJSONArray(RESULTS);

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject results = resultsArray.getJSONObject(i);

                String originalTitle = "";
                if (results.has(ORIGINAL_TITLE)) {
                    originalTitle = results.optString(ORIGINAL_TITLE);
                }

                String posterPath = "";
                if (results.has(POSTER_PATH)) {
                    posterPath = results.optString(POSTER_PATH);
                }

                String releaseDate = "";
                if (results.has(RELEASE_DATE)) {
                    releaseDate = results.optString(RELEASE_DATE);
                }

                String voteAverage = "";
                if (results.has(VOTE_AVERAGE)) {
                    voteAverage = results.optString(VOTE_AVERAGE);
                }

                String overview = "";
                if (results.has(OVERVIEW)) {
                    overview = results.optString(OVERVIEW);
                }

                MovieObject movie = new MovieObject(originalTitle, posterPath, releaseDate, voteAverage, overview);
                movies.add(movie);

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }

        // Return the list of movies
        return movies;
    }
}
