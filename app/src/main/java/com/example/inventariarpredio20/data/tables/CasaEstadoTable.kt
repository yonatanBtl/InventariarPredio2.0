package com.example.inventariarpredio20.data.tables

import com.example.inventariarpredio20.data.tables.CasaTable.autoIncrement
import com.example.inventariarpredio20.data.tables.CasaTable.varchar
import org.jetbrains.exposed.sql.Table


object CasaEstadoTable : Table("casa_estado"){
    val idEstado = integer("id_estado").autoIncrement()
    val descripcion = varchar("descripcion", length = 255)

    override val primaryKey = PrimaryKey(idEstado)
}