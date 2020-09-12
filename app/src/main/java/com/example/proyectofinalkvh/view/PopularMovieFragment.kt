package com.example.proyectofinalkvh.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.viewmodel.MovieVM
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_popular_movie.*

class PopularMovieFragment : Fragment(),MovieAdapter.IAdapterId {
    private lateinit var movieVM: MovieVM
    private lateinit var mContext: Context
    private lateinit var mAdapter:MovieAdapter
    private var idObtained=0

    override fun idFromMovie(id: Int){
        Log.d("kevin fragment",id.toString())
        //aqui llega el activity nulo, por eso se cae
        //(activity as MainActivity).changeFrag(MovieDetailsFragment.newInstance(id.toString(),""))
        //activity!!.supportFragmentManager.beginTransaction().replace(R.id.frameLayout,MovieDetailsFragment.newInstance(idObtained.toString(),"")).commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieVM=ViewModelProvider(activity!!).get(MovieVM::class.java)
        mAdapter= MovieAdapter(MoviePopular(0,null,0,0),mContext)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieVM.getPopularMovies().observe(viewLifecycleOwner,{
            //aca dice que "it must not be null", lo decia igual antes cuando no tenia los typeconverter
            //aca tambien la primera vez que se inicia la aplicacion, se cae "it must not be null", pero cuando comento la linea 51,luego inicio, luego descomento y vuelvo a iniciar, la cuestion funciona
            //TAMBIEN LO ARRREGLE, habia que hacer un "null check" en el metodo updateData del adapter
            mAdapter.updateData(it)
            Log.d("kevin","en PopularMovieFragment $it")
        })
        popular_recycler.adapter=mAdapter
        popular_recycler.layoutManager= GridLayoutManager(activity,3)


    }
    companion object {
        @JvmStatic
        fun newInstance(context: Context) =
            PopularMovieFragment().apply {
                mContext=context
            }
    }




}