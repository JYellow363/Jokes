package pe.edu.upc.jokes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_joke.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_joke, container, false)
        view.findViewById<Button>(R.id.btJoke).setOnClickListener {
            getJoke(view)
            Log.d("TAG","Luego de getJoke")
        }
        return view
    }

    companion object {
        fun newInstance(): JokeFragment = JokeFragment()
        const val TAG = "MainActivity"
    }

    private fun getJoke(view: View) {

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
                    view.findViewById<TextView>(R.id.tvJoke)
                    tvJoke?.text = joke!!.sentence

                    AppDatabase.getInstance(view.context).getDao().insert(joke)
                }
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                Log.d(TAG, t.toString())
            }
        }
        )

    }
}