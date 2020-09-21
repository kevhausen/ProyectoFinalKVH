package com.example.proyectofinalkvh.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviefavorite.MovieFavorite
import com.example.proyectofinalkvh.viewmodel.MovieVM
import com.tsuryo.swipeablerv.SwipeLeftRightCallback
import com.tsuryo.swipeablerv.SwipeableRecyclerView
import kotlinx.android.synthetic.main.fragment_favorite_movie.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavoriteMovieFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var movieVM: MovieVM
    private lateinit var favAdapter: FavoriteMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        movieVM = ViewModelProvider(activity!!).get(MovieVM::class.java)
        favAdapter=FavoriteMovieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieVM.getAllFavoriteMoviesFromDB()
        movieVM.getAllFavoriteMoviesFromDB().observe(viewLifecycleOwner, {
            favAdapter.setData(it as MutableList<MovieFavorite>)

        })
        movie_favorites_recycler.adapter=favAdapter
        movie_favorites_recycler.layoutManager=LinearLayoutManager(activity)

        //swipe left or right to delete favorite
        movie_favorites_recycler.setListener(object : SwipeLeftRightCallback.Listener {
            override fun onSwipedLeft(position: Int) {
                movieVM.deleteFavMovie(favAdapter.getFavMovie(position).id!!)
            }

            override fun onSwipedRight(position: Int) {
                movieVM.deleteFavMovie(favAdapter.getFavMovie(position).id!!)
            }
        })


    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteMovieFragment()
    }
}