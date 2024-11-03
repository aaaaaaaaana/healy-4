package com.bma.healy.FormularioViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bma.healy.R

class FormularioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val formulario: TextView = itemView.findViewById(R.id.formulario)
    val idade: TextView = itemView.findViewById(R.id.idade)
    val genero: TextView = itemView.findViewById(R.id.genero)
    val altura: TextView = itemView.findViewById(R.id.altura)
    val peso: TextView = itemView.findViewById(R.id.peso)
    val civil: TextView = itemView.findViewById(R.id.civil)
    val pais: TextView = itemView.findViewById(R.id.pais)
    val fuma: TextView = itemView.findViewById(R.id.fuma)
    val bebe: TextView = itemView.findViewById(R.id.bebe)
    val alergia: TextView = itemView.findViewById(R.id.alergia)
    val medicamento: TextView = itemView.findViewById(R.id.medicamento)
    val historico: TextView = itemView.findViewById(R.id.historico)
}
