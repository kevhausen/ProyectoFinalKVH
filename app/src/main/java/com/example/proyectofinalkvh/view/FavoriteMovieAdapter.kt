package com.example.proyectofinalkvh.view

import android.graphics.Movie
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviefavorite.MovieFavorite
import com.example.proyectofinalkvh.model.retrofit.IMAGE_BASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.favorite_viewholder.view.*


class FavoriteMovieAdapter :RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteHolder>() {
    private var mDataset:List<MovieFavorite> = emptyList()

    fun setData(favList:List<MovieFavorite>){
        mDataset=favList
        notifyDataSetChanged()
    }


    inner class FavoriteHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(favorite:MovieFavorite){
            itemView.favorite_movie_title.text=favorite.title
            itemView.favorite_movie_release_date.text=favorite.release_date
            itemView.favorite_movie_rating.setString(R.string.favorite_rating,favorite.vote_average.toString())
            itemView.favorite_movie_popularity.setString(R.string.favorite_popularity,favorite.popularity.toString())
            itemView.favorite_movie_vote_count.setString(R.string.favorite_vote_count,favorite.vote_count.toString())
            Picasso.get().load(IMAGE_BASE_URL+favorite.poster_path).placeholder(R.drawable.ic_launcher_foreground).into(itemView.favorite_movie_poster)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        return FavoriteHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorite_viewholder,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(mDataset[position])
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun TextView.setString(id:Int, movieDetail: String?){
        val txt=context.getString(id,movieDetail)
        this.text= Html.fromHtml(txt, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}