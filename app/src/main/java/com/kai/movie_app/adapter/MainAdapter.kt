package com.kai.movie_app.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kai.movie_app.R
import com.kai.movie_app.model.Constant
import com.kai.movie_app.model.Movie
import com.squareup.picasso.Picasso

class MainAdapter(var movies:ArrayList<Movie>, var listener:OnAdapterListener): RecyclerView.Adapter<MainAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
    )
    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movies[position])

        val posterPath = Constant.POSTER_PATH + movie.poster_path
        val poster:ImageView  = holder.view.findViewById(R.id.image_poster)
        Picasso.get()
            .load(posterPath)
            .placeholder(R.drawable.placeholder_portrait)
            .error(R.drawable.placeholder_portrait)
            .into(poster);

        poster.setOnClickListener{
            listener.onClick(movie)
        }

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view :View = view
        fun bind(movies : Movie){
            val title:TextView  = view.findViewById(R.id.text_title)
            title.text = movies.title
        }
    }

    public fun setData(newMovies : List<Movie>){
        movies.clear()
        movies.addAll(newMovies)
        //RELOAD
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(movie: Movie)

    }


}