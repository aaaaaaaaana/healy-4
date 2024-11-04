package com.bma.healy.bancodedados

import com.bma.healy.model.Formulario


interface IFormularioDAO {
    fun salvar(formulario: Formulario): Boolean
    fun listar(): List<Formulario>
    fun inserir(formulario: Formulario): Boolean
}