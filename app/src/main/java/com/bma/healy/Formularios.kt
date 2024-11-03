package com.bma.healy

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bma.healy.model.Formulario
import com.google.gson.Gson // Import Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Locale


const val DATASTORE_NAME = "formulario_datastore"
val FORMULARIO_KEY = stringPreferencesKey("formulario_data")
val FORMULARIO_DADOS_KEY = stringPreferencesKey("formulario_dados")

class Formularios : Fragment() {
    private lateinit var idade: EditText
    private lateinit var genero: EditText
    private lateinit var altura: EditText
    private lateinit var peso: EditText
    private lateinit var civil: EditText
    private lateinit var pais: EditText
    private lateinit var fuma: EditText
    private lateinit var bebe: EditText
    private lateinit var alergia: EditText
    private lateinit var medicamento: EditText
    private lateinit var historico: EditText
    private lateinit var registrar: Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var formularioAdapter: FormularioAdapter


    private val Context.dataStore by preferencesDataStore(
        name = DATASTORE_NAME
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_formularios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        registrar = view.findViewById(R.id.registrar)
        recyclerView = view.findViewById(R.id.recyclerView)

        formularioAdapter = FormularioAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = formularioAdapter

        loadFormularios()

        registrar.setOnClickListener {
            val data = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(System.currentTimeMillis())
            val formulario = Formulario(
                idade.text.toString(),
                genero.text.toString(),
                altura.text.toString(),
                peso.text.toString(),
                civil.text.toString(),
                pais.text.toString(),
                fuma.text.toString(),
                bebe.text.toString(),
                alergia.text.toString(),
                medicamento.text.toString(),
                historico.text.toString(),
                data
            )

            idade.setText("")
            genero.setText("")
            altura.setText("")
            peso.setText("")
            civil.setText("")
            pais.setText("")
            fuma.setText("")
            bebe.setText("")
            alergia.setText("")
            medicamento.setText("")
            historico.setText("")

            saveFormulario(formulario)
            Toast.makeText(context, "Formulário salvo!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveFormulario(formulario: Formulario) {
        lifecycleScope.launch {
            requireContext().dataStore.edit { preferences ->
                val gson = Gson()
                var formularios: MutableList<Formulario> =
                    try {
                        gson.fromJson(preferences[FORMULARIO_KEY], object : TypeToken<List<Formulario>>() {}.type)
                    } catch (e: JsonSyntaxException) {
                        mutableListOf()
                    }
                formularios.add(formulario)
                val json = gson.toJson(formularios)
                preferences[FORMULARIO_DADOS_KEY] = json
            }
        }
    }
    private fun loadFormularios() {
        lifecycleScope.launch {
            requireContext().dataStore.data.collect { preferences ->
                val json = preferences[FORMULARIO_DADOS_KEY]
                if (json != null) {
                    val gson = Gson()
                    val type: Type = object : TypeToken<List<Formulario>>() {}.type
                    val formularios: List<Formulario> = try {
                        gson.fromJson(json, type)
                    } catch (e: JsonSyntaxException) {
                        emptyList()
                    }
                    formularioAdapter.updateFormularios(formularios)
                }
            }
        }
    }

}