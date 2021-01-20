package dtu.android.moroapp.models.event

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
        val area: String,
        val place: String,
        @Embedded val address: Address,
        @Embedded val coordinates: Coordinates,
) : Parcelable