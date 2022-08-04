package com.dededev.moviehero.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dededev.moviehero.R
import com.dededev.moviehero.core.domain.model.Movie
import com.dededev.moviehero.core.ui.ViewModelFactory
import com.dededev.moviehero.core.utils.Credentials
import com.dededev.moviehero.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showSelectedMovie(movie)
    }

    private fun showSelectedMovie(movie: Movie?) {
        movie?.let {
            binding.apply {
                tvDetailTitle.text = movie.title
                Glide.with(applicationContext)
                    .load(Credentials.BASE_IMAGE_URL + movie.backdropPath)
                    .into(ivDetailImage)
                tvDetailOverview.text = movie.overview
                tvDetailRelease.text = movie.releaseDate
                tvDetailRating.text = movie.voteAverage.toString()
            }
            var isFavorite = movie.isFavorite
            setStatusFavorite(isFavorite)
            binding.fab.setOnClickListener {
                isFavorite = !isFavorite
                detailViewModel.setFavoriteMovie(movie, isFavorite)
                setStatusFavorite(isFavorite)
            }
        }
    }
    
    private fun setStatusFavorite(isStatusFavorite: Boolean) {
        if (isStatusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}