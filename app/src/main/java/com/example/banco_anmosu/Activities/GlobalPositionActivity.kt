package com.example.banco_anmosu.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_anmosu.Adapters.CuentasAdapter
import com.example.banco_anmosu.Fragments.AccountListener
import com.example.banco_anmosu.Fragments.AccountsFragment
import com.example.banco_anmosu.Fragments.AccountsMovementsFragment
import com.example.banco_anmosu.R
import com.example.banco_anmosu.bd.MiBancoOperacional
import com.example.banco_anmosu.databinding.ActivityGlobalPositionBinding
import com.example.banco_anmosu.pojo.Cliente
import com.example.banco_anmosu.pojo.Cuenta
import java.util.ArrayList

class GlobalPositionActivity : AppCompatActivity(), AccountListener {
    private lateinit var binding: ActivityGlobalPositionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        val cliente = intent.getSerializableExtra("cliente") as? Cliente


        val frgAccount: AccountsFragment = AccountsFragment.newInstance(cliente as Cliente)

        supportFragmentManager.beginTransaction().add(R.id.frgAccount, frgAccount).commit()

        frgAccount.setCuentasListener(this)
    }

    override fun onCuentaSeleccionada(c: Cuenta) {
        if (c != null) {
            var hayDetalle = supportFragmentManager.findFragmentById(R.id.frgDetalle) !=
                    null
            if(hayDetalle){//Se muestra el contenido en la misma Activity
                val detailFragment = AccountsMovementsFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frgDetalle, detailFragment)
                transaction.commitNow()

            }else{
                val i = Intent(this, ActivityGlobalPositionDetails::class.java)
                i.putExtra("cuenta", c)
                startActivity(i)
            }
        }
    }

}