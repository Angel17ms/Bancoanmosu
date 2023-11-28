package com.example.banco_anmosu.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.banco_anmosu.R
import com.example.banco_anmosu.databinding.ActivityTransferBinding

class TransferActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cuentas = arrayOf("45KJ-564J-DF98-95ÑO","45KJ-34HB-DF98-12LS","568U-564J-HNV8-95ÑO","E45E-DCV8-DF98-95ÑO")

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,cuentas)

        val spinner = binding.cuentaOrigen

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        val spinner2 = binding.cuentaDestino1

        spinner2.adapter = adapter

        val divisas = arrayOf("$", "€", "¥", "£")

        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)

        val spinner3 = binding.divisa

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner3.adapter = adapter2


        var cuentaOrigen: String? = null
        var cuentaDestino: String? = null
        var divisa: String? = null

        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                cuentaOrigen = cuentas[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })


        spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                posicion: Int,
                p3: Long
            ) {
                cuentaDestino = cuentas[posicion]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        })

        spinner3.setOnItemSelectedListener(object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                divisa = divisas[posicion]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        })

        binding.grupoRadion.setOnCheckedChangeListener{_,checkedId ->
        when(checkedId){
            R.id.cuentaPropia -> {
                binding.cuentaDestino1.visibility = View.VISIBLE
                binding.cuentaDestino2.visibility = View.INVISIBLE
            }
            R.id.cuentaAjena ->{
                binding.cuentaDestino1.visibility = View.INVISIBLE
                binding.cuentaDestino2.visibility = View.VISIBLE
            }
        }}



        binding.enviar.setOnClickListener {
            var mensage = "Cuenta origen:\n"
            mensage += cuentaOrigen + "\n"

            if (binding.cuentaPropia.isChecked){
                mensage += "A Cuenta Propia: \n$cuentaDestino\n"
            }
            if (binding.cuentaAjena.isChecked){
                mensage += "A Cuenta Ajena: \n" + binding.cuentaDestino2.text.toString() + "\n"
            }

            mensage += "Importe: " + binding.cantidad.text.toString() + divisa + "\n"


            if (binding.Justificante.isChecked){
                mensage += "Enviar justificante"
            }else{
                mensage += "No enviar justificante"
            }

            Toast.makeText(applicationContext, mensage, Toast.LENGTH_SHORT).show();
        }

        binding.cancelar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}