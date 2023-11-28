package com.example.banco_anmosu.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_anmosu.Adapters.MovimentsAdapter
import com.example.banco_anmosu.R
import com.example.banco_anmosu.bd.MiBancoOperacional
import com.example.banco_anmosu.databinding.FragmentAccountsMovementsBinding
import com.example.banco_anmosu.pojo.Cliente
import com.example.banco_anmosu.pojo.Cuenta
import com.example.banco_anmosu.pojo.Movimiento

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CUENTA = "cuenta"



class AccountsMovementsFragment : Fragment() {

    private lateinit var cuenta: Cuenta

    private lateinit var movimentsAdapter: MovimentsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: FragmentAccountsMovementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cuenta = it.getSerializable(ARG_CUENTA) as Cuenta
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsMovementsBinding.inflate(inflater,container,false)

        val mbo : MiBancoOperacional? = MiBancoOperacional.getInstance(context)

        if (mbo != null){
            var movimientos = mbo.getMovimientos(cuenta)
            movimentsAdapter = MovimentsAdapter(movimientos as ArrayList<Movimiento>)
            linearLayoutManager = LinearLayoutManager(context)

            binding.RecyclerCuentas.apply {
                layoutManager = linearLayoutManager
                adapter = movimentsAdapter
            }
        }

        return binding.root
    }

    companion object {


        @JvmStatic
        fun newInstance(cuenta: Cuenta) =
            AccountsMovementsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CUENTA, cuenta)
                }
            }
    }
}