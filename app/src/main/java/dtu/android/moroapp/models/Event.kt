package dtu.android.moroapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Event (
        val title: String,
        val genre: String,
        val image: String,
        val link: String,
        val other: MutableList<String>,
        val price: Int,
        val text: String,
        val tickets: String,
        val location: Location,
        val time: Long,
) : Parcelable {
    fun getDate(): String = SimpleDateFormat("dd/MM/yyyy").format(Date(time * 1000))
    fun getTime(): String = SimpleDateFormat("HH:mm").format(Date(time * 1000))
}
@Parcelize
data class Address(
        val city: String,
        val street: String,
        val no: String,
        val state: String,
        val zip: Int,
) : Parcelable
@Parcelize
data class Coordinates(
        val longitude: Float,
        val latitude: Float,
) : Parcelable
@Parcelize
data class Location(
        val area: String,
        val address: Address,
        val place: String,
        val coordinates: Coordinates,
) : Parcelable

