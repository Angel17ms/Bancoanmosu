package com.example.banco_anmosu.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.banco_anmosu.Fragments.AccountsMovementsFragment
import com.example.banco_anmosu.R
import com.example.banco_anmosu.databinding.ActivityGlobalPositionDetailsBinding
import com.example.banco_anmosu.pojo.Cuenta

class ActivityGlobalPositionDetails : AppCompatActivity() {
    private lateinit var binding: ActivityGlobalPositionDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cuenta = intent.getSerializableExtra("cuenta") as Cuenta

        val frgAccountMovements = AccountsMovementsFragment.newInstance(cuenta)

        supportFragmentManager.beginTransaction().add(R.id.frgAccountsMovements, frgAccountMovements).commit()

    }
}