package pe.edu.upc.jokes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JokeDao {

    @Query("select * from Joke")
    fun getAll() : List<Joke>

    @Insert
    fun insert(vararg jokes: Joke)

    @Delete
    fun delete(vararg jokes: Joke)
}