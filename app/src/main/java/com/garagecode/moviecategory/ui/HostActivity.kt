package com.garagecode.moviecategory.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.garagecode.moviecategory.R
import kotlinx.android.synthetic.main.activity_host.*

class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        setToolbar()
    }

    /**
     * Method used to sync the layout toolbar with the actionbar.
     */
    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let { actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(false)
            actionBar.setDisplayShowTitleEnabled(true)
        }
    }
}