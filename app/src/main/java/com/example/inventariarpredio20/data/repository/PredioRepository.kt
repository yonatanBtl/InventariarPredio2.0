package com.example.inventariarpredio20.data.repository

import com.example.inventariarpredio20.data.conexion.db
import com.example.inventariarpredio20.data.tables.PredioTable
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

object PredioRepository {

    suspend fun obtenerNombresDePredios(): List<String> {
        return db.dbQuery {
            PredioTable.selectAll()
                .orderBy(PredioTable.idPredio)
                .map { it[PredioTable.descripcion] }
        }
    }

    suspend fun obtenerIdPredioPorNombre(nombrePredio: String): Int? {
        return db.dbQuery {
            PredioTable.select { PredioTable.descripcion eq nombrePredio }
                .singleOrNull()?.get(PredioTable.idPredio)
        }
    }
}