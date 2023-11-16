package com.example.inventariarpredio20.data.tables

import org.jetbrains.exposed.sql.Table


object PredioAreaComunTable : Table("predio_area_comun") {
    //val idPredio = PredioTable.integer("id_predio").autoIncrement()
    //val idAreaComun= AreaComunTable.integer("id_area_comun").autoIncrement()
    val idPredio = integer("id_predio").autoIncrement()
    val idAreaComun= integer("id_area_comun").autoIncrement()
    val codigo = varchar("codigo", length = 6)
    val area = double("area")

    override val primaryKey = PrimaryKey(PredioTable.idPredio, AreaComunTable.idAreaComun)
}