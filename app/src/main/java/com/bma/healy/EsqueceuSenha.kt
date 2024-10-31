package com.bma.healy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class EsqueceuSenha : Fragment() {

    private lateinit var linkEsqueceuSenha: TextView
    private lateinit var botaoRedefinir: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_esqueceu_senha, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linkEsqueceuSenha = view.findViewById(R.id.linkEsqueceu)
        botaoRedefinir = view.findViewById(R.id.botaoRedefinir)

        linkEsqueceuSenha.setOnClickListener {
            (activity as MainActivity).showFragment(Login())
        }

        botaoRedefinir.setOnClickListener {
            (activity as MainActivity).showFragment(Login())
        }
    }
}