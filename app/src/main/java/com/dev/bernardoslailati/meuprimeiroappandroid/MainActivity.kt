package com.dev.bernardoslailati.meuprimeiroappandroid

import android.content.Context
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

        val myClass = MyClass(context = this)

        showToast(context = this)
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

        supportFragmentManager.beginTransaction().add(
            R.id.flMainContainer,
            BlankFragment.newInstance(
                name = "Bernardo Slailati",
                age = 29,
                isMale = true
            )
        ).commit()
    }

    fun showToast(context: Context) {
        Toast.makeText(
            context,
            "Hello World!",
            Toast.LENGTH_SHORT
        ).show()
    }
}