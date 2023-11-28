package com.example.banco_anmosu.Fragments

import com.example.banco_anmosu.pojo.Cuenta

interface AccountListener {

    fun onCuentaSeleccionada(c: Cuenta)
}