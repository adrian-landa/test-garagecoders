package com.garagecode.moviecategory.constants

/**
 * @author Luis L.
 *         Description:
 *         created on 27/09/2019
 */
object Web {
    const val LOG_API = "logAPI"
    const val TIMEOUT_MS: Long = 25000
    const val PAGING_ITEMS = 15

    val URL_BASE: String ="http://garagecoders.rocks:4000"
    private const val API: String = "/api"
    const val EP_MOVIES = "/movies"


    const val URL_SERVICE_MOVIES: String = "$API$EP_MOVIES"

    const val DETAIL_CATEGORIES: String = "/categories"
}