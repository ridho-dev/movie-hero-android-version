package com.dededev.moviehero.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dededev.moviehero.R
import com.dededev.moviehero.core.domain.model.Movie
import com.dededev.moviehero.databinding.MovieItemBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {
    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<Movie>?) {
        if (newData == null) return
        listData.clear()
        listData.addAll(newData)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = MovieItemBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                tvMovieItem.text = data.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}