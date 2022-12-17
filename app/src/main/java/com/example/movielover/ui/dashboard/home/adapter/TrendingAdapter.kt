package com.example.movielover.ui.dashboard.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielover.R
import com.example.movielover.ui.dashboard.home.model.Result
import com.example.movielover.ui.dashboard.home.model.ResultX

class TrendingAdapter(val items: List<ResultX>, val context: Context, val itemClickListener: OnItemClicklistener) : RecyclerView.Adapter<TrendingViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {

        holder.movieName.setText(items[position].original_title)

        holder.movieImage.setOnClickListener {
            itemClickListener.itemClicked(position,items[position].original_title,"https://image.tmdb.org/t/p/w500"+items[position].poster_path,items[position].overview,items[position].id)
        }

        Glide.with(context)
            .asBitmap()
            .load("https://image.tmdb.org/t/p/w500"+items[position].poster_path)
            .fitCenter()
            .placeholder(R.drawable.image_one)
            .into((holder.movieImage))


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(LayoutInflater.from(context).inflate(R.layout.popular_single_item, parent, false))

    }

    interface OnItemClicklistener {
        fun itemClicked(position: Int,title:String,imageString: String,overview:String,id:Int)
    }


}

class TrendingViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val movieName= view.findViewById<TextView>(R.id.movie_name)
    val movieImage = view.findViewById<ImageView>(R.id.movie_image)


}