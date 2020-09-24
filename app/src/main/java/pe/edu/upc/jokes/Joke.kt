package pe.edu.upc.jokes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Joke(

    @PrimaryKey(autoGenerate = true)
    var uid: Int?,

    @ColumnInfo(name = "sentence")
    @SerializedName("joke")
    var sentence: String
)