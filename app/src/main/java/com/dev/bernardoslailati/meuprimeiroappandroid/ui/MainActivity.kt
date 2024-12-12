package com.dev.bernardoslailati.meuprimeiroappandroid.ui

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dev.bernardoslailati.meuprimeiroappandroid.R
import com.dev.bernardoslailati.meuprimeiroappandroid.broadcastreceiver.LowBatteryBroadcastReceiver
import com.dev.bernardoslailati.meuprimeiroappandroid.databinding.ActivityMainBinding
import com.dev.bernardoslailati.meuprimeiroappandroid.service.SyncDataService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val lowBatteryBroadcastReceiver = LowBatteryBroadcastReceiver()
    private val lowBatteryIntentFilter = IntentFilter(Intent.ACTION_BATTERY_LOW)

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permissão garantida!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permissão não garantida!", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate")
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

        binding.button?.setOnClickListener {
            showGoToRockerseatWebsite()
            //startActivity(Intent(this, MainActivity2::class.java))
        }

        supportFragmentManager.beginTransaction().add(
            R.id.flMainContainer,
            BlankFragment.newInstance(
                name = "Bernardo Slailati",
                age = 29,
                isMale = true
            )
        ).commit()

        registerReceiver(lowBatteryBroadcastReceiver, lowBatteryIntentFilter)

        val intent = Intent(this, SyncDataService::class.java)
        startService(intent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun showGoToRockerseatWebsite() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
            && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        ) {
            Toast.makeText(this, "Permissão de notificações não garantida!", Toast.LENGTH_SHORT)
                .show()
            return
        }


        val intentGoToRockerseatWebsite =
            //Intent(this, MainActivity2::class.java)
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.rockerseat.com.br"))

        val pendingIntentGoToRockerseatWebsite = PendingIntent.getActivity(
            this,
            0,
            intentGoToRockerseatWebsite,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Cria a notificação
        val notification = NotificationCompat.Builder(this, "channel_id")
            .setContentTitle("Notificação")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentText("Toque aqui para abrir a MainActivity")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setContentIntent(pendingIntentGoToRockerseatWebsite)

        // Exibe a notificação
        val manager = getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "Go To Activity 2 Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        manager.notify(1, notification.build())
    }

    private fun showToast(context: Context) {
        Toast.makeText(
            context,
            "Hello World!",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(lowBatteryBroadcastReceiver)
        Log.d("MainActivity", "onDestroy")
    }
}