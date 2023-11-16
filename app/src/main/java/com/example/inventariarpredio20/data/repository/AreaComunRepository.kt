package com.example.inventariarpredio20.data.repository

import com.example.inventariarpredio20.data.conexion.db
import com.example.inventariarpredio20.data.tables.AreaComunTable
import com.example.inventariarpredio20.data.tables.PredioAreaComunTable
import com.example.inventariarpredio20.data.tables.PredioTable
import com.example.inventariarpredio20.models.PredioAreaComun
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

object AreaComunRepository {
    suspend fun obtenerTipoAreaComun(): List<String> {
        return db.dbQuery {
            AreaComunTable.selectAll()
                .orderBy(AreaComunTable.idAreaComun)
                .map { it[AreaComunTable.descripcion] }
        }
    }

    suspend fun guardarAreaComun(areaComun: PredioAreaComun) {
        db.dbQuery {
            PredioAreaComunTable.insert {
                it[idPredio] = areaComun.idPredio
                it[idAreaComun] = areaComun.idAreaComun
                it[codigo] = areaComun.codigo?: "valor_predeterminado"
                it[area] = areaComun.area
            }
        }
    }

    suspend fun obtenerIdTipoAreaComunPorNombre(nombreTipo: String): Int? {
        return db.dbQuery {
            AreaComunTable.select { AreaComunTable.descripcion eq nombreTipo }
                .singleOrNull()?.get(AreaComunTable.idAreaComun)
        }
    }
}
/*
class AreaComunRepositorys{
    fun guardarAreaComun(areaComun: AreaComun): Long {
        return db.dbQuery {
            PredioAreaComunTable.insertAndGetId {
                it[PredioAreaComunTable.idPredio] = areaComun.idPredio
                it[PredioAreaComunTable.codigo] = areaComun.codigo
                it[PredioAreaComunTable.area] = areaComun.area
            }.value
        }
    }
}*/