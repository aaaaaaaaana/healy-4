package com.bma.healy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {


    private lateinit var btnResultado: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var btnFormulario: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_menu)

        btnResultado = findViewById(R.id.btnResultado)
        btnPerfil = findViewById(R.id.btnPerfil)
        btnFormulario = findViewById(R.id.btnFormulario)



        showFragment(Home())

        btnResultado.setOnClickListener {
            showFragment(Home())
        }

        btnPerfil.setOnClickListener {
            showFragment(Perfil())
        }

        btnFormulario.setOnClickListener {
            showFragment(Formularios())
        }
    }


    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

}