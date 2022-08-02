package com.dededev.moviehero.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dededev.moviehero.R
import com.dededev.moviehero.core.data.Resource
import com.dededev.moviehero.core.ui.MovieAdapter
import com.dededev.moviehero.core.ui.ViewModelFactory
import com.dededev.moviehero.databinding.ActivitySearchBinding
import com.dededev.moviehero.detail.DetailActivity

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchView = binding.searchView
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedMovie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedMovie)
            startActivity(intent)
        }

        val factory = ViewModelFactory.getInstance(this)
        searchViewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchViewModel.searchMovies(query).observe(this@SearchActivity) { movie ->
                        when (movie) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                movieAdapter.setData(movie.data)
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                binding.viewError.root.visibility = View.VISIBLE
                                binding.viewError.tvError.text =
                                    movie.message ?: getString(R.string.something_wrong)
                            }
                        }
                    }

                    with(binding.rvMovie) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = movieAdapter
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}