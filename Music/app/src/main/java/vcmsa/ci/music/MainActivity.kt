package vcmsa.ci.music

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//ST10498309-SHANIA KUNI
class MainActivity : AppCompatActivity() {
    private val Song = Array(5) { "" }
    private val Creator = Array(5) { "" }
    private val Rating = IntArray(5)
    private val Comment = Array(5) { "" }
    private val IsCreator = BooleanArray(5) { true }
    private var currentSong = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtSong = findViewById<EditText>(R.id.edtSong)
        val edtArtist = findViewById<EditText>(R.id.edtArtist)
        val edtRate = findViewById<EditText>(R.id.edtRate)
        val edtComments = findViewById<EditText>(R.id.edtComment)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnAdd.setOnClickListener {
            val song = edtSong.text.toString()
            val artist = edtArtist.text.toString()
            val rate = edtRate.text.toString().toIntOrNull() ?: 0
            val comment = edtComments.text.toString()

            if (currentSong < 5) {
                Song[currentSong] = song
                Creator[currentSong] = artist
                Rating[currentSong] = rate
                Comment[currentSong] = comment
                IsCreator[currentSong] = true

                currentSong++
                Toast.makeText(this, "Added to playlist", Toast.LENGTH_SHORT).show()


                edtSong.text.clear()
                edtArtist.text.clear()
                edtRate.text.clear()
                edtComments.text.clear()
            } else {
                Toast.makeText(this, "Playlist is full", Toast.LENGTH_SHORT).show()
            }
        }

        btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)

            intent.putExtra("Song", Song)
            intent.putExtra("creator", IsCreator)
            intent.putExtra("rating", Rating.map { it.toString() }.toTypedArray())
            intent.putExtra("Comments", Comment)

            startActivity(intent)
        }

        btnExit.setOnClickListener {
            finishAffinity()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
