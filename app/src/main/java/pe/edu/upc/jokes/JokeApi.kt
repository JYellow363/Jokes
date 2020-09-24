package pe.edu.upc.jokes

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApi {

    @GET("api")
    fun getJoke(@Query("format") format: String): Call<Joke>
}