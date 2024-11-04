package com.bma.healy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bma.healy.bancodedados.FormularioDAO
import com.bma.healy.databinding.FragmentPerfilBinding


class Perfil : Fragment() {
    private lateinit var binding: FragmentPerfilBinding
    private lateinit var formularioDAO: FormularioDAO
    private lateinit var adapter: FormularioAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        formularioDAO = FormularioDAO(requireContext())

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FormularioAdapter(emptyList()) { formulario ->

            (activity as? MainActivity)?.showFragment(FormularioCard.newInstance(formulario))
        }
        binding.recyclerView.adapter = adapter

        loadFormularios()
    }

    private fun loadFormularios() {
        val formularios = formularioDAO.listar()
        adapter.updateFormularios(formularios)
    }


}