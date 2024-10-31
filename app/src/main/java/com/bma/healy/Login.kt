package com.bma.healy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment


class Login : Fragment() {
    private lateinit var linkLogin: TextView
    private lateinit var linkEsqueceuSenha: TextView
    private lateinit var botaoLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linkLogin = view.findViewById(R.id.linkLogin)
        linkEsqueceuSenha = view.findViewById(R.id.esqueceuLogin)
        botaoLogin = view.findViewById(R.id.botaoLogin)

        linkLogin.setOnClickListener {
            (activity as MainActivity).showFragment(Cadastro())
        }

        linkEsqueceuSenha.setOnClickListener {
            (activity as MainActivity).showFragment(EsqueceuSenha())
        }

        botaoLogin.setOnClickListener {
            startActivity(Intent(context, Menu::class.java))
        }
    }
}