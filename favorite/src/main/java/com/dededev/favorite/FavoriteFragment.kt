package com.dededev.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dededev.core.ui.MovieAdapter
import com.dededev.favorite.di.favoriteModule
import com.dededev.moviehero.databinding.FragmentFavoriteBinding
import com.dededev.moviehero.detail.DetailActivity
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val favoriteViewModel: FavoriteViewModel by inject()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        loadKoinModules(favoriteModule)
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

            favoriteViewModel.getFavoriteMovies().observe(viewLifecycleOwner) { movie ->
                movieAdapter.setData(movie)
                binding.viewEmpty.root.visibility = if (movie.isNotEmpty()) View.GONE else View.VISIBLE
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