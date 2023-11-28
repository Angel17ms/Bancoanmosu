package com.example.banco_anmosu.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_anmosu.R
import com.example.banco_anmosu.databinding.ActivityItemMovimentsBinding
import com.example.banco_anmosu.pojo.Movimiento

class MovimentsAdapter(private val movimientos: ArrayList<Movimiento>?): RecyclerView.Adapter<MovimentsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding = ActivityItemMovimentsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.activity_item_moviments, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movimientos!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movimiento = movimientos!!.get(position)
        with(holder){
            binding.info.text = movimiento.getDescripcion()
            binding.fecha.text = movimiento.getFechaOperacion().toString()
            binding.importe.text = movimiento.getImporte().toString()
        }
    }

}