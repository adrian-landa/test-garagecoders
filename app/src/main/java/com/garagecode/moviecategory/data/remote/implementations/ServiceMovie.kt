package com.garagecode.moviecategory.data.remote.implementations

import android.content.Context
import com.garagecode.moviecategory.constants.Json
import com.garagecode.moviecategory.constants.Web
import com.garagecode.moviecategory.data.remote.api.APIMovies
import com.garagecode.moviecategory.data.remote.interfaces.IServiceMovie
import com.garagecode.moviecategory.enums.ExceptionType
import com.garagecode.moviecategory.ui.models.UICategory
import com.garagecode.moviecategory.ui.models.UIMovie
import com.garagecode.moviecategory.util.retrofit.NoInternetException
import com.garagecode.moviecategory.util.retrofit.RetrofitFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * @author Luis L.
 *         Description:
 *         created on 27/09/2019
 */
class ServiceMovie(context: Context, private val compositeDisposable: CompositeDisposable) : IServiceMovie {

    private val service: APIMovies = RetrofitFactory.makeService(Web.URL_BASE, APIMovies::class.java, context)

    override fun getCategories(
        onResponse: (payload: List<UICategory>?) -> Unit,
        onException: (type: ExceptionType, code: Int?, message: String?) -> Unit
    ) {
        compositeDisposable.add(
            service.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        val jsonResponse = JSONObject(response.string())
                        val data = jsonResponse.getJSONArray(Json.LABEL_DATA)
                        val categoryList: ArrayList<UICategory> = ArrayList()
                        for (i in 0 until data.length()) {
                            val auxCategory = data.getJSONObject(i)
                            val auxMovieList = auxCategory.getJSONArray(Json.LABEL_CATEGORY_MOVIES)
                            val movieList = (0 until auxMovieList.length())
                                .map { index -> auxMovieList.getJSONObject(index) }
                                .map { movie ->
                                    UIMovie(
                                        title = movie.getString(Json.LABEL_TITLE),
                                        subtitle = movie.getString(Json.LABEL_SUBTITLE),
                                        year = movie.getInt(Json.LABEL_YEAR)
                                    )
                                }

                            categoryList.add(
                                UICategory(
                                    id = auxCategory.getLong(Json.LABEL_ID),
                                    name = auxCategory.getString(Json.LABEL_CATEGORY_NAME),
                                    color = auxCategory.getString(Json.LABEL_COLOR),
                                    movieList = movieList
                                )
                            )
                        }
                        onResponse.invoke(categoryList)

                    },
                    { errorResponse ->
                        when (errorResponse) {
                            is HttpException -> onException.invoke(
                                ExceptionType.HTTP,
                                errorResponse.code(),
                                errorResponse.message
                            )
                            is SocketTimeoutException -> onException.invoke(
                                ExceptionType.TIME_OUT,
                                null,
                                errorResponse.message
                            )
                            is NoInternetException -> onException.invoke(
                                ExceptionType.NO_INTERNET,
                                null,
                                errorResponse.message
                            )
                            is IOException -> onException.invoke(ExceptionType.IO, null, errorResponse.message)
                        }
                    }
                )
        )

    }
}