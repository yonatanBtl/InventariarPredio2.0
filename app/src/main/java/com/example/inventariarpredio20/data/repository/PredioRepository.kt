package com.example.inventariarpredio20.data.repository

import com.example.inventariarpredio20.data.conexion.db
import com.example.inventariarpredio20.data.tables.PredioTable
import com.example.inventariarpredio20.models.Predio
import org.jetbrains.exposed.sql.andWhere
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

    suspend fun obtenerDatosPredio(): List<Predio> {
        return db.dbQuery {
            PredioTable.selectAll().map {
                Predio(
                    it[PredioTable.idPredio],
                    it[PredioTable.idTipoPredio],
                    it[PredioTable.descripcion],
                    it[PredioTable.ruc],
                    it[PredioTable.telefono],
                    it[PredioTable.correo],
                    it[PredioTable.direccion],
                    it[PredioTable.idUbigeo]
                )
            }
        }
    }

    suspend fun filtrarPredios(
        tipoPredio: String?,
        nombrePredio: String?,
        rucPredio: String?
    ): List<Predio> {
        val query = PredioTable.selectAll()

        if (!tipoPredio.isNullOrBlank()) {
            val tipoPredioInt = tipoPredio.toIntOrNull()
            if (tipoPredioInt != null) {
                query.andWhere {
                    PredioTable.idTipoPredio eq tipoPredioInt
                }
            }
        }

        if (!nombrePredio.isNullOrBlank()) {
            query.andWhere {
                PredioTable.descripcion like "%$nombrePredio%"
            }
        }

        if (!rucPredio.isNullOrBlank()) {
            query.andWhere {
                PredioTable.ruc eq rucPredio
            }
        }

        return db.dbQuery {
            query.map {
                Predio(
                    it[PredioTable.idPredio],
                    it[PredioTable.idTipoPredio],
                    it[PredioTable.descripcion],
                    it[PredioTable.ruc],
                    it[PredioTable.telefono],
                    it[PredioTable.correo],
                    it[PredioTable.direccion],
                    it[PredioTable.idUbigeo]
                )
            }
        }
    }


}