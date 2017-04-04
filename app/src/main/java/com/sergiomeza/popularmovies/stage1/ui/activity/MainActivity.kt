package com.sergiomeza.popularmovies.stage1.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.sergiomeza.popularmovies.stage1.MainView

import com.sergiomeza.popularmovies.stage1.R
import com.sergiomeza.popularmovies.stage1.model.ApiResponse
import com.sergiomeza.popularmovies.stage1.model.Movie
import com.sergiomeza.popularmovies.stage1.presenter.MainPresenter
import com.sergiomeza.popularmovies.stage1.ui.adapter.MainAdapter
import com.sergiomeza.popularmovies.stage1.ui.adapter.OnMovieItemClickListener
import com.sergiomeza.popularmovies.stage1.util.Const
import com.sergiomeza.popularmovies.stage1.util.ErrorView
import com.sergiomeza.popularmovies.stage1.util.init
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity using the kotlin built In extensions function, allowing us to avoid the findViewId
 * Call to interact with the Xml View
 */
class MainActivity : AppCompatActivity(), MainView, OnMovieItemClickListener {
    /**
     * When a movie is clicked
     */
    override fun onItemClick(mMovie: Movie, mView: View) {
        val mIntentDetail = Intent(this, DetailActivity::class.java)
        mIntentDetail.putExtra(Const.DETAIL_DATA, mMovie)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                mView, getString(R.string.transition_image))
        ActivityCompat.startActivity(this,
            mIntentDetail, options.toBundle())
    }

    /**
     * Show loading state of the app depending if it is from swipe to refresh or not
     */
    override fun showLoading(mRefresh: Boolean) {
        if(mRefresh) {
            mSwipe.isRefreshing = true
        }
        else {
            mProgressBar.visibility = View.VISIBLE
            mLinearError.visibility = View.GONE
            mRecycler.visibility = View.GONE
        }
    }

    /**
     * Hide loader view (progressBar) and display to the user the interface depending of the mError
     * @param boolean to know if the hide is with error or not to display or Gone a respective View
     */
    override fun hideLoading(mError: Boolean) {
        mProgressBar.visibility = View.GONE
        mSwipe.isRefreshing = false
        if(mError) {
            mLinearError.visibility = View.VISIBLE
            mRecycler.visibility = View.GONE
        }
        else {
            mLinearError.visibility = View.GONE
            mRecycler.visibility = View.VISIBLE
        }
    }

    /**
     * Called when a request to the Api has succeded
     */
    override fun onRequestSuccess(mResult: ApiResponse) {
        /**
         * Check if the result list (movie list) it's not empty
         */
        if(mResult.results.isNotEmpty()) {
            /**
             * Add data to the adapter innerlist
             */
            mAdapter.items = mResult.results as MutableList<Movie>
        }
    }

    /**
     * When the request is an error,
     */
    override fun onRequestError(mError: String) {
        /**
         * We inflate the error view custom class into de Empty Linear layout of the XML file of
         * the activity passing the error string to display to the user
         */
        mLinearError.removeAllViews()
        mLinearError.addView(ErrorView(this).init(mError))
        Toast.makeText(this, mError, Toast.LENGTH_SHORT).show()
    }

    /**
     * Presenter of the View
     */
    var mPresenter: MainPresenter? = null
    var mAdapter = MainAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Saved instance
         */
        if(savedInstanceState == null){
            /**
             * Initilize the recyclerview using the extension function located in the Extensions.kt
             * file
             */
            mRecycler.init(LinearLayout.VERTICAL)
            val mGridLayoutManager = GridLayoutManager(this, 2)
            mRecycler.layoutManager = mGridLayoutManager
            mRecycler.adapter = mAdapter

            /**
             * Initialize the presenter and call the getMovies method to request the movies
             * to the api
             */
            mPresenter = MainPresenter(this, this)
            mPresenter?.getMovies()
        }

        /**
         * When the user swipe to refresh
         */
        mSwipe.setOnRefreshListener {
            mPresenter?.getMovies(mRefresh = true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
    }
}
