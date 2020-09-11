package com.example.proyectofinalkvh.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.viewmodel.MovieVM
import kotlinx.android.synthetic.main.fragment_popular_movie.*

class PopularMovieFragment : Fragment() {
    private lateinit var movieVM: MovieVM
    private lateinit var mContext: Context
    private lateinit var mAdapter:MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieVM=ViewModelProvider(activity!!).get(MovieVM::class.java)
        mAdapter= MovieAdapter(MoviePopular(0,null,0,0))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieVM.getPopularMovies().observe(this,{
            mAdapter.updateData(it)
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