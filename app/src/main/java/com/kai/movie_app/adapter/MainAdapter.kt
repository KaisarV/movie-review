package com.kai.movie_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kai.movie_app.R
import com.kai.movie_app.model.Movie

class MainAdapter(var movies:ArrayList<Movie>): RecyclerView.Adapter<MainAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
    )
    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view2 :View = view
        fun bind(movies : Movie){
            val a:TextView  = view2.findViewById(R.id.text_title)
            a.text = movies.title
        }
    }

    public fun setData(newMovies : List<Movie>){
        movies.clear()
        movies.addAll(newMovies)
        //RELOAD
        notifyDataSetChanged()
    }


}