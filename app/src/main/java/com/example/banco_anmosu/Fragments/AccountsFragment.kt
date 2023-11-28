package com.example.banco_anmosu.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_anmosu.Adapters.CuentasAdapter
import com.example.banco_anmosu.Adapters.OnClickListener
import com.example.banco_anmosu.R
import com.example.banco_anmosu.bd.MiBancoOperacional
import com.example.banco_anmosu.databinding.FragmentAccountsBinding
import com.example.banco_anmosu.pojo.Cliente
import com.example.banco_anmosu.pojo.Cuenta

private const val ARG_CLIENTE = "cliente"



@Suppress("DEPRECATION")
class AccountsFragment : Fragment(), OnClickListener{
    private lateinit var cliente: Cliente
    private lateinit var listener: AccountListener
    private lateinit var binding:FragmentAccountsBinding
    private lateinit var cuentaAdapter:CuentasAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    companion object {
        @JvmStatic
        fun newInstance(c: Cliente) =
            AccountsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("cliente", c)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable("cliente") as Cliente
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)


        val mbo : MiBancoOperacional? = MiBancoOperacional.getInstance(context)

        val cuentas: ArrayList<Cuenta>? = mbo?.getCuentas(cliente as Cliente?) as ArrayList<Cuenta>?

        cuentaAdapter = CuentasAdapter(cuentas, this)
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context,DividerItemDecoration.VERTICAL)

        binding.RecyclerCuentas.apply {
            layoutManager = linearLayoutManager
            adapter = cuentaAdapter
            addItemDecoration(itemDecoration)
        }

        return binding.root
    }



    override fun setCuentasListener(listener: AccountListener) {
        this.listener = listener
    }

    override fun onClick(c: Cuenta) {
        if (listener != null)
            listener.onCuentaSeleccionada(c)
    }


}