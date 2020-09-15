package com.example.proyectofinalkvh.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.viewmodel.MovieVM
import kotlinx.android.synthetic.main.fragment_popular_movie.*

class PopularMovieFragment : Fragment(),MovieAdapter.IAdapter {
    private lateinit var movieVM: MovieVM
    private lateinit var mAdapter:MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieVM=ViewModelProvider(activity!!).get(MovieVM::class.java)
        mAdapter= MovieAdapter(MoviePopular(),this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieVM.getPopularMovies().observe(viewLifecycleOwner,{
            mAdapter.updateData(it)
        })
        popular_recycler.adapter=mAdapter
        popular_recycler.layoutManager= GridLayoutManager(activity,3)


    }
    companion object {
        @JvmStatic
        fun newInstance() = PopularMovieFragment()
    }

    override fun idFromMovie(id: Int) {
        activity?.supportFragmentManager!!.beginTransaction().addToBackStack("popular").replace(R.id.frameLayout,MovieDetailsFragment.newInstance(id)).commit()
    }


}