package com.garagecode.moviecategory.viewmodels

import android.content.Context
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.garagecode.moviecategory.data.remote.implementations.ServiceMovie
import com.garagecode.moviecategory.data.remote.interfaces.IServiceMovie
import com.garagecode.moviecategory.enums.ExceptionType
import com.garagecode.moviecategory.ui.models.UICategory
import com.garagecode.moviecategory.interfaces.ICategory
import com.garagecode.moviecategory.ui.movies.MovieBottomsheetDialog
import com.garagecode.moviecategory.util.WrapperEvent
import io.reactivex.disposables.CompositeDisposable

class CategoryViewModel(context: Context) : ViewModel(), ICategory.UseCases, ICategory.RequestListener {


    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val categories: MutableLiveData<List<UICategory>> = MutableLiveData()
    val movieDialog: MutableLiveData<WrapperEvent<DialogFragment>> = MutableLiveData()
    val webError: MutableLiveData<WrapperEvent<String>> = MutableLiveData()
    private val disposable = CompositeDisposable()
    private val remote: IServiceMovie = ServiceMovie(context, disposable)

    /**
     * Method used to fetch the available categories
     */
    override fun getCategories() {
        loading.value = true
        remote.getCategories(
            onResponse = this::onCategoryResponse,
            onException = this::handleException
        )
    }

    /**
     * Method used to open the list of movies belonging to category
     */
    override fun onCategorySelected(category: UICategory) {
        val dialog = MovieBottomsheetDialog.newInstance(category = category.name, items = category.movieList)
        movieDialog.value = WrapperEvent(dialog)
    }

    override fun onCategoryResponse(payload: List<UICategory>?) {
        categories.value = payload ?: ArrayList()
        loading.value = false
    }

    override fun handleException(type: ExceptionType, code: Int?, message: String?) {
        loading.value = false
        webError.value = WrapperEvent(message ?: "Error Web")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


}