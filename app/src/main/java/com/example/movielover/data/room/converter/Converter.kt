package com.example.movielover.data.room.converter

import androidx.room.TypeConverter
import com.example.movielover.data.room.model.FavouriteModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    @TypeConverter
    fun StringToModel(dataString: String?): FavouriteModel? {
        return Gson().fromJson<FavouriteModel>(dataString, object : TypeToken<FavouriteModel>() {}.type)
    }

    @TypeConverter
    fun ModelToString(question: FavouriteModel?): String? {
        return Gson().toJson(question)
    }
}