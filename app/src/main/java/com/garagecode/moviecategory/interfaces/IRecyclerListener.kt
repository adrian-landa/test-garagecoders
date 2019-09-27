package com.garagecode.moviecategory.interfaces

import com.garagecode.moviecategory.ui.models.UICategory

/**
 * @author Luis L.
 *         Description:
 *         created on 26/09/2019
 */
interface IRecyclerListener {
    /**
     * Method used to communicate to component that an item has been clicked
     */
    fun onItemClick(item: UICategory)
}