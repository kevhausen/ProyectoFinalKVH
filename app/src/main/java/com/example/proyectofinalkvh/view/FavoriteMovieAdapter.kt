package com.example.proyectofinalkvh.view

import android.graphics.Bitmap
import android.graphics.Movie
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviefavorite.MovieFavorite
import com.example.proyectofinalkvh.model.retrofit.IMAGE_BASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.favorite_viewholder.view.*
import java.lang.Exception
import kotlin.math.roundToInt


class FavoriteMovieAdapter :RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteHolder>() {
    private var mDataset:MutableList<MovieFavorite> = mutableListOf()

    fun setData(favList:MutableList<MovieFavorite>){
        mDataset=favList
        notifyDataSetChanged()
    }
    fun deleteData(position: Int){
        mDataset.removeAt(position)
        notifyDataSetChanged()
    }
    fun getFavMovie(position: Int):MovieFavorite{
        return mDataset[position]
    }



    inner class FavoriteHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(favorite:MovieFavorite){
            itemView.favorite_movie_title.text=favorite.title
            itemView.favorite_movie_release_date.text=favorite.release_date
            itemView.favorite_movie_rating.setString(R.string.favorite_rating,favorite.vote_average.toString())
            itemView.favorite_movie_popularity.setString(R.string.favorite_popularity, favorite.popularity?.roundToInt().toString())
            itemView.favorite_movie_vote_count.setString(R.string.favorite_vote_count,favorite.vote_count.toString())


            val mColor= ContextCompat.getColor(itemView.context, R.color.colorWhite)
            Picasso.get().load(IMAGE_BASE_URL+favorite.backdrop_path).resize(600,200).centerCrop()
                .into(object : com.squareup.picasso.Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    val backgroundImg = BitmapDrawable(itemView.context.resources, bitmap)
                    backgroundImg.colorFilter = PorterDuffColorFilter(mColor, PorterDuff.Mode.ADD)
                    itemView.favorite_movie_layout_background.background = backgroundImg
                    itemView.favorite_movie_background_progress_bar.visibility=View.GONE
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    itemView.favorite_movie_background_progress_bar.visibility=View.GONE
                    itemView.favorite_movie_failed_text.text=e.toString()
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    itemView.favorite_movie_background_progress_bar.visibility=View.VISIBLE
                    itemView.favorite_movie_layout_background.background=placeHolderDrawable
                }
            })
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