package dts.pnj.yosephinecahaya

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        WriteUserInfoToFile()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomToolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, HomeFragment())
                .commit()
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_news -> selectedFragment = BeritaFragment()
                R.id.nav_profile -> selectedFragment = ProfileFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, selectedFragment!!)
                .commit()

            true
        }
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.alumni_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_add_alumni -> {
                val intent = Intent(this, AddAlumniActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.nav_data_alumni -> {
                val intent = Intent(this, DataAlumniActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.nav_logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun WriteUserInfoToFile() {
        val fileName = "user_info.txt"
        val fileContent = """
            Yosephine Cahaya Permatahari
            yosephinecp@gmail.com
            113333555555
            Junior Mobile Programmer
        """.trimIndent()

        try {
            val file = File(filesDir, fileName)
            FileOutputStream(file).use { fos ->
                fos.write(fileContent.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}