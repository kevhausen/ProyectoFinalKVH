package com.example.proyectofinalkvh.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.retrofit.IMAGE_BASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_viewholder.view.*


class MovieAdapter(var mDataset :MoviePopular): RecyclerView.Adapter<MovieAdapter.MovieHolder>(){

    fun updateData(movie:MoviePopular){
        mDataset=movie
        notifyDataSetChanged()
    }

    class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var titleMovie= itemView.movie_title
        var releaseDateMovie=itemView.movie_release
        var posterMovie=itemView.movie_poster

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_viewholder,parent,false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie= mDataset.results?.get(position)
        holder.titleMovie.text= movie?.title
        holder.releaseDateMovie.text=movie?.release_date
        Picasso.get().load(IMAGE_BASE_URL+movie?.poster_path).into(holder.posterMovie)

    }

    override fun getItemCount(): Int {
        if(mDataset.results!=null){
            return mDataset.results!!.size
        }else{
            return 0
        }

    }
}