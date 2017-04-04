package com.sergiomeza.popularmovies.stage1.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast

import com.sergiomeza.popularmovies.stage1.R
import com.sergiomeza.popularmovies.stage1.model.Movie
import com.sergiomeza.popularmovies.stage1.presenter.DetailPresenter
import com.sergiomeza.popularmovies.stage1.ui.view.DetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {
    /**
     * if the validation in the presenter is passed we load the data into the View
     */
    override fun onMovieLoaded(mMovie: Movie) {
        Picasso.with(this).load(mMovie.mediumbackgroundUrl())
                .into(mImgDetail)
        mCollapsingDetail.title = mMovie.title
        mTxtMovieTitle.text = mMovie.original_title
        mTxtMovieYear.text = mMovie.parseDate()
        mTxtMovieVotes.text = getString(R.string.movie_votes,
                mMovie.vote_average.toString())
        Picasso.with(this).load(mMovie.smallImageUrl())
                .into(mImgPoster)
        mTxtMovieOverview.text = mMovie.overview
    }

    /**
     * If an error is occurred
     */
    override fun onMovieError(mError: String) {
        Toast.makeText(this, mError, Toast.LENGTH_SHORT).show()
        supportFinishAfterTransition();
    }

    var mPresenter: DetailPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /**
         * Initialize the toolbar
         */
        setSupportActionBar(mToolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener {
            supportFinishAfterTransition();
        }

        /**
         * Load the View with the extras
         */
        mPresenter = DetailPresenter(this, this)
        mPresenter?.loadMovie(intent)
    }
}
