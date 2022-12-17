package com.example.movielover.ui.dashboard.favourites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielover.R
import com.example.movielover.data.room.model.FavouriteModel
import com.example.movielover.ui.dashboard.home.adapter.TrendingViewHolder
import com.example.movielover.ui.dashboard.home.model.ResultX

class FavouriteAdapter(val items: List<FavouriteModel>, val context: Context, val itemClickListener: OnItemClicklistener) : RecyclerView.Adapter<TrendingViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {

        holder.movieName.setText(items[position].movieName)

        holder.movieImage.setOnClickListener {
        }

        Glide.with(context)
            .asBitmap()
            .load(items[position].movieImage)
            .fitCenter()
            .placeholder(R.drawable.image_one)
            .into((holder.movieImage))


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(LayoutInflater.from(context).inflate(R.layout.popular_single_item, parent, false))

    }

    interface OnItemClicklistener {
    }


}

class FavouriteViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val movieName= view.findViewById<TextView>(R.id.movie_name)
    val movieImage = view.findViewById<ImageView>(R.id.movie_image)


}