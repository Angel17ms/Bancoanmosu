package com.example.banco_anmosu.Adapters

import com.example.banco_anmosu.Fragments.AccountListener
import com.example.banco_anmosu.pojo.Cuenta

interface OnClickListener {

    fun onClick(c: Cuenta)

    fun setCuentasListener(listener: AccountListener)
}