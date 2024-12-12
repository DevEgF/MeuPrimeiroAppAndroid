package com.dev.bernardoslailati.meuprimeiroappandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dev.bernardoslailati.meuprimeiroappandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val url = "https://www.rocketseat.com.br"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding.tvMyFirstAndroidApp) {
            this?.text = getString(R.string.my_first_android_app)
            this?.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        }

        val color = this.getColor(R.color.white)

        supportFragmentManager.beginTransaction().add(
            R.id.flMainContainer,
            BlankFragment.newInstance(
                name = "Bernardo Slailati",
                age = 29,
                isMale = true
            )
        ).commit()
    }
}