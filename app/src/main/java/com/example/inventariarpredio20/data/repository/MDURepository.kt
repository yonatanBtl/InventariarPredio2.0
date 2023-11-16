package com.example.inventariarpredio20.data.repository

import com.example.inventariarpredio20.data.conexion.db
import com.example.inventariarpredio20.data.tables.MDUTable
import com.example.inventariarpredio20.data.tables.PredioTable
import com.example.inventariarpredio20.data.tables.PredioMDUTable
import com.example.inventariarpredio20.models.PredioMDU
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

object MDURepository {

    suspend fun obtenerTipoMDU(): List<String> {
        return db.dbQuery {
            MDUTable.selectAll()
                .orderBy(MDUTable.idMDU)
                .map { it[MDUTable.descripcion] }
        }
    }
    suspend fun guardarMDU(mdu: PredioMDU) {
        db.dbQuery {
            PredioMDUTable.insert {
                //it[idPredioMDU] = mdu.idPredioMDU
                it[idPredio] = mdu.idPredio
                it[idMDU] = mdu.idMDU
                it[descripcion] = mdu.descripcion
                it[direccion] = mdu.direccion
                it[numero] = mdu.numero
            }
        }
    }

    suspend fun obtenerIdTipoMDUPorNombre(nombreTipoMDU: String): Int? {
        return db.dbQuery {
            MDUTable.select { MDUTable.descripcion eq nombreTipoMDU }
                .singleOrNull()?.get(MDUTable.idMDU)
        }
    }



}