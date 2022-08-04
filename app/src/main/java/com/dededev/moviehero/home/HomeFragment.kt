package com.dededev.moviehero.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dededev.moviehero.R
import com.dededev.core.data.Resource
import com.dededev.core.ui.MovieAdapter
import com.dededev.moviehero.databinding.FragmentHomeBinding
import com.dededev.moviehero.detail.DetailActivity
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedMovie ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedMovie)
                startActivity(intent)
            }

            homeViewModel.getPopularMovies.observe(viewLifecycleOwner) { movie ->
                if (movie != null) {
                 when(movie) {
                     is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                     is Resource.Success -> {
                         binding.progressBar.visibility = View.GONE
                         movieAdapter.setData(movie.data)
                     }
                     is Resource.Error -> {
                         binding.progressBar.visibility = View.GONE
                         binding.viewError.root.visibility = View.VISIBLE
                         binding.viewError.tvError.text = movie.message ?: getString(R.string.something_wrong)
                     }
                 }
                }
            }
            with(binding.rvMovie) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}