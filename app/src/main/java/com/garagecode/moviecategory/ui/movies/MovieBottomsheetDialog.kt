package com.garagecode.moviecategory.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.garagecode.moviecategory.R
import com.garagecode.moviecategory.constants.App
import com.garagecode.moviecategory.data.entities.Movie
import com.garagecode.moviecategory.interfaces.IView
import com.garagecode.moviecategory.ui.adapters.CategoryAdapter
import com.garagecode.moviecategory.ui.adapters.MovieAdapter
import com.garagecode.moviecategory.ui.adapters.decorators.SpaceDecorator
import com.garagecode.moviecategory.ui.models.UIMovie
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_movies.*

/**
 * @author Luis L.
 *         Description:
 *         created on 26/09/2019
 */
class MovieBottomsheetDialog : BottomSheetDialogFragment(), IView {


    companion object {
        fun newInstance(category: String, items: List<UIMovie>): DialogFragment {
            val dialog: DialogFragment = MovieBottomsheetDialog()
            val arguments = Bundle()
            arguments.putString(App.KEY_CATEGORY, category)
            arguments.putParcelableArrayList(App.KEY_ITEMS, ArrayList(items))
            dialog.arguments = arguments
            return dialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottomsheet_movies, container, false)
        return view ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        setListeners()
    }

    /***
     * Method used to linking the view and setting the start value
     */
    override fun setData() {
        context?.let { ctx ->
            val adapter = MovieAdapter()
            recyclerMovies.adapter = adapter
            recyclerMovies.layoutManager = LinearLayoutManager(ctx)
            recyclerMovies.addItemDecoration(
                SpaceDecorator(
                    resources.getDimension(R.dimen.dimen_medium).toInt()
                )
            )
            val args = arguments
            if (args != null) {
                val list: List<UIMovie> = args.getParcelableArrayList(App.KEY_ITEMS)
                val category: String = args.getString(App.KEY_CATEGORY, "")
                adapter.submitList(list)
                tvBelongingCategory.text = category
            }
        }

    }

    /***
     * Method used to setting the listeners of the view controller elements
     */
    override fun setListeners() {
    }

}