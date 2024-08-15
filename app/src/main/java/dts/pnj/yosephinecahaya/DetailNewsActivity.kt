package dts.pnj.yosephinecahaya

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_news)
        val imageView = findViewById<ImageView>(R.id.detailImage)

        val intent = intent
        val title = intent.getStringExtra("EXTRA_TITLE")
        val content = intent.getStringExtra("EXTRA_CONTENT")
        val imagePath = intent.getStringExtra("EXTRA_IMAGE_PATH")

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Show back button
        supportActionBar?.setDisplayShowTitleEnabled(false) // Hide default title

        val titleTextView = findViewById<TextView>(R.id.detailTitle)
        val contentTextView = findViewById<TextView>(R.id.detailContent)

        titleTextView.text = title
        contentTextView.text = content

        val imageResource = when (imagePath) {
            "drawable/ai_medis" -> R.drawable.ai_medis
            "drawable/harimau" -> R.drawable.harimau
            "drawable/polusi" -> R.drawable.polusi
            "drawable/sayuran" -> R.drawable.sayuran
            else -> R.drawable.ic_berita
        }
        imageView.setImageResource(imageResource)
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            // Handle the back button click
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
