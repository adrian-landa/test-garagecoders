package com.garagecode.moviecategory.data.remote.api

import com.garagecode.moviecategory.constants.Web
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface APIMovies {
    @GET(Web.URL_SERVICE_MOVIES + Web.DETAIL_CATEGORIES)
    fun getCategories(): Flowable<ResponseBody>
}