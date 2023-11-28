package com.example.banco_anmosu.Activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_anmosu.Adapters.MovimentsAdapter
import com.example.banco_anmosu.bd.MiBancoOperacional
import com.example.banco_anmosu.databinding.ActivityMovementsBinding
import com.example.banco_anmosu.pojo.Cliente
import com.example.banco_anmosu.pojo.Cuenta
import com.example.banco_anmosu.pojo.Movimiento
import kotlin.collections.ArrayList

class MovementsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovementsBinding

    private lateinit var movimentsAdapter: MovimentsAdapter
    private lateinit var linearLayoutManager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        val cliente = intent.getSerializableExtra("cliente") as? Cliente


        val cuentas: ArrayList<Cuenta>? = mbo?.getCuentas(cliente) as ArrayList<Cuenta>?

        val cuentas2 : Array<String> = cuentas?.mapNotNull { it.getNumeroCuenta() }!!.toTypedArray()

        var adapter2 = ArrayAdapter(this,android.R.layout.simple_spinner_item, cuentas2)

        val spiner = binding.cuenta

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiner.adapter = adapter2

        var cuenta : Cuenta? = null
        linearLayoutManager = LinearLayoutManager(this)

        spiner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                cuenta = cuentas[position]

                val movimientos = mbo?.getMovimientos(cuenta)
                movimentsAdapter = MovimentsAdapter(movimientos as ArrayList<Movimiento>?)


                binding.RecyclerCuentas.apply {
                    layoutManager = linearLayoutManager
                    adapter = movimentsAdapter
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })



    }
}