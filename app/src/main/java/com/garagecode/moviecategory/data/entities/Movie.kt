package com.garagecode.moviecategory.data.entities

/**
 * @author Luis L.
 *         Description: Aquí irian los POJOS para el almancenamiento en la base de datos
 *         created on 26/09/2019
 */
data class Movie(
    val id: Long,
    val title: String,
    val subtitle: String,
    val year: Int
)