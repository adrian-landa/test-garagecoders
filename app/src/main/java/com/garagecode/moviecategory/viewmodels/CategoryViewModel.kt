package com.garagecode.moviecategory.viewmodels

import android.content.Context
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.garagecode.moviecategory.data.entities.Movie
import com.garagecode.moviecategory.ui.models.UICategory
import com.garagecode.moviecategory.interfaces.ICategory
import com.garagecode.moviecategory.ui.models.UIMovie
import com.garagecode.moviecategory.ui.movies.MovieBottomsheetDialog
import com.garagecode.moviecategory.util.WrapperEvent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CategoryViewModel(context: Context) : ViewModel(), ICategory.UseCases {



    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val categories: MutableLiveData<List<UICategory>> = MutableLiveData()
    val movieDialog: MutableLiveData<WrapperEvent<DialogFragment>> = MutableLiveData()
    val webError: MutableLiveData<WrapperEvent<String>> = MutableLiveData()
    private val disposable = CompositeDisposable()
    private val job = Job()
    private val mainThread = CoroutineScope(job + Dispatchers.Main)
    private val ioThread = CoroutineScope(job + Dispatchers.IO)

    /**
     * Method used to fetch the available categories
     */
    override fun getCategories() {
        loading.value = true
        val result: ArrayList<UICategory> = ArrayList()
        result.add(UICategory(id = 0, name = "Comedy", color = "#5062d4",
            movies = listOf(UIMovie("Volver al futuro","01",2000))))
        result.add(UICategory(id = 1, name = "Drama", color = "#b54d26",
            movies = listOf(UIMovie("Volver al futuro 02","02",2000))))
        result.add(UICategory(id = 2, name = "Western", color = "#38c499",
            movies = listOf(UIMovie("Volver al futuro 063","01",2000))))
        categories.value = result
        loading.value = false
    }

    /**
     * Method used to open the list of category belonging movies
     */
    override fun onCategorySelected(category: UICategory) {
        val dialog = MovieBottomsheetDialog.newInstance(category.movies)
        movieDialog.value = WrapperEvent(dialog)
    }



}