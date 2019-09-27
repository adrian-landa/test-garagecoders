package com.garagecode.moviecategory.interfaces

/**
 * @author Luis L.
 *         Description:
 *         created on 26/09/2019
 */
interface IView{
    /***
     * Method used to linking the view and setting the start value
     */
    fun setData()

    /***
     * Method used to setting the listeners of the view controller elements
     */
    fun setListeners()
}