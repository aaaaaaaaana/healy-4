package com.bma.healy.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Formulario(
    val id: Int = 0,
    val idade: String,
    val genero: String,
    val altura: String,
    val peso: String,
    val civil: String,
    val pais: String,
    val fuma: String,
    val bebe: String,
    val alergia: String,
    val medicamento: String,
    val historico: String,
    val data: String?

) : Parcelable


