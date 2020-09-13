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
            null,null,null,null,null,null,null

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

            //TODO ESTOS TODAVIA NO SE COMO SACARLOS DEL OBSERVE, AFUERA NO FUNCIONAN
            fun setString(stringResource:Int,movieDetail:String?):String{
                return context?.resources?.getString(stringResource, movieDetail).toString()
            }

            Picasso.get().load(IMAGE_BASE_URL+movieDetails.backdrop_path).into(poster_detail)

            title_detail.text = setString(R.string.title,movieDetails.title)

            if(movieDetails.original_title!=movieDetails.title){
                original_title_detail.visibility=View.VISIBLE
                original_title_detail.text=setString(R.string.original_title,movieDetails.original_title)
            }
            val genresList=movieDetails.genres
            val onlyGenres= mutableListOf<String>()
            if (genresList != null) {
                for(genre in genresList){
                    onlyGenres.add(genre?.name!!)
                }
                genre_detail.text=setString(R.string.genres,onlyGenres.toString())
            }
            release_date_detail.text=setString(R.string.release_date,movieDetails.release_date)
            if(movieDetails.tagline==""){
                tagline_detail.visibility=View.GONE
            }
            tagline_detail.text=movieDetails.tagline
            vote_average_detail.text=setString(R.string.rating,movieDetails.vote_average.toString())
            runtime_detail.text=setString(R.string.duration,movieDetails.runtime.toString())

            //ESTO ES PARA LA LISTA DE LENGUAJES HABLADOS DURANTE LA PELICULA
            /*val spokenLangList=movieDetails.spoken_languages
            val onlySpokenLanguage= mutableListOf<String>()
            if(spokenLangList!=null){
                for(lang in spokenLangList){
                    onlySpokenLanguage.add(lang?.iso_639_1!!)
                }
                spoken_language_detail.text=onlySpokenLanguage.toString()
            }*/

            if(movieDetails.original_language!="en"){
                original_language_detail.visibility=View.VISIBLE
                original_language_detail.text=setString(R.string.original_language,movieDetails.original_language)
            }
            if(movieDetails.budget==0){
                budget_detail.visibility=View.GONE
            }
            budget_detail.text=setString(R.string.budget,movieDetails.budget.toString())
            overview_detail.text=setString(R.string.sinopsis,movieDetails.overview)
            if(movieDetails.revenue==0){
                revenue_detail.visibility=View.GONE
            }
            revenue_detail.text=setString(R.string.revenue,movieDetails.revenue.toString())
            //video_details.text=movieDetails.video.toString()









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