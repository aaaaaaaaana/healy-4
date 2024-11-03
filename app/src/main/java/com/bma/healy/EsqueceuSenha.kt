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
import com.google.firebase.auth.auth


class EsqueceuSenha : Fragment() {

    private val autenticador by lazy {
        Firebase.auth
    }


    private lateinit var linkEsqueceuSenha: TextView
    private lateinit var botaoRedefinir: Button
    private lateinit var email: EditText
    private lateinit var novaSenha: EditText
    private lateinit var confirmaSenha: EditText


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
        novaSenha = view.findViewById(R.id.novaSenha)
        confirmaSenha = view.findViewById(R.id.confirmaSenhaNova)

        linkEsqueceuSenha.setOnClickListener {
            (activity as MainActivity).showFragment(Login())
        }

        botaoRedefinir.setOnClickListener {
            val email = email.text.toString().trim()
            val novaSenha = novaSenha.text.toString().trim()
            val confirmaSenha = confirmaSenha.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(context, "Digite seu email", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(context, "Email inválido", Toast.LENGTH_SHORT).show()
            } else if (novaSenha.isEmpty() || confirmaSenha.isEmpty()) {
                Toast.makeText(context, "Digite a nova senha e a confirmação", Toast.LENGTH_SHORT)
                    .show()
            } else if (novaSenha != confirmaSenha) {
                Toast.makeText(context, "Senhas não coincidem", Toast.LENGTH_SHORT).show()
            } else {

                autenticador.fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            atualizarSenha(email, novaSenha)
                        } else {

                            Toast.makeText(context, "Email não encontrado.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    .addOnFailureListener { exception ->

                        Toast.makeText(
                            context,
                            "Erro ao verificar o email: ${exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }


    private fun atualizarSenha(email: String, novaSenha: String) {
        autenticador.signInWithEmailAndPassword(email, "")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val user = task.result?.user
                    if (user != null) {
                        user.updatePassword(novaSenha)
                            .addOnCompleteListener { updateTask ->
                                if (updateTask.isSuccessful) {

                                    Toast.makeText(
                                        context,
                                        "Senha atualizada com sucesso!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    (activity as MainActivity).showFragment(Login())
                                } else {

                                    Toast.makeText(
                                        context,
                                        "Erro ao atualizar a senha: ${updateTask.exception?.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            .addOnFailureListener { exception ->

                                Toast.makeText(
                                    context,
                                    "Erro ao atualizar a senha: ${exception.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                } else {

                    Toast.makeText(
                        context,
                        "Erro ao fazer login: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}