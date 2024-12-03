package com.dev.bernardoslailati.meuprimeiroappandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AppProcesses {
    fun starMusic() {
        Log.d("MainActivity2", "AppProcesses: starMusic")
    }
    fun stopMusic() {
        Log.d("MainActivity2", "AppProcesses: stopMusic")
    }
    fun deleteTemporaryCacheFiles() {
        Log.d("MainActivity2", "AppProcesses: deleteTemporaryCacheFiles")
    }
    fun createTemporaryCacheFiles() {
        Log.d("MainActivity2", "AppProcesses: createTemporaryCacheFiles")
    }
    fun startLongRunningTasks() {
        Log.d("MainActivity2", "AppProcesses: startLongRunningTasks")
    }
    fun stopLongRunningTasks() {
        Log.d("MainActivity2", "AppProcesses: stopLongRunningTasks")
    }

    fun setupLayout() {
        Log.d("MainActivity2", "AppProcesses: setupLayout")
    }
}

class MainActivity2 : AppCompatActivity() {

    private val myAppProcesses by lazy {
        AppProcesses()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity2", "onCreate")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myAppProcesses.setupLayout()
        myAppProcesses.createTemporaryCacheFiles()
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity2", "onStart")
        myAppProcesses.startLongRunningTasks()
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity2", "onResume")

        // ...

        myAppProcesses.starMusic()
        // myAppProcesses.startAnimations()
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity2", "onPause")

        myAppProcesses.stopMusic()
        // myAppProcesses.stopAnimations()
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity2", "onStop")

        myAppProcesses.stopLongRunningTasks()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity2", "onRestart")

        // myAppProcesses.restartLongRunningTasks()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity2", "onDestroy")

        myAppProcesses.deleteTemporaryCacheFiles()
    }
}