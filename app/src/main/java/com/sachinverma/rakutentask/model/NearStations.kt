package com.sachinverma.rakutentask.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Sachin Verma on 2020-02-09.
 */

data class NearStations(var id: String,
                        var street_name: String,
                        var city: String,
                        var utm_x: String,
                        var utm_y: String,
                        var lat: String,
                        var lon: String,
                        var furniture: String,
                        var buses: String,
                        var distance: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(street_name)
        parcel.writeString(city)
        parcel.writeString(utm_x)
        parcel.writeString(utm_y)
        parcel.writeString(lat)
        parcel.writeString(lon)
        parcel.writeString(furniture)
        parcel.writeString(buses)
        parcel.writeString(distance)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NearStations> {
        override fun createFromParcel(parcel: Parcel): NearStations {
            return NearStations(parcel)
        }

        override fun newArray(size: Int): Array<NearStations?> {
            return arrayOfNulls(size)
        }
    }

}