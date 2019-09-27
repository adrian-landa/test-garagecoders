package com.garagecode.moviecategory.data.remote.interfaces

import com.garagecode.moviecategory.enums.ExceptionType
import com.garagecode.moviecategory.ui.models.UICategory
/**
 * @author Luis L.
 *         Description:
 *         created on 27/09/2019
 */
interface IServiceMovie {
    fun getCategories(
        onResponse: (
            payload: List<UICategory>?
        ) -> Unit,
        onException: (
            type: ExceptionType,
            code: Int?,
            message: String?
        ) -> Unit
    )
}