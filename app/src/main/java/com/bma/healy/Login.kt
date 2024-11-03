package com.bma.healy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class Login : Fragment() {

    private val autenticador by lazy {
        FirebaseAuth.getInstance()
    }

    private lateinit var linkLogin: TextView
    private lateinit var linkEsqueceuSenha: TextView
    private lateinit var botaoLogin: Button
    private lateinit var email: EditText
    private lateinit var senha: EditText

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
        email = view.findViewById(R.id.email)
        senha = view.findViewById(R.id.senha)

        linkLogin.setOnClickListener {
            (activity as MainActivity).showFragment(Cadastro())
        }

        linkEsqueceuSenha.setOnClickListener {
            (activity as MainActivity).showFragment(EsqueceuSenha())
        }

        botaoLogin.setOnClickListener {
            val email = email.text.toString()
            val senha = senha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                fazerLogin(email, senha)
            }
        }
    }

    private fun fazerLogin(email: String, senha: String) {
        autenticador.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val user = autenticador.currentUser
                    if (user != null) {

                        val intent = Intent(requireActivity(), Menu::class.java)
                        startActivity(intent)
                    } else {

                        Toast.makeText(context, "Erro ao fazer login", Toast.LENGTH_SHORT).show()
                    }
                } else {

                    val exception = task.exception
                    if (exception is FirebaseAuthInvalidCredentialsException) {

                        Toast.makeText(context, "Email ou senha inválidos", Toast.LENGTH_SHORT)
                            .show()
                    } else {

                        Toast.makeText(context, "Esta conta não existe", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}