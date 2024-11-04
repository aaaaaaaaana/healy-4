package com.bma.healy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bma.healy.bancodedados.FormularioDAO
import com.bma.healy.model.Formulario

class FormularioCard : Fragment() {
    private lateinit var idade: TextView
    private lateinit var genero: TextView
    private lateinit var altura: TextView
    private lateinit var peso: TextView
    private lateinit var civil: TextView
    private lateinit var pais: TextView
    private lateinit var fuma: TextView
    private lateinit var bebe: TextView
    private lateinit var alergia: TextView
    private lateinit var medicamento: TextView
    private lateinit var historico: TextView
    private lateinit var data: TextView
    private lateinit var formularioDAO: FormularioDAO

    companion object {
        const val ARG_FORMULARIO = "formulario"

        fun newInstance(formulario: Formulario): FormularioCard {
            val fragment = FormularioCard()
            val args = Bundle()
            args.putParcelable(ARG_FORMULARIO, formulario)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_formulario_card, container, false)

        idade = view.findViewById(R.id.idade)
        genero = view.findViewById(R.id.genero)
        altura = view.findViewById(R.id.altura)
        peso = view.findViewById(R.id.peso)
        civil = view.findViewById(R.id.civil)
        pais = view.findViewById(R.id.pais)
        fuma = view.findViewById(R.id.fuma)
        bebe = view.findViewById(R.id.bebe)
        alergia = view.findViewById(R.id.alergia)
        medicamento = view.findViewById(R.id.medicamento)
        historico = view.findViewById(R.id.historico)
        data = view.findViewById(R.id.data)

        formularioDAO = FormularioDAO(requireContext())

        val formulario = arguments?.getParcelable<Formulario>(ARG_FORMULARIO)

        if (formulario != null) {
            idade.text = "Idade: ${formulario.idade}"
            genero.text = "Gênero: ${formulario.genero}"
            altura.text = "Altura: ${formulario.altura}"
            peso.text = "Peso: ${formulario.peso}"
            civil.text = "Estado Civil: ${formulario.civil}"
            pais.text = "País: ${formulario.pais}"
            fuma.text = "Fuma: ${formulario.fuma}"
            bebe.text = "Bebe: ${formulario.bebe}"
            alergia.text = "Alergia: ${formulario.alergia}"
            medicamento.text = "Medicamento: ${formulario.medicamento}"
            historico.text = "Histórico: ${formulario.historico}"
            data.text = "Data: ${formulario.data}"
        }

        return view
    }
}