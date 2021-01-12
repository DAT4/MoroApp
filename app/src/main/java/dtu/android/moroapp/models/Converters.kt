package dtu.android.moroapp.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(string: String): List<String> {
        return Gson().fromJson(string, object : TypeToken<List<String>>(){}.type)
    }
    @TypeConverter
    fun fromList(list: List<String>) = Gson().toJson(list)
}