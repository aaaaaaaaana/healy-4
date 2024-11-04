package com.bma.healy

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
import com.google.firebase.auth.UserProfileChangeRequest

class Cadastro : Fragment() {

    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    private lateinit var linkCadastro: TextView
    private lateinit var botaoCadastro: Button
    private lateinit var email: EditText
    private lateinit var nome: EditText
    private lateinit var cpf: EditText
    private lateinit var senha: EditText
    private lateinit var confirmaSenha: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linkCadastro = view.findViewById(R.id.linkCadastro)
        botaoCadastro = view.findViewById(R.id.botaoCadastro)
        email = view.findViewById(R.id.emailCadastro)
        nome = view.findViewById(R.id.nomeCadastro)
        cpf = view.findViewById(R.id.cpfCadastro)
        senha = view.findViewById(R.id.senhaCadastro)
        confirmaSenha = view.findViewById(R.id.confirmaSenhaCadastro)

        linkCadastro.setOnClickListener {
            (activity as MainActivity).showFragment(Login())
        }

        botaoCadastro.setOnClickListener {
            val email = email.text.toString().trim()
            val nome = nome.text.toString().trim()
            val cpf = cpf.text.toString().trim()
            val senha = senha.text.toString().trim()
            val confirmaSenha = confirmaSenha.text.toString().trim()

            if (email.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty() || nome.isEmpty() || cpf.isEmpty()) {
                Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (senha != confirmaSenha) {
                Toast.makeText(context, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            cadastrarUsuario(email, nome, cpf, senha)
        }
    }

    private fun cadastrarUsuario(email: String, nome: String, cpf: String, senha: String) {
        autenticacao.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val user = task.result?.user
                    if (user != null) {

                        user.updateProfile(
                            UserProfileChangeRequest.Builder()
                                .setDisplayName(nome)
                                .build()
                        )
                            .addOnCompleteListener { profileTask ->
                                if (profileTask.isSuccessful) {
                                    Toast.makeText(
                                        context,
                                        "Usuário cadastrado!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    (activity as MainActivity).showFragment(Login())
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Erro ao atualizar o perfil",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            .addOnFailureListener { exception ->
                                Toast.makeText(
                                    context,
                                    "Erro ao atualizar o perfil: ${exception.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    context,
                    "Falha no cadastro: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}