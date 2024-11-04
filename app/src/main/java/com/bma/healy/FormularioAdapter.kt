package com.bma.healy


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bma.healy.model.Formulario

class FormularioAdapter(
    private var formularios: List<Formulario>,
    private val onItemClickListener: (Formulario) -> Unit
) : RecyclerView.Adapter<FormularioAdapter.FormularioViewHolder>() {

    class FormularioViewHolder(
        itemView: View,
        private val onItemClickListener: (Formulario) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        val dataTextView: TextView = itemView.findViewById(R.id.data)

        fun bind(formulario: Formulario) {
            dataTextView.text = "Data: ${formulario.data}"


            itemView.setOnClickListener {
                onItemClickListener(formulario)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormularioViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)


        return FormularioViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: FormularioViewHolder, position: Int) {
        val formulario = formularios[position]
        holder.bind(formulario)
    }

    override fun getItemCount(): Int {
        return formularios.size
    }

    fun updateFormularios(newFormularios: List<Formulario>) {
        this.formularios = newFormularios
        notifyDataSetChanged()
    }

}