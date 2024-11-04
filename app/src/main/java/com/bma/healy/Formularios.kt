package com.bma.healy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bma.healy.bancodedados.FormularioDAO
import com.bma.healy.databinding.FragmentFormulariosBinding
import com.bma.healy.model.Formulario
import java.text.SimpleDateFormat
import java.util.Locale

class Formularios : Fragment() {

    private lateinit var binding: FragmentFormulariosBinding
    private lateinit var formularioDAO: FormularioDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormulariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        formularioDAO = FormularioDAO(requireContext())

        binding.registrar.setOnClickListener {
            val idade = binding.idade.text.toString()
            val genero = binding.genero.text.toString()
            val altura = binding.altura.text.toString()
            val peso = binding.peso.text.toString()
            val civil = binding.civil.text.toString()
            val pais = binding.pais.text.toString()
            val fuma = binding.fuma.text.toString()
            val bebe = binding.bebe.text.toString()
            val alergia = binding.alergia.text.toString()
            val medicamento = binding.medicamento.text.toString()
            val historico = binding.historico.text.toString()

            val data = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            ).format(System.currentTimeMillis())


            val novoFormulario = Formulario(
                idade = idade,
                genero = genero,
                altura = altura,
                peso = peso,
                civil = civil,
                pais = pais,
                fuma = fuma,
                bebe = bebe,
                alergia = alergia,
                medicamento = medicamento,
                historico = historico,
                data = data
            )

            if (formularioDAO.salvar(novoFormulario)) {
                Toast.makeText(
                    requireContext(),
                    "Formulario salvo com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()

                limparCampos()

            } else {
                Toast.makeText(
                    requireContext(),
                    "Erro ao salvar formulario.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun limparCampos() {
        binding.idade.setText("")
        binding.genero.setText("")
        binding.altura.setText("")
        binding.peso.setText("")
        binding.civil.setText("")
        binding.pais.setText("")
        binding.fuma.setText("")
        binding.bebe.setText("")
        binding.alergia.setText("")
        binding.medicamento.setText("")
        binding.historico.setText("")
    }
}