package com.garagecode.moviecategory.ui.models

import android.os.Parcel
import android.os.Parcelable

data class UIMovie(
    val title: String,
    val subtitle: String,
    val year: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(subtitle)
        parcel.writeInt(year)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UIMovie> {
        override fun createFromParcel(parcel: Parcel): UIMovie {
            return UIMovie(parcel)
        }

        override fun newArray(size: Int): Array<UIMovie?> {
            return arrayOfNulls(size)
        }
    }
}