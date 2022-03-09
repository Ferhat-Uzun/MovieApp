package com.ferhat.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferhat.movieapp.models.Movie
import com.ferhat.movieapp.models.MovieResponse
import com.ferhat.movieapp.services.MovieApiInterface
import com.ferhat.movieapp.services.MovieApiService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movies_list.layoutManager= LinearLayoutManager(this)
        getMovieData {
            rv_movies_list.adapter = Adapter(it)
        }
    }

    private fun getMovieData(callback : (List<Movie>)-> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)

        apiService.getMovieList().enqueue(object: Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                call.cancel()
                Snackbar.make(Error_textView,"Connection Problem",Snackbar.LENGTH_INDEFINITE).show()
            }
        })
    }
}