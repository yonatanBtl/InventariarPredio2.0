package com.example.inventariarpredio20.data.conexion

import android.util.Log
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object db {
    fun init() {
        val driverClassName = "org.postgresql.Driver"
        val jdbcURL = "jdbc:postgresql://137.184.120.127:5432/sigcon"
        val user = "modulo4"
        val password = "modulo4"
        try {
            Database.connect(jdbcURL, driverClassName, user, password)
            Log.d("DataBaseManager", "Conexion exitosa")
            //println("Conexion exitosa")
        } catch (e: Exception) {
            Log.e("DataBaseManager", "Error al conectar a la Base de Datos")
            //println("Error al conectar a la Base de Datos")
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}