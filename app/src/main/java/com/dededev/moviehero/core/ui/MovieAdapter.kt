package com.dededev.moviehero.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dededev.moviehero.R
import com.dededev.moviehero.core.data.source.remote.response.ResultsItem
import com.dededev.moviehero.databinding.MovieItemBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {
    private var listData = ArrayList<ResultsItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<ResultsItem>?) {
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

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = MovieItemBinding.bind(itemView)
        fun bind(data: ResultsItem) {
            with(binding) {
                tvMovieItem.text = data.title
            }
        }
    }

}