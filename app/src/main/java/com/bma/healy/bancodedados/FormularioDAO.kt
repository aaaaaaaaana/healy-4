package com.bma.healy.bancodedados

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.bma.healy.model.Formulario
import java.lang.Exception

class FormularioDAO(context: Context) : IFormularioDAO {
    private val dbHelper = DatabaseHelper.getInstance(context)

    override fun salvar(formulario: Formulario): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues()

        values.put(DatabaseHelper.COLUMN_IDADE, formulario.idade)
        values.put(DatabaseHelper.COLUMN_GENERO, formulario.genero)
        values.put(DatabaseHelper.COLUMN_ALTURA, formulario.altura)
        values.put(DatabaseHelper.COLUMN_PESO, formulario.peso)
        values.put(DatabaseHelper.COLUMN_CIVIL, formulario.civil)
        values.put(DatabaseHelper.COLUMN_PAIS, formulario.pais)
        values.put(DatabaseHelper.COLUMN_FUMA, formulario.fuma)
        values.put(DatabaseHelper.COLUMN_BEBE, formulario.bebe)
        values.put(DatabaseHelper.COLUMN_ALERGIA, formulario.alergia)
        values.put(DatabaseHelper.COLUMN_MEDICAMENTO, formulario.medicamento)
        values.put(DatabaseHelper.COLUMN_HISTORICO, formulario.historico)
        values.put(DatabaseHelper.COLUMN_DATA, formulario.data)

        try {
            db.insert(DatabaseHelper.TABLE_NAME, null, values)
            Log.i("db_info", "Formulario salvo")
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db_info", "Erro ao salvar Formulario")
            return false
        } finally {
            db.close()
        }
    }


    @SuppressLint("Range")
    override fun listar(): List<Formulario> {
        val formularios: MutableList<Formulario> = ArrayList()
        val db = dbHelper.readableDatabase

        try {
            val cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null)

            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID))
                    val idade = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IDADE))
                    val genero =
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_GENERO))
                    val altura =
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ALTURA))
                    val peso = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PESO))
                    val civil = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CIVIL))
                    val pais = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PAIS))
                    val fuma = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FUMA))
                    val bebe = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_BEBE))
                    val alergia =
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ALERGIA))
                    val medicamento =
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MEDICAMENTO))
                    val historico =
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_HISTORICO))
                    val data = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATA))

                    val f = Formulario(
                        id,
                        idade,
                        genero,
                        altura,
                        peso,
                        civil,
                        pais,
                        fuma,
                        bebe,
                        alergia,
                        medicamento,
                        historico,
                        data
                    )
                    formularios.add(f)
                } while (cursor.moveToNext())
            }

            Log.i("db_info", "Formularios listados")
            return formularios
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db_info", "Erro ao listar Formularios")
            return formularios
        } finally {
            db.close()
        }
    }

    override fun inserir(formulario: Formulario): Boolean {
        return salvar(formulario)
    }

}
