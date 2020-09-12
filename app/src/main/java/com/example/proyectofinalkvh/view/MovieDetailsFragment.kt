package com.example.proyectofinalkvh.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviedetails.MovieDetails
import com.example.proyectofinalkvh.model.retrofit.IMAGE_BASE_URL
import com.example.proyectofinalkvh.viewmodel.MovieVM
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MovieDetailsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var movieVM: MovieVM
    private lateinit var movieDetails: MovieDetails


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            Log.d("kevin", "en arguments $param1")
        }
        movieVM = ViewModelProvider(activity!!).get(MovieVM::class.java)
        movieDetails = MovieDetails(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,null

        )


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieVM.cacheDetailData(param1?.toIntOrNull()!!)
        movieVM.getMovieDetailsById(param1?.toIntOrNull()!!).observe(viewLifecycleOwner, {
            updateDetails(it)
            Log.d("kevin", "moviedetailDB $it")
            title_detail.text = movieDetails.original_title
            Picasso.get().load(IMAGE_BASE_URL+movieDetails.backdrop_path).into(poster_detail)
        })


    }

    companion object {
        @JvmStatic
        fun newInstance(id: String?) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, id)

                }
            }
    }
    fun updateDetails(movie:MovieDetails?){
        if(movie!=null){
            movieDetails=movie
        }
    }
}