package vcmsa.ci.music

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    private var rate = IntArray(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val txtDetails = findViewById<TextView>(R.id.txtDetails)
        val txtAverage = findViewById<TextView>(R.id.txtAverage)
        val btnAverage = findViewById<Button>(R.id.btnAverage)
        val btnDisplay = findViewById<Button>(R.id.btnDisplay)
        val btnBack = findViewById<Button>(R.id.BtnBack)

        val songs = intent.getStringArrayExtra("Song")
        val creators = intent.getBooleanArrayExtra("creator")
        val ratings = intent.getStringArrayExtra("rating")
        val comments = intent.getStringArrayExtra("Comments")

        btnDisplay.setOnClickListener {
            if (songs != null && creators != null && ratings != null && comments != null) {
                val builder = StringBuilder()
                for (i in songs.indices) {
                    builder.append("Song: ${songs[i]}\n")
                    builder.append("Creator: ${if (creators[i]) "Yes" else "No"}\n")
                    builder.append("Rating: ${ratings[i]}\n")
                    builder.append("Comments: ${comments[i]}\n\n")


                    if (i < 5) {
                        rate[i] = ratings[i].toIntOrNull() ?: 0
                    }
                }
                txtDetails.text = builder.toString()
            } else {
                txtDetails.text = "No data received."
            }
        }

        btnBack.setOnClickListener {
            finish()
        }

        btnAverage.setOnClickListener {
            var total = 0.0
            for (i in 0 until rate.size) {
                total += rate[i]
            }
            val totalAverage = total / rate.size
            txtAverage.text = "Average: ${"%.1f".format(totalAverage)}"
        }
    }
}
