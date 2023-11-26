package com.example.inventariarpredio20.data.repository

import com.example.inventariarpredio20.data.conexion.db
import com.example.inventariarpredio20.data.tables.MDUTable
import com.example.inventariarpredio20.data.tables.PredioTable
import com.example.inventariarpredio20.data.tables.PredioMDUTable
import com.example.inventariarpredio20.models.PredioMDU
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.max
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object MDURepository {

    suspend fun obtenerTipoMDU(): List<String> {
        return db.dbQuery {
            MDUTable.selectAll()
                .orderBy(MDUTable.idMDU)
                .map { it[MDUTable.descripcion] }
        }
    }
    suspend fun guardarMDU(mdu: PredioMDU) {
        val nuevoIdPredioMDU = db.dbQuery {
            PredioMDUTable
                .slice(PredioMDUTable.idPredioMDU.max())
                .selectAll()
                .singleOrNull()
                ?.get(PredioMDUTable.idPredioMDU.max()) as? Int
        } ?: 1

        val siguienteIdPredioMDU = nuevoIdPredioMDU + 1

        db.dbQuery {
            PredioMDUTable.insert {
                it[idPredioMDU] = siguienteIdPredioMDU
                it[idPredio] = mdu.idPredio
                it[idMDU] = mdu.idMDU
                it[descripcion] = mdu.descripcion
                it[direccion] = mdu.direccion
                it[numero] = mdu.numero
                it[totalCasas] = 0
            }
        }
    }

    suspend fun obtenerIdTipoMDUPorNombre(nombreTipoMDU: String): Int? {
        return db.dbQuery {
            MDUTable.select { MDUTable.descripcion eq nombreTipoMDU }
                .singleOrNull()?.get(MDUTable.idMDU)
        }
    }

    suspend fun obtenerPredioMDUPorIdMDU(idMDU: String): List<PredioMDU> {
        return transaction {
            PredioMDUTable.select {
                PredioMDUTable.idMDU eq idMDU.toInt()
            }.map { row ->
                PredioMDU(
                    idPredioMDU = row[PredioMDUTable.idPredioMDU],
                    idPredio = row[PredioMDUTable.idPredio],
                    idMDU = row[PredioMDUTable.idMDU],
                    descripcion = row[PredioMDUTable.descripcion],
                    direccion = row[PredioMDUTable.direccion],
                    numero = row[PredioMDUTable.numero],
                    totalCasas = row[PredioMDUTable.totalCasas]
                )
            }
        }
    }



}