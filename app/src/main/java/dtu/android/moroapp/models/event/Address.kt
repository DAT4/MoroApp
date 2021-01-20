package dtu.android.moroapp.models.event

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
        val city: String,
        val street: String,
        val no: String,
        val state: String,
        val zip: Int,
) : Parcelable