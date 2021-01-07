package dtu.android.moroapp.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "events")
data class Event(
        @PrimaryKey val title: String,
        val genre: String,
        val image: String,
        val link: String,
        val price: Int,
        val text: String,
        val tickets: String,
        @Embedded val location: Location,
        val time: Long,
) : Serializable {
    fun getDate(): String = SimpleDateFormat("dd/MM/yyyy").format(Date(time * 1000))
    fun getTime(): String = SimpleDateFormat("HH:mm").format(Date(time * 1000))
}

data class Address(
        val city: String,
        val street: String,
        val no: String,
        val state: String,
        val zip: Int,
) : Serializable

data class Coordinates(
        val longitude: Float,
        val latitude: Float,
) : Serializable

data class Location(
        val area: String,
        @Embedded val address: Address,
        val place: String,
        @Embedded val coordinates: Coordinates,
) : Serializable

