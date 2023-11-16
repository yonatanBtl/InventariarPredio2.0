package com.example.inventariarpredio20.data.tables

import org.jetbrains.exposed.sql.Table

object AreaComunTable: Table("area_comun") {
    val idAreaComun= integer("id_area_comun").autoIncrement()
    val descripcion = varchar("descripcion", length = 255)

    override val primaryKey = PrimaryKey(idAreaComun)
}