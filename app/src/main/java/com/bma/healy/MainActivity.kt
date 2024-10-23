package com.bma.healy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.bma.healy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var botaoLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        botaoLogin = findViewById(R.id.botaoLogin)


        showFragment(ListaFragment())

        botaoLogin.setOnClickListener {
            showFragment(CadastroFragment())
        }
    }

}