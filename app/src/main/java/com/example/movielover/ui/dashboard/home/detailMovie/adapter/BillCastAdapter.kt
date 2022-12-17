package com.example.movielover.ui.dashboard.home.detailMovie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielover.R
import com.example.movielover.ui.dashboard.home.detailMovie.model.ProfileModel
import com.example.movielover.ui.dashboard.home.model.Result

class BillCastAdapter(val items: List<ProfileModel>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.profileName.setText(items[position].name)



        Glide.with(context)
            .asBitmap()
            .load(items[position].image)
            .fitCenter()
            .placeholder(R.drawable.image_one)
            .into((holder.profileImage))







    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_profile_item, parent, false))

    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val profileName= view.findViewById<TextView>(R.id.profile_name)
    val profileImage = view.findViewById<ImageView>(R.id.image)


}