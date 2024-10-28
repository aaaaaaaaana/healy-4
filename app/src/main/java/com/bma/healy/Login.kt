package com.bma.healy

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {

    private lateinit var linkLogin: TextView
    private lateinit var botaoLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        botaoLogin = findViewById(R.id.botaoLogin)
        linkLogin = findViewById(R.id.linkLogin)


        showFragment(MainActivity())

        botaoLogin.setOnClickListener {
            showFragment(Menu())
        }

        linkLogin.setOnClickListener {
            showFragment(Cadastr())
        }
    }


    fun showFragment(fragment: Menu) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    fun atualizarLista() {

        val listaFragment = supportFragmentManager.findFragmentByTag("ListaFragment") as? ListaFragment
        listaFragment?.atualizarLista()
    }
}