package com.example.proyectofinalkvh.view


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


class MovieAdapter(var mDataset :MutableList<Result>,var iAdapter: IAdapter): RecyclerView.Adapter<MovieAdapter.MovieHolder>(){


    fun updateData(movie:MutableList<Result>?){
        if (movie != null) {
            mDataset= movie
        }
        notifyDataSetChanged()
    }

    //INNER CLASS PARA QUE RECONOZCA LOS CONTRSUCTORES DE LA CLASE MAMA
    inner class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(result:Result?){
            itemView.movie_title.text=result?.title
            itemView.movie_release.text=result?.release_date
            Picasso.get().load(IMAGE_BASE_URL+result?.poster_path).placeholder(R.drawable.ic_launcher_foreground).into(itemView.movie_poster)
            itemView.setOnClickListener {
                iAdapter.idFromMovie(result?.id!!)
            }
            itemView.setOnLongClickListener {
                iAdapter.idFromLonglick(result?.id!!)//se le envia el id al fragment para que este se la pase al viewmodel y el viewmodel guarde ese id como favorito
                Toast.makeText(itemView.context,"${result.title?.take(10)} added as favorite",Toast.LENGTH_SHORT).show()
                itemView.movie_star_favorite.visibility=View.VISIBLE
                //enviar instruccion a db
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_viewholder,parent,false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        holder.bind(mDataset[position])

    }

    //resultado del metodo por inferencia
    override fun getItemCount()= mDataset.size

    interface IAdapter{
        fun idFromMovie(id:Int)
        fun idFromLonglick(id:Int)
    }

}