package com.bma.healy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bma.healy.databinding.FragmentItemBinding
import com.bma.healy.model.Formulario

class FormularioAdapter : RecyclerView.Adapter<FormularioAdapter.FormularioViewHolder>() {

    private var formularios: List<Formulario> = emptyList()

    fun updateFormularios(formularios: List<Formulario>) {
        this.formularios = formularios
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormularioViewHolder {
        val binding = FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FormularioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FormularioViewHolder, position: Int) {
        val formulario = formularios[position]
        holder.bind(formulario)
    }

    override fun getItemCount(): Int {
        return formularios.size
    }

    class FormularioViewHolder(private val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(formulario: Formulario) {
            binding.formulario.text = "Preenchido em: ${formulario.data}"
            binding.idade.setText(formulario.idade)
            binding.genero.setText(formulario.genero)
            binding.altura.setText(formulario.altura)
            binding.peso.setText(formulario.peso)
            binding.civil.setText(formulario.civil)
            binding.pais.setText(formulario.pais)
            binding.fuma.setText(formulario.fuma)
            binding.bebe.setText(formulario.bebe)
            binding.alergia.setText(formulario.alergia)
            binding.medicamento.setText(formulario.medicamento)
            binding.historico.setText(formulario.historico)
        }
    }
}