package dtu.android.moroapp.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import androidx.room.ForeignKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Parcelize
@Entity(tableName = "events")
data class Event(
        @PrimaryKey val title: String,
        val genre: String,
        val image: String,
        val link: String,
        val category: List<String>,
        val price: Int,
        val text: String,
        val tickets: String,
        var time: Long,
        @Embedded val location: Location
) : Parcelable {
    fun getDate(): String = SimpleDateFormat("dd/MM/yyyy").format(Date(time * 1000))
    fun getTimeToString(): String = SimpleDateFormat("HH:mm").format(Date(time * 1000))
    fun getLocalDate() = this.time.toLocalDate()
    private fun Long.toLocalDate(): LocalDate {
        return Instant.ofEpochMilli(this)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
    }
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
        val place: String,
        @Embedded val address: Address,
        @Embedded val coordinates: Coordinates,
) : Parcelable