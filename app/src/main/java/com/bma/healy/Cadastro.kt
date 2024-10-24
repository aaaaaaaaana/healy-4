package com.bma.healy

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class Cadastro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_cadastro)


        var linkCadastro: TextView  = findViewById(R.id.linkCadastro)

        var botaoCadastro: TextView = findViewById(R.id.botaoCadastro)


        // adicionar evento do clique
        linkCadastro.setOnClickListener{

            var intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        }

        botaoCadastro.setOnClickListener{

            var intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
}