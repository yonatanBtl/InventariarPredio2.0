package com.example.inventariarpredio20.data.tables

import com.example.inventariarpredio20.data.tables.PredioTable.autoIncrement
import org.jetbrains.exposed.sql.Table

object MDUTable : Table("mdu"){
    val idMDU = integer("id_mdu").autoIncrement()
    val descripcion = varchar("descripcion", length = 255)

    override val primaryKey = PrimaryKey(MDUTable.idMDU)
}