package com.dededev.moviehero.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

class IntegerListConverters {
    @TypeConverter
    fun listToJsonInt(value: List<Int>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonIntToList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()
}