package pe.edu.upc.jokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        btJoke.setOnClickListener {
            getJoke()
        }
    }

    private fun getJoke() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://geek-jokes.sameerkumar.website/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jokeApi = retrofit.create(JokeApi::class.java)

        val request = jokeApi.getJoke("json")

        request.enqueue(object : Callback<Joke> {
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {

                if (response.isSuccessful) {
                    val joke = response.body()
                    tvJoke.text = joke!!.sentence

                    AppDatabase.getInstance(this@MainActivity).getDao().insert(joke)

                }
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

        }
        )

    }
}