package com.garagecode.moviecategory.interfaces

import com.garagecode.moviecategory.ui.models.UICategory

/**
 * @author Luis L.
 *         Description:
 *         created on 26/09/2019
 */
interface ICategory {
    interface UseCases {
        /**
         * Method used to fetch the available categories
         */
        fun getCategories()

        /**
         * Method used to open the list of category belonging movies
         */
        fun onCategorySelected(category:UICategory)
    }
}