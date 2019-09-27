package com.garagecode.moviecategory.data.remote.interfaces

import com.garagecode.moviecategory.enums.ExceptionType


interface IWebErrorListener {
    fun handleException(type: ExceptionType, code: Int?, message: String?)
}