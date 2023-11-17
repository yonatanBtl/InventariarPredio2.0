package com.example.inventariarpredio20.data.repository

import com.example.inventariarpredio20.data.conexion.db
import com.example.inventariarpredio20.data.tables.CasaTable
import com.example.inventariarpredio20.data.tables.CasaEstadoTable
import com.example.inventariarpredio20.data.tables.MDUTable
import com.example.inventariarpredio20.data.tables.PredioMDUTable
import com.example.inventariarpredio20.models.PredioCasa
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.max
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

object CasaRepository {
    suspend fun obtenerEstadoCasa(): List<String> {
        return db.dbQuery {
            CasaEstadoTable.selectAll()
                .orderBy(CasaEstadoTable.idEstado)
                .map { it[CasaEstadoTable.descripcion] }
        }
    }

    suspend fun guardarDep(casa: PredioCasa) {
        val UltimoIdCasa = db.dbQuery {
            CasaTable
                .slice(CasaTable.idCasa.max())
                .selectAll()
                .singleOrNull()
                ?.get(CasaTable.idCasa.max()) as? Int
        } ?: 1

        val siguienteIdCasa = UltimoIdCasa + 1

        db.dbQuery {
            CasaTable.insert {
                it[idCasa] = siguienteIdCasa
                it[idPredio] = casa.idPredio
                it[idEstado] = casa.idEstado
                it[numero] = casa.numero
                it[piso] = casa.piso
                it[area] = casa.area
                it[participacion] = casa.participacion
            }
        }
    }

    suspend fun obtenerIdEstadoPorNombre(nombreEstadoCasa: String): Int? {
        return db.dbQuery {
            CasaEstadoTable.select { CasaEstadoTable.descripcion eq nombreEstadoCasa }
                .singleOrNull()?.get(CasaEstadoTable.idEstado)
        }
    }

}