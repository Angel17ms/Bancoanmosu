package com.example.banco_anmosu.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.banco_anmosu.bd.MiBancoOperacional
import com.example.banco_anmosu.databinding.ActivityLoginBinding
import com.example.banco_anmosu.pojo.Cliente

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.txtpass.onFocusChangeListener = View.OnFocusChangeListener { view, hasfocus ->
            if (!hasfocus) {
                if (binding.txtpass.text.toString().isEmpty()) {
                    binding.Password.error = "Tienes que rellenar la contraseña"
                }
            }
        }

        binding.txtUsuario.onFocusChangeListener = View.OnFocusChangeListener { view, hasfocus ->
            if (!hasfocus) {
                if (binding.txtUsuario.text.toString().isEmpty()) {
                    binding.usuario.error = "Tienes que rellenar el usuario"
                }
            }
        }


        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)


        println(mbo)
        binding.enviar.setOnClickListener {
            val dni = binding.usuario.editText?.text.toString()

            if (binding.txtUsuario.text.toString().isNotEmpty() && binding.txtpass.text.toString().isNotEmpty()){

                var a = Cliente()
                a.setNif(binding.txtUsuario.text.toString())
                a.setClaveSeguridad(binding.txtpass.text.toString())


                val resultado = mbo?.login(a) ?: -1
                println(resultado)

                if(resultado == -1) {
                    Toast.makeText(applicationContext,"El usuario o la contraseña son erroneos",Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("dni", dni)
                    intent.putExtra("cliente", resultado)
                    startActivity(intent)
                }


            }
        }

        binding.salir.setOnClickListener {
            finish()
        }




    }
}