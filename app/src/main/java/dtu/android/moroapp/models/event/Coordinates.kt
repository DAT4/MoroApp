package dtu.android.moroapp.models.event

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinates(
        val longitude: Float,
        val latitude: Float,
) : Parcelable