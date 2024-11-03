package com.bma.healy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bma.healy.model.Formulario
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class Perfil : Fragment() {
    val DATASTORE_NAME = "formulario_datastore"
    val FORMULARIO_KEY = stringPreferencesKey("formulario_data")
    val FORMULARIO_DADOS_KEY = stringPreferencesKey("formulario_dados")

    private lateinit var recyclerView: RecyclerView
    private lateinit var formularioAdapter: FormularioAdapter

    private val Context.dataStore by preferencesDataStore(
        name = DATASTORE_NAME
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)

        formularioAdapter = FormularioAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = formularioAdapter

        loadFormularios()
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