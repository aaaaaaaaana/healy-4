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
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class Cadastro : Fragment() {


    private val autenticador by lazy {
        Firebase.auth
    }


    private lateinit var linkCadastro: TextView
    private lateinit var botaoCadastro: Button
    private lateinit var email: EditText
    private lateinit var nome: EditText
    private lateinit var cpf: EditText
    private lateinit var senha: EditText
    private lateinit var confirmaSenha: EditText


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

            if (email.isEmpty() || nome.isEmpty() || cpf.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
                Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else if (senha != confirmaSenha) {
                Toast.makeText(context, "Senhas não coincidem", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(context, "Email inválido", Toast.LENGTH_SHORT).show()
            } else {
                cadastrarUsuario(email, senha, nome, cpf)
            }
        }
    }

    private fun cadastrarUsuario(email: String, senha: String, nome: String, cpf: String) {
        autenticador.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val user = task.result?.user
                    if (user != null) {

                        user.updateProfile(
                            com.google.firebase.auth.UserProfileChangeRequest.Builder()
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
                } else {

                    Toast.makeText(
                        context,
                        "Falha no cadastro: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
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