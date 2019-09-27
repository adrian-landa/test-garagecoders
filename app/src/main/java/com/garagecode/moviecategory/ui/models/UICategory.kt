package com.garagecode.moviecategory.ui.models

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Luis L.
 *         Description:
 *         created on 26/09/2019
 */
data class UICategory(val id: Long, val name: String, val color: String, val movies: List<UIMovie>)