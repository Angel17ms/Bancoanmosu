package com.example.banco_anmosu.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_anmosu.Fragments.AccountListener
import com.example.banco_anmosu.R
import com.example.banco_anmosu.databinding.ActivityItemCuentasBinding
import com.example.banco_anmosu.pojo.Cuenta
import java.util.ArrayList


class CuentasAdapter(private val cuentas: ArrayList<Cuenta>?,private val listener: OnClickListener): RecyclerView.Adapter<CuentasAdapter.ViewHolder>(){

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val binding = ActivityItemCuentasBinding.bind(view)

        fun setListener(c:Cuenta){
            binding.root.setOnClickListener {
                listener.onClick(c)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.activity_item_cuentas,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cuentas?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cuenta = cuentas!!.get(position)
        with(holder){
            setListener(cuenta)
            binding.numCuenta.text = cuenta.getNumeroCuenta()
            binding.SaldoCuenta.text = cuenta.getSaldoActual().toString()
        }
    }
}