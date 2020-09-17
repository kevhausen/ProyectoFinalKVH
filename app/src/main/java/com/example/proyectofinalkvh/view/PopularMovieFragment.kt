package com.example.proyectofinalkvh.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.dataclass.moviepopular.Result
import com.example.proyectofinalkvh.viewmodel.MovieVM
import kotlinx.android.synthetic.main.fragment_popular_movie.*

class PopularMovieFragment : Fragment(),MovieAdapter.IAdapter {
    private lateinit var movieVM: MovieVM
    private lateinit var mAdapter:MovieAdapter
    var page=1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieVM=ViewModelProvider(activity!!).get(MovieVM::class.java)
        mAdapter= MovieAdapter(mutableListOf(),this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieVM.cachePopularData(page).also { Log.d("kevin","el primer cache $page") }


        nested_scrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (v != null) {
                if(scrollY== v.getChildAt(0)?.measuredHeight!! - v.measuredHeight){
                    if(page<500){
                        page++
                        Log.d("kevin","pagina numero $page")
                        popular_progressbar.visibility=View.VISIBLE
                        movieVM.cachePopularData(page)
                        popular_progressbar.visibility=View.GONE
                    }else{
                        popular_endofline.visibility=View.VISIBLE
                    }
                }
            }
        })
        movieVM.getPopularMovies().observe(viewLifecycleOwner,{
            mAdapter.updateData(it as MutableList<Result>)
        })
        //logica que suma pagina
        //cuando hago el llamado, se va a guardar
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