package com.example.proyectofinalkvh.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviedetails.MovieDetails
import com.example.proyectofinalkvh.model.dataclass.movievideos.MovieVideos
import com.example.proyectofinalkvh.model.retrofit.IMAGE_BASE_URL
import com.example.proyectofinalkvh.viewmodel.MovieVM
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*

private const val ARG_PARAM1 = "param1"

class MovieDetailsFragment : Fragment() {
    private var param1: String? = null
    private lateinit var movieVM: MovieVM
    private lateinit var movieDetails: MovieDetails
    private lateinit var movieVideos: MovieVideos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
        movieVM = ViewModelProvider(activity!!).get(MovieVM::class.java)
        initObjects()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //MOVIE DETAILS
        movieVM.cacheDetailData(param1?.toIntOrNull()!!)
        //TODO detailsFragment no encesita observar datos, por lo tanto , hay que recibir un objeto sin live data
        movieVM.getMovieDetailsById(param1?.toIntOrNull()!!).observe(viewLifecycleOwner, {
            updateDetails(it)
            //TODO ESTOS TODAVIA NO SE COMO SACARLOS DEL OBSERVE, AFUERA NO FUNCIONAN

            Picasso.get().load(IMAGE_BASE_URL + movieDetails.backdrop_path).into(poster_detail)
            title_detail.text=setString(R.string.title, movieDetails.title)
            if (movieDetails.original_title != movieDetails.title) {
                original_title_detail.visibility = View.VISIBLE
                original_title_detail.text = setString(R.string.original_title, movieDetails.original_title)
            }

            //sacando solamente el genero desde la lista Genres
            val genresList = movieDetails.genres
            val onlyGenres = mutableListOf<String>()
            if (genresList != null) {
                for (genre in genresList) {
                    onlyGenres.add(genre?.name!!)
                }
                genre_detail.text = setString(R.string.genres, onlyGenres.toString())
            }

            release_date_detail.text = setString(R.string.release_date, movieDetails.release_date)

            if (movieDetails.tagline == "") {
                tagline_detail.visibility = View.GONE
            }
            tagline_detail.text = movieDetails.tagline
            vote_average_detail.text = setString(
                R.string.rating,
                movieDetails.vote_average.toString()
            )
            runtime_detail.text = setString(R.string.duration, movieDetails.runtime.toString())

            if (movieDetails.original_language != "en" && movieDetails.original_language!=null) {
                original_language_detail.visibility = View.VISIBLE
                original_language_detail.text = setString(
                    R.string.original_language,
                    movieDetails.original_language
                )
            }

            if (movieDetails.budget == 0) {
                budget_detail.visibility = View.GONE
            }
            budget_detail.text = setString(R.string.budget, movieDetails.budget.toString())
            overview_detail.text = setString(R.string.sinopsis, movieDetails.overview)
            if (movieDetails.revenue == 0) {
                revenue_detail.visibility = View.GONE
            }
            revenue_detail.text = setString(R.string.revenue, movieDetails.revenue.toString())

                movieVM.cacheVideoData(param1?.toIntOrNull()!!)
                movieVM.getMovieVideosById(param1?.toIntOrNull()!!).observe(viewLifecycleOwner, {
                    updateVideos(it)
                    Log.d("kevin", "viewmodel en fragment $it")
                    val youTubePlayerView: YouTubePlayerView = youtube_player_view
                    lifecycle.addObserver(youTubePlayerView)
                    youTubePlayerView.visibility = View.VISIBLE
                    val vidResults = movieVideos.results
                    val videos = hashMapOf<String, String>()
                    if (vidResults != null) {
                        for (result in vidResults) {
                            videos.put(result?.type!!, result.key!!)
                        }
                    }
                    if (videos.contains("Trailer")){
                        youTubePlayerView.addYouTubePlayerListener(object :
                            AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                youTubePlayer.loadVideo(videos["Trailer"]!!, 0f)
                            }
                        })
                }
                })


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
    private fun updateDetails(movie: MovieDetails?){
        if(movie!=null){
            movieDetails=movie
        }
    }
    private fun updateVideos(videos:MovieVideos?){
        if(videos!=null){
            movieVideos=videos
        }
    }
    private fun initObjects(){
        movieDetails = MovieDetails(
            null, null, null,
            null, null, null, null,
            null, null, null, null, null,
            null, null, null, null, null, null,
            null, null, null, null, null, null, null
        )
        movieVideos= MovieVideos(null,null)
    }
    fun setString(stringResource: Int, movieDetail: String?): String {
        return context?.resources?.getString(stringResource, movieDetail).toString()
    }
}