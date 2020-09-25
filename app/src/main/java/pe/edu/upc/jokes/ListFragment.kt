package pe.edu.upc.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    lateinit var jokes: List<Joke>
    lateinit var jokeAdapter: JokeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        jokes = AppDatabase.getInstance(view.context).getDao().getAll()

        jokeAdapter = JokeAdapter(view.context, jokes)

        view.findViewById<RecyclerView>(R.id.rvJoke).adapter = jokeAdapter
        view.findViewById<RecyclerView>(R.id.rvJoke).layoutManager = LinearLayoutManager(view.context)
        return view
    }

    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }
}