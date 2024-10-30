package com.bma.healy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class Cadastro : Fragment() {


    private lateinit var linkCadastro: TextView
    private lateinit var botaoCadastro: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linkCadastro = view.findViewById(R.id.linkCadastro)
        botaoCadastro = view.findViewById(R.id.botaoCadastro)

        linkCadastro.setOnClickListener {
            (activity as MainActivity).showFragment(Login())
        }

        botaoCadastro.setOnClickListener {
            (activity as MainActivity).showFragment(Login())
        }
    }
}