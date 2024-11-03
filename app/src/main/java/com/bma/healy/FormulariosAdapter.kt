package com.bma.healy

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bma.healy.FormularioViewHolder.FormularioViewHolder


import com.bma.healy.databinding.FragmentItemBinding
import com.bma.healy.model.Formulario


class FormularioAdapter : RecyclerView.Adapter<FormularioAdapter.FormularioViewHolder>() {

    private var formularios: List<Formulario> = emptyList()

    fun updateFormularios(formularios: List<Formulario>) {
        this.formularios = formularios
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormularioViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false)
        return FormularioViewHolder(view)
    }

    override fun onBindViewHolder(holder: FormularioViewHolder, position: Int) {
        val formulario = formularios[position]
        holder.formularioTextView.text = "Preenchido em: ${formulario.data}"
        holder.idadeTextView.text = "Idade: ${formulario.idade}"
        holder.generoTextView.text = "Gênero: ${formulario.genero}"
        holder.alturaTextView.text = "Altura: ${formulario.altura}"
        holder.pesoTextView.text = "Peso: ${formulario.peso}"
        holder.civilTextView.text = "Estado Civil: ${formulario.civil}"
        holder.paisTextView.text = "País: ${formulario.pais}"
        holder.fumaTextView.text = "Fuma: ${formulario.fuma}"
        holder.bebeTextView.text = "Bebe: ${formulario.bebe}"
        holder.alergiaTextView.text = "Alergia: ${formulario.alergia}"
        holder.medicamentoTextView.text = "Medicamento: ${formulario.medicamento}"
        holder.historicoTextView.text = "Histórico: ${formulario.historico}"
    }

    override fun getItemCount(): Int {
        return formularios.size
    }

    class FormularioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val formularioTextView: TextView = itemView.findViewById(R.id.formulario)
        val idadeTextView: TextView = itemView.findViewById(R.id.idade)
        val generoTextView: TextView = itemView.findViewById(R.id.genero)
        val alturaTextView: TextView = itemView.findViewById(R.id.altura)
        val pesoTextView: TextView = itemView.findViewById(R.id.peso)
        val civilTextView: TextView = itemView.findViewById(R.id.civil)
        val paisTextView: TextView = itemView.findViewById(R.id.pais)
        val fumaTextView: TextView = itemView.findViewById(R.id.fuma)
        val bebeTextView: TextView = itemView.findViewById(R.id.bebe)
        val alergiaTextView: TextView = itemView.findViewById(R.id.alergia)
        val medicamentoTextView: TextView = itemView.findViewById(R.id.medicamento)
        val historicoTextView: TextView = itemView.findViewById(R.id.historico)
    }
}