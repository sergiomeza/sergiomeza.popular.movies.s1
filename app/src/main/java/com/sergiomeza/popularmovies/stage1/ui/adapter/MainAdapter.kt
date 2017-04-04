package com.sergiomeza.popularmovies.stage1.ui.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.sergiomeza.popularmovies.stage1.R
import com.sergiomeza.popularmovies.stage1.model.Movie
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

/**
 * Created by sergiomeza on 4/4/17.
 */
class MainAdapter(mMovieClick: OnMovieItemClickListener): RecyclerView.Adapter<MainAdapter.ViewHolderGrid>() {
    /**
     * Items of the Movielist
     */
    var items: MutableList<Movie> by Delegates.observable(mutableListOf(),
            { _, _, _ -> notifyDataSetChanged() })
    var mMovieClick: OnMovieItemClickListener? = null
    init {
        this.mMovieClick = mMovieClick
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderGrid {
        val mInflater = LayoutInflater.from(parent?.context)
        return MainAdapter.ViewHolderGrid(mInflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderGrid?, position: Int) {
        holder?.bindView(items[position], mMovieClick!!)
    }

    override fun getItemCount(): Int = items.count()

    class ViewHolderGrid(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        var mImg: ImageView = itemView.findViewById(R.id.mImgGrid) as ImageView
        /**
         * Bindview into recyclerView
         */
        fun bindView(mMovie: Movie, mMovieClick: OnMovieItemClickListener){
            /**
             * Load image url with picasso
             */
            Picasso.with(itemView.context)
                    .load(mMovie.smallImageUrl())
                    .into(mImg)

            itemView.setOnClickListener {
                mMovieClick.onItemClick(mMovie, mImg)
            }
        }
    }
}

//
interface OnMovieItemClickListener {
    fun onItemClick(mMovie: Movie, mView: View)
}