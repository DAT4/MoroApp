package dtu.android.moroapp.models.event

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


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
        var time: TimeDate,
        @Embedded val location: Location,

) : Parcelable {
    @Ignore var isSaved : Boolean = false

    fun getDate() = time.format("dd/MM/yyyy")
    fun getTimeToString() = time.format("HH:mm")
    fun getLocalDate() = this.time.toLocalDateTime()

}

