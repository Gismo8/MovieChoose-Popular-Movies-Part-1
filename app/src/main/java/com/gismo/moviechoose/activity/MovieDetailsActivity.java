package com.gismo.moviechoose.activity;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.gismo.moviechoose.R;
import com.gismo.moviechoose.model.MovieObject;
import com.github.florent37.shapeofview.ShapeOfView;
import com.github.florent37.shapeofview.manager.ClipPathManager;
import com.squareup.picasso.Picasso;

/**
 * Created by gismo on 2018. 03. 11..
 */

public class MovieDetailsActivity extends AppCompatActivity {

    protected MovieObject movieObject;
    protected KenBurnsView posterImage;
    protected ProgressBar progressBar;
    protected ShapeOfView shapeOfView;
    protected TextView titleView, releaseDate, averageVote, plotView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        posterImage = (KenBurnsView) findViewById(R.id.posterImage);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        shapeOfView = (ShapeOfView) findViewById(R.id.shapeOfView);
        titleView = (TextView) findViewById(R.id.titleView);
        releaseDate = (TextView) findViewById(R.id.releaseDate);
        averageVote = (TextView) findViewById(R.id.averageVote);
        plotView = (TextView) findViewById(R.id.plotView);

        movieObject = (MovieObject) getIntent().getSerializableExtra(MovieObject.class.getSimpleName());

        bindActivity();
    }

    private void bindActivity() {
        bindPosterImage();
        titleView.setText(movieObject.getOriginalTitle());
        releaseDate.setText(getFormattedReleaseYear(movieObject.getReleaseDate()));
        averageVote.setText(movieObject.getVoteAverage());
        plotView.setText(movieObject.getOverview());
    }

    private String getFormattedReleaseYear(String releaseDate) {
        String formattedReleaseYear = null;
        String[] parts = releaseDate.split("-");
        formattedReleaseYear = "(" + parts[0] + ")";
        return formattedReleaseYear;
    }

    private void bindPosterImage() {
        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Picasso.with(MovieDetailsActivity.this)
                        .load(movieObject.getImageUrl(false))
                        .centerCrop()
                        .resize(metrics.widthPixels, metrics.heightPixels / 2)
                        .into(posterImage, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError() {

                            }
                        });
            }
        }, 800);
    }
}
