package com.ferhat.movieapp.services

import com.ferhat.movieapp.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("/movie/popular?api_key=5423485a60d15f231a07dc6267529f07")
    fun getMovieList(): Call<MovieResponse>
}