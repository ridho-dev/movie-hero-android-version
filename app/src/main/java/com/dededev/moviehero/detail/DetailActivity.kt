package com.dededev.moviehero.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dededev.moviehero.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}