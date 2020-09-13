package com.example.proyectofinalkvh.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.dataclass.moviepopular.Result
import com.example.proyectofinalkvh.model.retrofit.IMAGE_BASE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_viewholder.view.*


class MovieAdapter(var mDataset :MoviePopular,var context: Context): RecyclerView.Adapter<MovieAdapter.MovieHolder>(){



    fun updateData(movie:MoviePopular?){
        if (movie != null) {
            mDataset= movie
        }
        notifyDataSetChanged()
    }

    class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(result:Result?, contexta: Context){
            itemView.movie_title.text=result?.title
            itemView.movie_release.text=result?.release_date
            Picasso.get().load(IMAGE_BASE_URL+result?.poster_path).into(itemView.movie_poster)

            itemView.setOnClickListener {
                Toast.makeText(contexta,result?.id.toString(),Toast.LENGTH_LONG).show()
                (contexta as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout,MovieDetailsFragment.newInstance(result?.id.toString()))
                    .addToBackStack("popular_fragment")
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_viewholder,parent,false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(mDataset.results?.get(position),context)

    }

    override fun getItemCount(): Int {
        if(mDataset.results!=null){
            return mDataset.results!!.size
        }else{
            return 0
        }

    }
    interface IAdapterId{
        fun idFromMovie(id:Int)
    }

}