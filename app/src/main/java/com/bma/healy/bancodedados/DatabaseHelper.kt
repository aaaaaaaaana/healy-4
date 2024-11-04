package com.bma.healy.bancodedados


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            if (INSTANCE == null) {
                INSTANCE = DatabaseHelper(context)
            }
            return INSTANCE!!
        }

        private const val DATABASE_NAME = "formularios.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "formularios"
        const val COLUMN_ID = "id"
        const val COLUMN_IDADE = "idade"
        const val COLUMN_GENERO = "genero"
        const val COLUMN_ALTURA = "altura"
        const val COLUMN_PESO = "peso"
        const val COLUMN_CIVIL = "civil"
        const val COLUMN_PAIS = "pais"
        const val COLUMN_FUMA = "fuma"
        const val COLUMN_BEBE = "bebe"
        const val COLUMN_ALERGIA = "alergia"
        const val COLUMN_MEDICAMENTO = "medicamento"
        const val COLUMN_HISTORICO = "historico"
        const val COLUMN_DATA = "data"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IDADE + " TEXT, " +
                COLUMN_GENERO + " TEXT, " +
                COLUMN_ALTURA + " TEXT, " +
                COLUMN_PESO + " TEXT, " +
                COLUMN_CIVIL + " TEXT, " +
                COLUMN_PAIS + " TEXT, " +
                COLUMN_FUMA + " TEXT, " +
                COLUMN_BEBE + " TEXT, " +
                COLUMN_ALERGIA + " TEXT, " +
                COLUMN_MEDICAMENTO + " TEXT, " +
                COLUMN_HISTORICO + " TEXT, " +
                COLUMN_DATA + " TEXT" +
                ")"

        try {
            db.execSQL(createTableQuery)
            Log.i("db_info", "Tabela criada com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db_info", "Erro ao criar tabela")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}

