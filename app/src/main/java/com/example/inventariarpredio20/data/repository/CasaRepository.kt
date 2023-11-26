package com.example.inventariarpredio20.data.repository

import com.example.inventariarpredio20.data.conexion.db
import com.example.inventariarpredio20.data.tables.CasaTable
import com.example.inventariarpredio20.data.tables.CasaEstadoTable
import com.example.inventariarpredio20.data.tables.MDUTable
import com.example.inventariarpredio20.data.tables.PredioMDUTable
import com.example.inventariarpredio20.models.PredioCasa
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.max
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.math.BigDecimal

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

        val ultimoIdPredioMdu = db.dbQuery {
            PredioMDUTable
                .slice(PredioMDUTable.idPredioMDU.max())
                .selectAll()
                .singleOrNull()
                ?.get(PredioMDUTable.idPredioMDU.max()) as? Int
        } ?: 1

        db.dbQuery {
            CasaTable.insert {
                it[idCasa] = siguienteIdCasa
                it[idPredio] = casa.idPredio
                it[idEstado] = casa.idEstado
                it[idPredioMdu] = ultimoIdPredioMdu
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

    suspend fun obtenerCasasPorIdMDU(idMDU: String?, idPredio: String): List<PredioCasa> {
        return transaction {
            val idMDUtoInt = idMDU?.toIntOrNull()
            val idPredioToInt = idPredio.toIntOrNull() ?: return@transaction emptyList()

            val casasQuery = if (idMDUtoInt != null) {
                CasaTable.select {
                    (CasaTable.idPredioMdu inList
                            PredioMDUTable.select { PredioMDUTable.idMDU eq idMDUtoInt }
                                .map { it[PredioMDUTable.idPredioMDU] }) and
                            (CasaTable.idPredio eq idPredioToInt)
                }
            } else {
                CasaTable.select { CasaTable.idPredio eq idPredioToInt }
            }

            casasQuery.map { row ->
                PredioCasa(
                    idCasa = row[CasaTable.idCasa],
                    idPredio = row[CasaTable.idPredio],
                    idEstado = row[CasaTable.idEstado],
                    idPredioMdu = row[CasaTable.idPredioMdu],
                    numero = row[CasaTable.numero],
                    piso = row[CasaTable.piso]?.toShort(),
                    area = row[CasaTable.area],
                    participacion = row[CasaTable.participacion]
                )
            }
        }
    }





    /*    suspend fun obtenerCasasPorIdMDU(idMDU: String): List<PredioCasa> {
            return transaction {
                CasaTable.select {
                    CasaTable.idPredioMdu eq PredioMDUTable.idMDU
                }.map { row ->
                    PredioCasa(
                        idCasa = row[CasaTable.idCasa],
                        idPredio = row[CasaTable.idPredio],
                        idEstado = row[CasaTable.idEstado],
                        idPredioMdu = row[CasaTable.idPredioMdu],
                        numero = row[CasaTable.numero],
                        piso = row[CasaTable.piso]?.toShort(),
                        area = row[CasaTable.area],
                        participacion = row[CasaTable.participacion]
                    )
                }
            }
        }
    */
}