package com.example.movielover.ui.dashboard.home.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielover.R
import com.example.movielover.ui.dashboard.home.search.model.Result
import com.example.movielover.ui.dashboard.home.model.ResultXX

class SearchAdapter(val items: List<Result>, val context: Context, val itemClickListener:OnItemClicklistener) : RecyclerView.Adapter<ViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.movieName.setText(items[position].original_title)

        holder.movieImage.setOnClickListener {
            itemClickListener.itemClicked(position,items[position].original_title,"https://image.tmdb.org/t/p/w500"+items[position].poster_path,items[position].overview)
        }
        Glide.with(context)
            .asBitmap()
            .load("https://image.tmdb.org/t/p/w500"+items[position].poster_path)
            .fitCenter()
            .placeholder(R.drawable.image_one)
            .into((holder.movieImage))







    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.search_list_single_item, parent, false))

    }

    interface OnItemClicklistener {
        fun itemClicked(position: Int,title:String,imageString: String,overview:String)
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val movieName= view.findViewById<TextView>(R.id.name)
    val movieImage = view.findViewById<ImageView>(R.id.iconImage)


}