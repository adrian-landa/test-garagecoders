package com.garagecode.moviecategory.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.garagecode.moviecategory.R
import com.garagecode.moviecategory.constants.App
import com.garagecode.moviecategory.interfaces.IRecyclerListener
import com.garagecode.moviecategory.interfaces.IView
import com.garagecode.moviecategory.ui.adapters.CategoryAdapter
import com.garagecode.moviecategory.ui.adapters.decorators.SpaceDecorator
import com.garagecode.moviecategory.ui.models.UICategory
import com.garagecode.moviecategory.util.getViewModel
import com.garagecode.moviecategory.viewmodels.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * @author Luis L.
 *         Description:
 *         created on 26/09/2019
 */
class CategoryFragment : Fragment(), IView,IRecyclerListener {


    private val viewmodel: CategoryViewModel by lazy { getViewModel { CategoryViewModel(context!!) } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
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
        setToolbar()
        context?.let { ctx ->
            val adapter = CategoryAdapter(this)
            recyclerResult.adapter = adapter
            recyclerResult.layoutManager = LinearLayoutManager(ctx)
            recyclerResult.addItemDecoration(
                SpaceDecorator(
                    resources.getDimension(R.dimen.dimen_medium).toInt()
                )
            )
            viewmodel.categories.observe(this, Observer { list ->
                if (list.isEmpty()) {
                    recyclerResult.visibility = View.GONE
                    emptyContainer.visibility = View.VISIBLE
                } else {
                    recyclerResult.visibility = View.VISIBLE
                    emptyContainer.visibility = View.GONE
                }
                adapter.submitList(list)
            })

            viewmodel.webError.observe(this, Observer { error ->
                error.getContentIfNotHandled()?.let { message ->
                    Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
                }
            })
        }
        viewmodel.loading.observe(this, Observer { isLoading ->
            val visibility = if (isLoading) View.VISIBLE else View.GONE
            progress.visibility = visibility
        })

        viewmodel.movieDialog.observe(this, Observer { dialog ->
            dialog.getContentIfNotHandled()?.show(fragmentManager!!, App.TAG_FRAGMENT_DIALOG_MOVIES)
        })


        viewmodel.getCategories()

    }

    /***
     * Method used to setting the listeners of the view controller elements
     */
    override fun setListeners() {
    }

    /**
     * Method used to communicate to component that an item has been clicked
     */
    override fun onItemClick(item: UICategory) {
        viewmodel.onCategorySelected(item)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        recyclerResult.invalidateItemDecorations()
    }

    /**
     * Method used to sync the layout toolbar with the actionbar.
     */
    private fun setToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }


}