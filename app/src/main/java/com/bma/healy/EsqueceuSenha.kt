package com.bma.healy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class EsqueceuSenha : Fragment() {

    private val autenticador by lazy {
        FirebaseAuth.getInstance()
    }

    private lateinit var linkEsqueceuSenha: TextView
    private lateinit var botaoRedefinir: Button
    private lateinit var email: EditText


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
        email = view.findViewById(R.id.emailEsqueceu)
        linkEsqueceuSenha.setOnClickListener {
            (activity as MainActivity).showFragment(Login())
        }

        botaoRedefinir.setOnClickListener {
            val email = email.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(context, "Digite seu email", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(context, "Email inválido", Toast.LENGTH_SHORT).show()
            } else {

                autenticador.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Email de redefinição de senha enviado!",
                                Toast.LENGTH_SHORT
                            ).show()

                            (activity as MainActivity).showFragment(Login())
                        } else {

                            Toast.makeText(
                                context,
                                "Erro ao enviar email de redefinição de senha.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}