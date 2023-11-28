package com.example.banco_anmosu.Activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.banco_anmosu.databinding.ActivityMainBinding
import com.example.banco_anmosu.pojo.Cliente

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dni = intent.getStringExtra("dni")
        val cliente = intent.getSerializableExtra("cliente") as? Cliente

        binding.dni.text = "$dni"

        binding.boton4.setOnClickListener {
            val intent = Intent(this, Change_Password::class.java)
            startActivity(intent)
        }
        binding.boton7.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
        binding.boton3.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }
        binding.boton1.setOnClickListener {
            val intent = Intent(this, GlobalPositionActivity::class.java)
            intent.putExtra("cliente", cliente)
            startActivity(intent)
        }
        binding.boton2.setOnClickListener {
            val intent = Intent(this, MovementsActivity::class.java)
            intent.putExtra("cliente", cliente)
            startActivity(intent)
        }

    }
}